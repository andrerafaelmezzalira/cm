package br.com.samaia.cm.domain.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Tabela Fazenda Brasilia
 * 
 * @author vitoralmeida
 *
 */
@Entity
public class FazendaBrasilia extends Parametro {

	private static final long serialVersionUID = 1L;

	private String inscricao;
	
	@JsonIgnore
	@ManyToOne
	private Header header;

	public void setHeader(Header header) {
		this.header = header;
	}

	public Header getHeader() {
		return header;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	
	public String getInscricao() {
		return inscricao;
	}

}
