package br.com.samaia.cm.service.robo;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.openqa.selenium.WebDriver;

import br.com.samaia.cm.arq.producer.Property;
import br.com.samaia.cm.domain.entity.Header;
import br.com.samaia.cm.domain.entity.Parametro;
import br.com.samaia.cm.domain.entity.Site;
import br.com.samaia.cm.domain.entity.Usuario;
import br.com.samaia.cm.exception.CampoObrigatorioException;
import br.com.samaia.cm.exception.RoboException;
import br.com.samaia.cm.exception.UsuarioInvalidoException;
import br.com.samaia.cm.service.domain.HeaderService;
import br.com.samaia.cm.service.domain.UsuarioService;
import br.com.samaia.cm.service.robo.enel.DebitoEnelService;
import br.com.samaia.cm.service.robo.enel.TitularidadeEnelService;
import br.com.samaia.cm.utils.JsonUtils;
import br.com.samaia.cm.utils.Utils;
import br.com.samaia.cm.vo.HeaderVO;

/**
 * Filtra os sites e grava na tabela Header
 * 
 * @author andrerafaelmezzalira
 *
 */
@Stateless
public class RoboApiService {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	TitularidadeEnelService titularidadeEnelService;

	@Inject
	DebitoEnelService debitoEnelService;


	@Inject
	private HeaderService headerService;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	@Property("cpfCnpjInvalido")
	private String CPF_CNPJ_INVALIDO;

	@Inject
	@Property("siteInvalido")
	private String SITE_INVALIDO;

	@Inject
	@Property("usuarioInvalido")
	private String USUARIO_INVALIDO;

	@Inject
	@Property("servicoInvalido")
	private String SERVICO_INVALIDO;

	@Inject
	@Property("parametroInvalido")
	private String PARAMETRO_INVALIDO;

	@Inject
	@Property("problemaDesenvolvimento")
	private String PROBLEMA_DESENVOLVIMENTO;

	@Inject
	@Property("headless")
	private Boolean headless;

