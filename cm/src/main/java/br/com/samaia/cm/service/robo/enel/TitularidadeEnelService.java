package br.com.samaia.cm.service.robo.enel;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import br.com.samaia.cm.constants.Constants;
import br.com.samaia.cm.domain.entity.Enel;
import br.com.samaia.cm.domain.entity.Parametro;
import br.com.samaia.cm.utils.JsonUtils;
import br.com.samaia.cm.utils.Utils;
import br.com.samaia.cm.vo.TitularidadeVO;

/**
 * ENEL - Conferência de Titularidade - Módulo Energia
 * 
 * @author andrerafaelmezzalira
 *
 */
public class TitularidadeEnelService extends EnelService {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Override
	public String getNomeServico() {
		return Constants.TITULARIDADE;
	}

	@Override
	public void executeSite(WebDriver webDriver, Parametro parametro) throws Exception {
		log.info("conf. titularidade enel");
		initPage(webDriver);
		Enel enel = (Enel) parametro;
		TitularidadeVO titularidadeVO = new TitularidadeVO();
		if (isTitular(webDriver, enel)) {
			titularidadeVO.setStatus(Constants.TITULAR);
			titularidadeVO.setNome(getNome(webDriver));
		} else {
			titularidadeVO.setStatus(Constants.NAO_IDENTIFICADO);
		}
		log.info("titularidade " + titularidadeVO.getStatus());
		enel.setResultado(JsonUtils.toJson(titularidadeVO));
		log.info("finalizado conf. tituaridade enel");
	}

	public boolean isTitular(WebDriver webDriver, Enel enel) throws Exception {

		boolean titular = processarEntrada(webDriver, enel);

		if (titular && getProperty("enelTitularSite").equals(webDriver.getCurrentUrl())) {
			webDriver.get(getProperty("enelUrlConsultaDebito"));
		} else if (titular) {
			log.severe("problema no carregamento da pagina");
			throw new Exception("problema no carregamento da pagina");
		}

		return titular;
	}

	private boolean processarEntrada(WebDriver webDriver, Enel enel) throws InterruptedException {

		Utils.sleep(1000);
		webDriver.findElement(By.id(getProperty("enelInscricao"))).sendKeys(enel.getUnidadeConsumidora());
		Utils.sleep(1000);
		webDriver.findElement(By.id(getProperty("enelCpfCnpj"))).sendKeys(enel.getCpfCnpj());
		Utils.sleep(1000);
		webDriver.findElement(By.id(getProperty("enelSubmit"))).click();
		Utils.sleep(2000);

		return isTitular(webDriver);

	}

	private boolean isTitular(WebDriver webDriver) throws InterruptedException {
		boolean titular = false;
		try {
			webDriver.switchTo().alert().accept();
			Utils.sleep(4000);
		} catch (NoAlertPresentException e) {
			titular = true;
		}
		log.info("titular " + titular);
		return titular;
	}

	private String getNome(WebDriver webDriver) {
		Matcher matcher = Pattern.compile(getProperty("enelRegexpNomeCliente"), Pattern.DOTALL)
				.matcher(webDriver.findElement(By.id(getProperty("enelNomeCliente"))).getText());
		return matcher.find() ? matcher.group(1) : "";
	}
}
