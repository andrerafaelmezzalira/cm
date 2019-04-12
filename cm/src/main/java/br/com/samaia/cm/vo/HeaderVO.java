package br.com.samaia.cm.vo;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Value Object que representa os parametros de entrada da api
 * 
 * @author andrerafaelmezzalira
 *
 */
public class HeaderVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cpfCnpj;

	private String site;

	private String servico;

	@JsonIgnore
	private String ip;

	private Collection<?> parametros;

	private Integer[] parcelas;

	private String login;

	private String senha;

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setParcelas(Integer[] parcelas) {
		this.parcelas = parcelas;
	}

	public Integer[] getParcelas() {
		return parcelas;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public String getServico() {
		return servico;
	}

	public void setParametros(Collection<?> parametros) {
		this.parametros = parametros;
	}

	public Collection<?> getParametros() {
		return parametros;
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
}
