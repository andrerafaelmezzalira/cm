package br.com.samaia.cm.domain.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Tabela Celesc
 * 
 * @author andrerafaelmezzalira
 *
 */
@Entity
public class Celesc extends Parametro {

	private static final long serialVersionUID = 1L;

	private String unidadeConsumidora;

	private String cpfCnpj;

	@JsonIgnore
	@ManyToOne
	private Header header;

	public void setHeader(Header header) {
		this.header = header;
	}

	public Header getHeader() {
		return header;
	}


	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setUnidadeConsumidora(String unidadeConsumidora) {
		this.unidadeConsumidora = unidadeConsumidora;
	}

	public String getUnidadeConsumidora() {
		return unidadeConsumidora;
	}
}