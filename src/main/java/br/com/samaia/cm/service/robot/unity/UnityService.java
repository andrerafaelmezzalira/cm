package br.com.samaia.cm.service.robot.unity;

import java.util.List;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.type.TypeReference;

import br.com.samaia.cm.domain.entity.Header;
import br.com.samaia.cm.domain.entity.Unity;
import br.com.samaia.cm.exception.RequiredFieldException;
import br.com.samaia.cm.service.robot.AbstractRobotService;
import br.com.samaia.cm.utils.JsonUtils;
import br.com.samaia.cm.vo.HeaderVO;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
public abstract class UnityService extends AbstractRobotService {

	private Logger log = Logger.getLogger(this.getClass().getName());


	@Override
	public void validations(Header header, HeaderVO headerVO) throws RequiredFieldException {
		log.info("validar os parametros enel");
		try {
			header.setUnits(JsonUtils.convertList(headerVO.getParameters(), new TypeReference<List<Unity>>() {
			}));
		} catch (Exception e) {
			throw new RequiredFieldException(getProperty("tipoObjetoInvalido").toString());
		}
		log.info("parametros populados ok");
	}
}