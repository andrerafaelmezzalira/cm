package br.com.samaia.cm.domain.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Tabela São José
 * 
 * @author ricardoschmidt
 *
 */
@Entity
public class SaoJose extends Parametro {

	private static final long serialVersionUID = 1L;

	private String inscImobiliaria;
	
	@JsonIgnore
	@ManyToOne
	private Header header;

	public void setHeader(Header header) {
		this.header = header;
	}

	public Header getHeader() {
		return header;
	}

	public String getInscImobiliaria() {
		return inscImobiliaria;
	}

	public void setInscImobiliaria(String inscImobiliaria) {
		this.inscImobiliaria = inscImobiliaria;
	}

}
