package br.com.samaia.cm.service.robot.unity;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

import br.com.samaia.cm.constants.Constants;
import br.com.samaia.cm.domain.entity.Parameter;
import br.com.samaia.cm.domain.entity.Unity;
import br.com.samaia.cm.utils.JsonUtils;

/**
 * 
 * @author andrerafaelmezzalira e ricardoschmidt
 *
 */
public class AddUnityService extends UnityService {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Override
	public String getService() {
		return Constants.ADD_UNITY;
	}

	@Override
	public void executeService(WebDriver webDriver, Parameter parameter) throws Exception {
		log.info("conf. debito enel");
		Unity unity = (Unity) parameter;

		unity.setResult(JsonUtils.toJson(unity));
		log.info("finalizado conf. debito enel");
	}

}
