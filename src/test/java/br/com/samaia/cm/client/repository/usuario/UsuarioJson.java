package br.com.samaia.cm.client.repository.usuario;

import br.com.samaia.cm.domain.entity.Usuario;

/**
 * 
 * Fonte de dados para usuario
 * 
 * @author andrerafaelmezzalira
 *
 */
public class UsuarioJson {

	public static final Usuario getFonteDadosObject() {
		Usuario usuario = new Usuario();
		usuario.setCpfCnpj("66666666666669");

		return usuario;
	}
}
