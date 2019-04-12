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
public class BombeiroPernambuco extends Parametro {

	private static final long serialVersionUID = 1L;

	private String sequencial;

	@JsonIgnore
	@ManyToOne
	private Header header;

	public void setHeader(Header header) {
		this.header = header;
	}

	public Header getHeader() {
		return header;
	}

	public void setSequencial(String sequencial) {
		this.sequencial = sequencial;
	}
	
	public String getSequencial() {
		return sequencial;
	}
}
