package br.com.samaia.cm.service.robot;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.openqa.selenium.WebDriver;

import br.com.samaia.cm.arq.producer.Property;
import br.com.samaia.cm.domain.entity.Header;
import br.com.samaia.cm.domain.entity.Parameter;
import br.com.samaia.cm.domain.entity.Person;
import br.com.samaia.cm.exception.InvalidPersonException;
import br.com.samaia.cm.exception.RequiredFieldException;
import br.com.samaia.cm.exception.RobotException;
import br.com.samaia.cm.service.domain.HeaderService;
import br.com.samaia.cm.service.domain.PersonService;
import br.com.samaia.cm.service.robot.unity.AddUnityService;
import br.com.samaia.cm.utils.Utils;
import br.com.samaia.cm.vo.HeaderVO;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
@Stateless
public class RobotApiService {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	AddUnityService addUnityService;

	@Inject
	private HeaderService headerService;

	@Inject
	private PersonService usuarioService;

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

	public void call(HeaderVO headerVO) throws RequiredFieldException, InvalidPersonException, RobotException {
		try {
			validations(headerVO);
			boolean okExecute = false;
			for (final Field field : getClass().getDeclaredFields()) {
				if (RobotService.class.isAssignableFrom(field.getType())) {
					field.setAccessible(true);
					final Object serviceObject = field.get(this);
					final String service = (String) serviceObject.getClass().getMethod("getService")
							.invoke(serviceObject);
					if (headerVO.getService().equals(service)) {
						log.info("execute class " + serviceObject.getClass().getName());
						final Header header = new Header();
						header.setDateTimeInit(LocalDateTime.now());
						validateUser(header, headerVO);
						serviceObject.getClass().getMethod("validations", new Class[] { Header.class, HeaderVO.class })
								.invoke(serviceObject, header, headerVO);
						final WebDriver webDriver = Utils.getWebDriver(headless);
						headerService.saveHeader(header);
						serviceObject.getClass().getMethod("call", new Class[] { WebDriver.class, Header.class })
								.invoke(serviceObject, webDriver, header);
						try {
							log.info(" destroy webdriver ");
							webDriver.quit();
						} catch (Exception e) {
							log.info(" destroy webdriver problem ");
						}
						header.setDateTimeEnd(LocalDateTime.now());
						headerService.saveHeader(header);
						headerVO.setParameters(Utils.getParameters(header));
						log.info("parameters size: " + headerVO.getParameters().size());
						log.info("sucessufuly");
						okExecute = true;
						break;
					}
				}
			}
			if (!okExecute) {
				log.severe("ocorreu algum problema na criaçao da classe executadora ");
				throw new RobotException(PROBLEMA_DESENVOLVIMENTO);
			}
			log.info("done call vo ");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof RequiredFieldException) {
				throw new RequiredFieldException(e.getMessage());
			} else if (e.getCause() != null && e.getCause() instanceof RequiredFieldException) {
				throw new RequiredFieldException(e.getCause().getMessage());
			} else if (e instanceof InvalidPersonException) {
				throw new InvalidPersonException(e.getMessage());
			} else if (e instanceof RobotException) {
				throw new RobotException(e.getMessage());
			}
			log.severe("|||||||||||| problem RoboApiService call ||||||||||||||||||||||||");
			log.severe(e.getClass().getName());
			if (e.getCause() != null) {
				log.severe(e.getCause().getClass().getName());
			}
			throw new RobotException(PROBLEMA_DESENVOLVIMENTO);
		}
	}

	public void call(Header header) throws RobotException {
		try {
			for (final Field field : getClass().getDeclaredFields()) {
				if (RobotService.class.isAssignableFrom(field.getType())) {
					field.setAccessible(true);
					final Object serviceObject = field.get(this);
					final String service = (String) serviceObject.getClass().getMethod("getService")
							.invoke(serviceObject);
					if (header.getService().getName().equals(service)) {
						final WebDriver webDriver = Utils.getWebDriver(headless);
						log.info("execute class " + serviceObject.getClass().getName());
						for (Parameter parameter : Utils.getParameters(header)) {
							parameter.setDateTimeInitReprocessing(LocalDateTime.now());
							parameter.setState(null);
							parameter.setResult(null);
						}
						serviceObject.getClass().getMethod("call", new Class[] { WebDriver.class, Header.class })
								.invoke(serviceObject, webDriver, header);
						log.info("done call");
						try {
							log.info(" destroy webdriver ");
							webDriver.quit();
						} catch (Exception e) {
							log.info(" destroy webdriver problem ");
						}
						for (Parameter parameter : Utils.getParameters(header)) {
							parameter.setDateTimeEndReprocessing(LocalDateTime.now());
						}
						log.info("parameters size: " + Utils.getParameters(header).size());
						headerService.saveHeader(header);
						log.info("sucessufuly");
						break;
					}
				}
			}
		} catch (Exception e) {
			log.severe("problem call header");
			e.printStackTrace();
			throw new RobotException(PROBLEMA_DESENVOLVIMENTO);
		}
	}

	private final void validations(final HeaderVO headerVO) throws RequiredFieldException {
		log.info("init validations header ");
		if (!Utils.isValidString(headerVO.getCpf())) {
			throw new RequiredFieldException(CPF_CNPJ_INVALIDO);
		}
		if (!Utils.isValidString(headerVO.getService())) {
			throw new RequiredFieldException(SERVICO_INVALIDO);
		}
		final Collection<?> parametros = headerVO.getParameters();
		if (parametros == null || parametros.isEmpty()) {
			throw new RequiredFieldException(PARAMETRO_INVALIDO);
		}
		log.info("end validations header");
	}

	private void validateUser(final Header header, final HeaderVO headerVO) throws InvalidPersonException {
		log.info("load person ");
		final Person person = new Person();
		person.setCpf(headerVO.getCpf());
		usuarioService.loadPerson(person);
	}
}
