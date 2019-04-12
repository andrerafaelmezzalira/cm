package br.com.samaia.cm.service.robo;

import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.samaia.cm.arq.producer.Property;
import br.com.samaia.cm.domain.entity.Header;
import br.com.samaia.cm.domain.entity.Site;
import br.com.samaia.cm.domain.entity.Usuario;
import br.com.samaia.cm.exception.CampoObrigatorioException;
import br.com.samaia.cm.exception.UsuarioInvalidoException;
import br.com.samaia.cm.service.domain.HeaderService;
import br.com.samaia.cm.service.domain.UsuarioService;
import br.com.samaia.cm.utils.Utils;
import br.com.samaia.cm.vo.ConsultaHeaderVO;

/**
 * Filtra uma lista de headers para a API
 * 
 * @author andrerafaelmezzalira
 *
 */
@Stateless
public class ConsultaRoboApiService {

	private Logger log = Logger.getLogger(this.getClass().getName());

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
	@Property("servicoInvalido")
	private String SERVICO_INVALIDO;

	@Inject
	@Property("usuarioInvalido")
	private String USUARIO_INVALIDO;

	@Inject
	@Property("dataHoraInvalido")
	private String DATA_HORA_INVALIDO;


	public List<Header> find(ConsultaHeaderVO consultaHeaderVO)
			throws CampoObrigatorioException, UsuarioInvalidoException {
		validations(consultaHeaderVO);
		List<Header> headers = headerService.consultar(consultaHeaderVO);
		for (Header header : headers) {
			sizeOf(header);
		}
		return headers;
	}

	private void sizeOf(Header header) {
		for (final Field field : header.getClass().getDeclaredFields()) {
			if (List.class.isAssignableFrom(field.getType())) {
				field.setAccessible(true);
				try {
					final List<?> list = (List<?>) field.get(header);
					log.info(String.valueOf(list.size()));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					log.severe("problema sizeof list consulta robo api");
				}
			}
		}
	}

	private final void validations(final ConsultaHeaderVO consultaHeaderVO)
			throws CampoObrigatorioException, UsuarioInvalidoException {
		log.info("realizando a validação do header ");
		consultaHeaderVO.setCpfCnpj(Utils.formatCpfCnpjOnlyNumbers(consultaHeaderVO.getCpfCnpj()));
		if (!Utils.isValidString(consultaHeaderVO.getCpfCnpj())) {
			throw new CampoObrigatorioException(CPF_CNPJ_INVALIDO);
		}
		if (!Utils.isValidString(consultaHeaderVO.getSite())) {
			throw new CampoObrigatorioException(SITE_INVALIDO);
		}
		if (!Utils.isValidString(consultaHeaderVO.getServico())) {
			throw new CampoObrigatorioException(SERVICO_INVALIDO);
		}
		if (consultaHeaderVO.getDataHora() == null) {
			throw new CampoObrigatorioException(DATA_HORA_INVALIDO);
		}
		log.info("validar usuario");
		final Usuario usuario = usuarioService.isUsuarioValido(consultaHeaderVO.getCpfCnpj(),
				consultaHeaderVO.getSite(), consultaHeaderVO.getServico());
		if (usuario == null) {
			log.info("usuário invalido ");
			throw new UsuarioInvalidoException(USUARIO_INVALIDO);
		}
		boolean executou = false;
		for (Site site : usuario.getSites()) {
			if (site.getNome().equals(consultaHeaderVO.getSite())
					&& site.getServico().equals(consultaHeaderVO.getServico())) {
				log.info("usuario validado ok: " + site.getNome() + " servico " + site.getServico());
				executou = true;
				break;
			}
		}
		if (!executou) {
			log.info("usuário inválido ");
			throw new UsuarioInvalidoException(USUARIO_INVALIDO);
		}

		log.info("validações do header ok");
	}
}