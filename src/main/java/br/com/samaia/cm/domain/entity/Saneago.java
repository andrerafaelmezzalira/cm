package br.com.samaia.cm.domain.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Tabela Saneago
 * 
 * @author ricardoschmidt
 *
 */
@Entity
public class Saneago extends Parametro {

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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}