	public void call(HeaderVO headerVO) throws CampoObrigatorioException, UsuarioInvalidoException, RoboException {
		try {
			validations(headerVO);
			boolean okExecute = false;
			for (final Field field : getClass().getDeclaredFields()) {
				if (RoboService.class.isAssignableFrom(field.getType())) {
					field.setAccessible(true);
					final Object service = field.get(this);
					final String site = (String) service.getClass().getMethod("getNomeSite").invoke(service);
					final String servico = (String) service.getClass().getMethod("getNomeServico").invoke(service);
					if (headerVO.getSite().equals(site) && headerVO.getServico().equals(servico)) {
						log.info("encontrou classe executadora " + service.getClass().getName());
						final Header header = new Header();
						log.info(" seta dataHoraInicio: " + LocalDateTime.now());
						header.setDataHoraInicio(LocalDateTime.now());
						header.setIp(headerVO.getIp());
						if (headerVO.getParcelas() != null) {
							header.setParcelas(JsonUtils.toJson(headerVO.getParcelas()));
						}
						validateUser(header, headerVO);
						service.getClass().getMethod("validations", new Class[] { Header.class, HeaderVO.class })
								.invoke(service, header, headerVO);
						final WebDriver webDriver = Utils.getWebDriver(headless);
						headerService.saveHeader(header);
						service.getClass().getMethod("call", new Class[] { WebDriver.class, Header.class })
								.invoke(service, webDriver, header);
						try {
							log.info(" destroy webdriver ");
							webDriver.quit();
						} catch (Exception e) {
							log.info(" destroy webdriver problem ");
						}
						log.info(" seta dataHoraFim: " + LocalDateTime.now());
						header.setDataHoraFim(LocalDateTime.now());
						headerService.saveHeader(header);
						headerVO.setParametros(Utils.getParametros(header));
						log.info("parametros salvo: " + headerVO.getParametros().size());
						log.info("salvo com sucesso.");
						okExecute = true;
						break;
					}
				}
			}
			if (!okExecute) {
				log.info("ocorreu algum problema na criaçao da classe executadora ");
				throw new RoboException(PROBLEMA_DESENVOLVIMENTO);
			}
			log.info("concluiu método call headervo");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof CampoObrigatorioException) {
				throw new CampoObrigatorioException(e.getMessage());
			} else if (e.getCause() != null && e.getCause() instanceof CampoObrigatorioException) {
				throw new CampoObrigatorioException(e.getCause().getMessage());
			} else if (e instanceof UsuarioInvalidoException) {
				throw new UsuarioInvalidoException(e.getMessage());
			} else if (e instanceof RoboException) {
				throw new RoboException(e.getMessage());
			}
			log.severe("|||||||||||| problema RoboApiService call ||||||||||||||||||||||||");
			log.severe(e.getClass().getName());
			if (e.getCause() != null) {
				log.severe(e.getCause().getClass().getName());
			}
			throw new RoboException(PROBLEMA_DESENVOLVIMENTO);
		}
	}

	public void call(Header header) throws RoboException {
		try {
			for (final Field field : getClass().getDeclaredFields()) {
				if (RoboService.class.isAssignableFrom(field.getType())) {
					field.setAccessible(true);
					final Object service = field.get(this);
					final String site = (String) service.getClass().getMethod("getNomeSite").invoke(service);
					final String servico = (String) service.getClass().getMethod("getNomeServico").invoke(service);
					if (header.getSite().getNome().equals(site) && header.getServico().equals(servico)) {
						final WebDriver webDriver = Utils.getWebDriver(headless);
						log.info("encontrou classe executadora " + service.getClass().getName());
						log.info(" seta dataHoraInicioReprocessamento: " + LocalDateTime.now());
						for (Parametro parametro : Utils.getParametros(header)) {
							parametro.setDataHoraInicioReprocessamento(LocalDateTime.now());
							parametro.setEstado(null);
							parametro.setResultado(null);
							parametro.setPdfs(null);
						}
						service.getClass().getMethod("call", new Class[] { WebDriver.class, Header.class })
								.invoke(service, webDriver, header);
						log.info("concluiu método call.");
						try {
							log.info(" destroy webdriver ");
							webDriver.quit();
						} catch (Exception e) {
							log.info(" destroy webdriver problem ");
						}
						log.info(" seta dataHoraFimReprocessamento: " + LocalDateTime.now());
						for (Parametro parametro : Utils.getParametros(header)) {
							parametro.setDataHoraFimReprocessamento(LocalDateTime.now());
						}
						log.info("parametros salvo: " + Utils.getParametros(header).size());
						headerService.saveHeader(header);
						log.info("salvo com sucesso.");
						break;
					}
				}
			}
		} catch (Exception e) {
			log.severe("problema call header");
			e.printStackTrace();
			throw new RoboException(PROBLEMA_DESENVOLVIMENTO);
		}
	}

	private final void validations(final HeaderVO headerVO) throws CampoObrigatorioException {
		log.info("realizando a validação do header ");
		headerVO.setCpfCnpj(Utils.formatCpfCnpjOnlyNumbers(headerVO.getCpfCnpj()));
		if (!Utils.isValidString(headerVO.getCpfCnpj())) {
			throw new CampoObrigatorioException(CPF_CNPJ_INVALIDO);
		}
		if (!Utils.isValidString(headerVO.getSite())) {
			throw new CampoObrigatorioException(SITE_INVALIDO);
		}
		if (!Utils.isValidString(headerVO.getServico())) {
			throw new CampoObrigatorioException(SERVICO_INVALIDO);
		}
		final Collection<?> parametros = headerVO.getParametros();
		if (parametros == null || parametros.isEmpty()) {
			throw new CampoObrigatorioException(PARAMETRO_INVALIDO);
		}
		log.info("validações do header ok");
	}

	private void validateUser(final Header header, final HeaderVO headerVO) throws UsuarioInvalidoException {
		log.info("validar usuario");
		final Usuario usuario = usuarioService.isUsuarioValido(headerVO.getCpfCnpj(), headerVO.getSite(),
				headerVO.getServico());
		if (usuario == null) {
			log.info("usuário inválido ");
			throw new UsuarioInvalidoException(USUARIO_INVALIDO);
		}
		boolean executou = false;
		for (Site site : usuario.getSites()) {
			if (site.getNome().equals(headerVO.getSite()) && site.getServico().equals(headerVO.getServico())) {
				header.setUsuario(usuario);
				header.setSite(site);
				header.setServico(headerVO.getServico());
				log.info("usuario validado ok: " + site.getNome() + " servico " + site.getServico());
				executou = true;
				break;
			}
		}
		if (!executou) {
			log.info("usuário inválido ");
			throw new UsuarioInvalidoException(USUARIO_INVALIDO);
		}
	}
}
