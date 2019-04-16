package br.com.samaia.cm.service.robot;

import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.samaia.cm.arq.producer.Property;
import br.com.samaia.cm.domain.entity.Header;
import br.com.samaia.cm.domain.entity.Person;
import br.com.samaia.cm.domain.entity.Service;
import br.com.samaia.cm.exception.InvalidPersonException;
import br.com.samaia.cm.exception.RequiredFieldException;
import br.com.samaia.cm.service.domain.HeaderService;
import br.com.samaia.cm.service.domain.PersonService;
import br.com.samaia.cm.utils.Utils;
import br.com.samaia.cm.vo.FindHeaderVO;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
@Stateless
public class FindRobotApiService {

	private Logger log = Logger.getLogger(this.getClass().getName());

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
	@Property("servicoInvalido")
	private String SERVICO_INVALIDO;

	@Inject
	@Property("usuarioInvalido")
	private String USUARIO_INVALIDO;

	@Inject
	@Property("dataHoraInvalido")
	private String DATA_HORA_INVALIDO;

	public List<Header> find(FindHeaderVO consultaHeaderVO) throws RequiredFieldException, InvalidPersonException {
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
					log.severe("problem sizeof list find robot api");
				}
			}
		}
	}

	private final void validations(final FindHeaderVO consultaHeaderVO)
			throws RequiredFieldException, InvalidPersonException {
		log.info("realizando a validação do header ");
		if (!Utils.isValidString(consultaHeaderVO.getCpf())) {
			throw new RequiredFieldException(CPF_CNPJ_INVALIDO);
		}
		if (!Utils.isValidString(consultaHeaderVO.getService())) {
			throw new RequiredFieldException(SERVICO_INVALIDO);
		}
		if (consultaHeaderVO.getDateTime() == null) {
			throw new RequiredFieldException(DATA_HORA_INVALIDO);
		}
		log.info("validar usuario");
		Person person = new Person();
		person.setCpf(consultaHeaderVO.getCpf());
		usuarioService.loadPerson(person);
		boolean executou = false;
		for (Service site : person.getServices()) {
			if (site.getName().equals(consultaHeaderVO.getService())) {
				log.info("usuario validado ok: " + site.getName());
				executou = true;
				break;
			}
		}
		if (!executou) {
			log.info("usuário inválido ");
			throw new InvalidPersonException(USUARIO_INVALIDO);
		}

		log.info("validações do header ok");
	}
}