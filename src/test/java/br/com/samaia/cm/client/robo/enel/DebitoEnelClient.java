package br.com.samaia.cm.client.robo.enel;

import br.com.samaia.cm.client.AbstractClient;
import br.com.samaia.cm.constants.Constants;
import br.com.samaia.cm.vo.HeaderVO;

/**
 * Client enel conf. debito
 * 
 * @author andrerafaelmezzalira
 *
 */
public class DebitoEnelClient extends AbstractClient<HeaderVO> {

	public static void main(String[] args) {
		DebitoEnelClient client = new DebitoEnelClient();
		client.log(client.post(URL_ROBO, client.getFonteDeDados()));
	}

	@Override
	protected HeaderVO getFonteDeDados() {
		return EnelFonteDados.getFonteDadosExcel(Constants.DEBITO);
	}

}
