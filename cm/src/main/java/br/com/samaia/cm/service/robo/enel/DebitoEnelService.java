package br.com.samaia.cm.service.robo.enel;

import java.time.LocalDate;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.samaia.cm.constants.Constants;
import br.com.samaia.cm.domain.entity.Enel;
import br.com.samaia.cm.domain.entity.Parametro;
import br.com.samaia.cm.utils.JsonUtils;
import br.com.samaia.cm.utils.RegexpUtils;
import br.com.samaia.cm.utils.Utils;
import br.com.samaia.cm.vo.DebitoVO;

/**
 * ENEL - Conferência de Débito - Módulo Energia
 * 
 * @author andrerafaelmezzalira e ricardoschmidt
 *
 */
public class DebitoEnelService extends EnelService {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private TitularidadeEnelService titularidadeEnelService;

	@Override
	public String getNomeServico() {
		return Constants.DEBITO;
	}

	@Override
	public void executeSite(WebDriver webDriver, Parametro parametro) throws Exception {
		log.info("conf. debito enel");
		initPage(webDriver);
		Enel enel = (Enel) parametro;

		DebitoVO debitoVO = new DebitoVO();
		debitoVO.setStatus(getStatus(webDriver, enel));

		if (debitoVO.getStatus().equals(Constants.COM_DEBITO)) {
			populateVO(debitoVO, webDriver.findElement(By.id(getProperty("enelContentConsultaDebito"))).getText());
		}
		enel.setResultado(JsonUtils.toJson(debitoVO));
		log.info("finalizado conf. debito enel");
	}

	public String getStatus(WebDriver webDriver, Enel enel) throws Exception {

		String status = titularidadeEnelService.isTitular(webDriver, enel) ? getStatusIdentificado(webDriver)
				: Constants.NAO_IDENTIFICADO;

		log.info("status " + status);

		return status;
	}

	private String getStatusIdentificado(WebDriver webDriver) throws InterruptedException {
		webDriver.findElement(By.id(getProperty("enelContentBotaoEnviar"))).click();
		Utils.sleep(5000);
		return webDriver.findElements(By.id(getProperty("enelContentConsultaDebito"))).isEmpty() ? Constants.SEM_DEBITO
				: Constants.COM_DEBITO;
	}

	private final void populateVO(DebitoVO debitoVO, String text) {
		log.info("preenchendo objeto debito enel");

		String[] str = text.split("\\n");
		LocalDate vencimento;
		Double valorDebito = 0.0;
		Double valorDebitoAtrasado = 0.0;
		Integer totalFaturaAtrasada = 0;

		for (int i = 1; i < str.length; i++) {
			vencimento = Utils.formatDate(RegexpUtils.executeRegexp(str[i], getProperty("enelVencimento"), 1));
			if (vencimento.isBefore(LocalDate.now())) {
				totalFaturaAtrasada++;
				valorDebitoAtrasado += Double
						.parseDouble(RegexpUtils.executeRegexp(str[i], getProperty("enelValorFatura"), 1));
			}
			valorDebito += Double.parseDouble(RegexpUtils.executeRegexp(str[i], getProperty("enelValorFatura"), 1));
		}

		debitoVO.setTotalFatura((str.length - 1) + "");
		debitoVO.setTotalFaturaAtrasada(totalFaturaAtrasada.toString());
		debitoVO.setValorDebito(valorDebito.toString());
		debitoVO.setValorDebitoAtrasado(valorDebitoAtrasado.toString());

		if (totalFaturaAtrasada != 0) {
			debitoVO.setFaturaAtrasada(Constants.SIM);
		} else {
			debitoVO.setFaturaAtrasada(Constants.NAO);
		}

		log.info("objeto debito enel preenchido");
	}

}
