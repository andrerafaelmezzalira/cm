package br.com.samaia.cm.client.repository.usuario;

import br.com.samaia.cm.client.AbstractClient;
import br.com.samaia.cm.domain.entity.Usuario;

/**
 * Client que cria um usuario
 * 
 * @author andrerafaelmezzalira
 *
 */
public class SaveUsuarioClient extends AbstractClient<Usuario> {

	private static final String URL_SAVE_USUARIO = "http://localhost:8080/cm/api/saveUsuario";

	public static void main(String[] args) {
		SaveUsuarioClient client = new SaveUsuarioClient();
		client.log(client.post(URL_SAVE_USUARIO, client.getFonteDeDados()));
	}

	@Override
	protected Usuario getFonteDeDados() {
		return UsuarioJson.getFonteDadosObject();

	}

}
