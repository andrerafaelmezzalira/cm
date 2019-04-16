package br.com.samaia.cm.client.robot;

import br.com.samaia.cm.vo.HeaderVO;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
public class HeaderDataSource {

	public static final HeaderVO getHeader(final String service) {
		final HeaderVO header = new HeaderVO();
		header.setCpf("00000000000");
		header.setService(service);
		header.setLogin("andre");
		header.setPassword("andre123");
		return header;
	}
}
