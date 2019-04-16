package br.com.samaia.cm.vo;

import java.io.Serializable;
import java.util.Collection;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
public class HeaderVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cpf;

	private String service;

	private Collection<?> parameters;

	private String login;

	private String password;

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setParameters(Collection<?> parameters) {
		this.parameters = parameters;
	}

	public Collection<?> getParameters() {
		return parameters;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getService() {
		return service;
	}
}
