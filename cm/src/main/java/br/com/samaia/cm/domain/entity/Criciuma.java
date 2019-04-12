package br.com.samaia.cm.domain.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Tabela Criciuma
 * 
 * @author vitoralmeida
 *
 */
@Entity
public class Criciuma extends Parametro {

	private static final long serialVersionUID = 1L;

	private String codigoImovelBetha;
	
	@JsonIgnore
	@ManyToOne
	private Header header;

	public void setHeader(Header header) {
		this.header = header;
	}

	public Header getHeader() {
		return header;
	}

	public String getCodigoImovelBetha() {
		return codigoImovelBetha;
	}

	public void setCodigoImovelBetha(String codigoImovelBetha) {
		this.codigoImovelBetha = codigoImovelBetha;
	}



}
