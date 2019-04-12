package br.com.samaia.cm.domain.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Tabela BombeiroPernambuco
 * 
 * @author andrerafaelmezzalira
 *
 */
@Entity
public class PatrimonioDeTodos extends Parametro {

	private static final long serialVersionUID = 1L;

	private String rip;

	@JsonIgnore
	@ManyToOne
	private Header header;

	public void setHeader(Header header) {
		this.header = header;
	}

	public Header getHeader() {
		return header;
	}


	public void setRip(String rip) {
		this.rip = rip;
	}

	public String getRip() {
		return rip;
	}

}
