package br.com.samaia.cm.vo;

import java.io.Serializable;

/**
 * Value Object Titularidade VO
 * 
 * @author andrerafaelmezzalira
 *
 */
public class TitularidadeVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String status;

	private String nome;
	
	private String cpfCnpjTitular;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public String getCpfCnpjTitular() {
		return cpfCnpjTitular;
	}

	public void setCpfCnpjTitular(String cpfCnpjTitular) {
		this.cpfCnpjTitular = cpfCnpjTitular;
	}
	
}
