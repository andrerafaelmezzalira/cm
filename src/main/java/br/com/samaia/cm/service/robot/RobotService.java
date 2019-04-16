package br.com.samaia.cm.service.robot;

import br.com.samaia.cm.domain.entity.Header;
import br.com.samaia.cm.exception.RequiredFieldException;
import br.com.samaia.cm.vo.HeaderVO;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
public interface RobotService {

	String getService();

	void validations(final Header header, final HeaderVO headerVO) throws RequiredFieldException;
}
