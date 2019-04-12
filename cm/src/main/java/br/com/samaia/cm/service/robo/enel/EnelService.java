package br.com.samaia.cm.service.robo.enel;

import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

import com.fasterxml.jackson.core.type.TypeReference;

import br.com.samaia.cm.constants.Constants;
import br.com.samaia.cm.domain.entity.Enel;
import br.com.samaia.cm.domain.entity.Header;
import br.com.samaia.cm.exception.CampoObrigatorioException;
import br.com.samaia.cm.service.robo.AbstractRoboService;
import br.com.samaia.cm.utils.JsonUtils;
import br.com.samaia.cm.utils.Utils;
import br.com.samaia.cm.vo.HeaderVO;

/**
 * Site ENEL
 * 
 * @author andrerafaelmezzalira
 *
 */
public abstract class EnelService extends AbstractRoboService {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Override
	public String getNomeSite() {
		return Constants.ENEL;
	}

	protected void initPage(WebDriver webDriver) {
		log.info(getProperty("enelSite"));
		webDriver.get(getProperty("enelSite"));
	}

	@Override
	public void validations(Header header, HeaderVO headerVO) throws CampoObrigatorioException {
		log.info("validar os parametros enel");
		try {
			header.setEnels(JsonUtils.convertList(headerVO.getParametros(), new TypeReference<List<Enel>>() {
			}));
		} catch (Exception e) {
			throw new CampoObrigatorioException(getProperty("tipoObjetoInvalido").toString());
		}
		log.info("parametros populados ok");
		for (Enel enel : header.getEnels()) {
			validations(enel);
			enel.setCpfCnpj(Utils.formatCpfCnpjOnlyNumbers(enel.getCpfCnpj()));
			if (!Utils.isValidString(enel.getUnidadeConsumidora())) {
				throw new CampoObrigatorioException(getProperty("enelUnidadeConsumidoraInvalida"));
			}
			if (!Utils.isValidString(enel.getCpfCnpj())) {
				throw new CampoObrigatorioException(getProperty("cpfCnpjInvalido"));
			}
			enel.setCpfCnpj(enel.getCpfCnpj().trim());
			enel.setUnidadeConsumidora(enel.getUnidadeConsumidora().trim());
			try {
				Long.parseLong(enel.getUnidadeConsumidora());
			} catch (NumberFormatException e) {
				throw new CampoObrigatorioException(getProperty("enelUnidadeConsumidoraInvalida"));
			}
			try {
				Long.parseLong(enel.getCpfCnpj());
			} catch (NumberFormatException e) {
				throw new CampoObrigatorioException(getProperty("cpfCnpjInvalido"));
			}

			if (enel.getUnidadeConsumidora().length() > Integer.parseInt(getProperty("enelSizeUnidadeConsumidora"))) {
				throw new CampoObrigatorioException(getProperty("enelUnidadeConsumidoraInvalida"));
			}
			if (enel.getCpfCnpj().length() > Integer.parseInt(getProperty("sizeCpfCnpj"))
					|| enel.getCpfCnpj().length() < Integer.parseInt(getProperty("enelMinSizeCpfCnpj"))) {
				throw new CampoObrigatorioException(getProperty("cpfCnpjInvalido"));
			}
		}
		log.info("parametros enel validados");
	}
}