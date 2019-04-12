package br.com.samaia.cm.domain.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Tabela Compesa
 * 
 * @author andrerafaelmezzalira
 *
 */
@Entity
public class Compesa extends Parametro {

	private static final long serialVersionUID = 1L;

	private String matricula;
	
	@JsonIgnore
	@ManyToOne
	private Header header;

	public void setHeader(Header header) {
		this.header = header;
	}

	public Header getHeader() {
		return header;
	}



	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMatricula() {
		return matricula;
	}
}
