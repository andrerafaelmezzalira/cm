package br.com.samaia.cm.client.robo.consulta;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.samaia.cm.client.AbstractClient;
import br.com.samaia.cm.constants.Constants;
import br.com.samaia.cm.utils.JsonUtils;
import br.com.samaia.cm.vo.ConsultaHeaderVO;

/**
 * Client consulta robo
 * 
 * @author andrerafaelmezzalira
 *
 */
public class ConsultaRoboClient extends AbstractClient<ConsultaHeaderVO> {

	static final String URL_CONSULTA = "http://localhost:8080/cm/api/consultar";
	static final String URL_CONSULTA_HOMOLOG = "http://homologacao.projetabrasil.net.br:8080/cm/api/consultar";

	public static void main(String[] args) throws UnsupportedEncodingException, JsonProcessingException {
		ConsultaRoboClient client = new ConsultaRoboClient();
		client.log(client.get(URL_CONSULTA,
				"?header=" + URLEncoder.encode(JsonUtils.toJson(client.getFonteDeDados()), "UTF-8")));
	}

	@Override
	protected ConsultaHeaderVO getFonteDeDados() {
		ConsultaHeaderVO consultaHeader = new ConsultaHeaderVO();
		consultaHeader.setCpfCnpj("66666666666669");
		consultaHeader.setSite(Constants.ENEL);
		consultaHeader.setServico(Constants.DAM);
		consultaHeader.setDataHora(LocalDateTime.of(2018, 11, 28, 22, 0));
		return consultaHeader;
	}
}
