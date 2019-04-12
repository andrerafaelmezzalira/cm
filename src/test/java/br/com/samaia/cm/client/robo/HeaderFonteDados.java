package br.com.samaia.cm.client.robo;

import br.com.samaia.cm.vo.HeaderVO;

/**
 * 
 * Fonte de dados HeaderVO
 * 
 * @author andrerafaelmezzalira
 *
 */
public class HeaderFonteDados {

	public static final HeaderVO getHeader(final String site, final String servico) {
		final HeaderVO header = new HeaderVO();
		header.setCpfCnpj("66666666666669");
		header.setServico(servico);
		header.setSite(site);
		return header;
	}
}
