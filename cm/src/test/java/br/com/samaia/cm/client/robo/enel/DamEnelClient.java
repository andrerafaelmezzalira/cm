package br.com.samaia.cm.client.robo.enel;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.samaia.cm.client.AbstractClient;
import br.com.samaia.cm.constants.Constants;
import br.com.samaia.cm.utils.JsonUtils;
import br.com.samaia.cm.vo.HeaderVO;

/**
 * Client enel dam
 * 
 * @author ricardoschmimdt
 *
 */
public class DamEnelClient extends AbstractClient<HeaderVO> {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		DamEnelClient client = new DamEnelClient();
		String json = client.post(URL_ROBO, client.getFonteDeDados());
		HeaderVO headerVO = JsonUtils.fromJson(json, HeaderVO.class);
		System.err.println(headerVO);
	}

	@Override
	protected HeaderVO getFonteDeDados() {
		return EnelFonteDados.getFonteDadosExcel(Constants.DAM);
	}

}
