package br.com.samaia.cm.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import br.com.samaia.cm.arq.domain.AbstractEntity;

/**
 * Tabela UsuarioCelesc
 * 
 * @author andrerafaelmezzalira
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = UsuarioCelesc.VALIDAR_USUARIO_CELESC, query = "select a from UsuarioCelesc a where a.login = :login and a.senha = :senha and a.usuario.id = :usuario") })
public class UsuarioCelesc implements AbstractEntity<Integer> {

	private static final long serialVersionUID = 1L;

	public static final String VALIDAR_USUARIO_CELESC = "UsuarioCelesc.isUsuarioCelescValido";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String login;

	private String senha;

	@ManyToOne
	private Usuario usuario;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
