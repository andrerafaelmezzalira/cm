package br.com.samaia.cm.client.repository.usuario;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.samaia.cm.client.AbstractClient;
import br.com.samaia.cm.domain.entity.Usuario;
import br.com.samaia.cm.utils.JsonUtils;

/**
 * Client que valida um usuario
 * 
 * @author andrerafaelmezzalira
 *
 */
public class ValidaUsuarioClient extends AbstractClient<Usuario> {

	private static final String URL_VALIDA_USUARIO = "http://localhost:8080/cm/api/validarUsuario";

	public static void main(String[] args) throws UnsupportedEncodingException, JsonProcessingException {
		ValidaUsuarioClient client = new ValidaUsuarioClient();
		client.log(client.get(URL_VALIDA_USUARIO,
				"?usuario=" + URLEncoder.encode(JsonUtils.toJson(client.getFonteDeDados()), "UTF-8")));
	}

	@Override
	protected Usuario getFonteDeDados() {
		return UsuarioJson.getFonteDadosObject();

	}

}
