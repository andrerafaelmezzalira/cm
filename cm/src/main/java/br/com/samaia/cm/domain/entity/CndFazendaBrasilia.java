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
public class CndFazendaBrasilia extends Parametro {

	private static final long serialVersionUID = 1L;

	private String iptu;
	
	@JsonIgnore
	@ManyToOne
	private Header header;

	public void setHeader(Header header) {
		this.header = header;
	}

	public Header getHeader() {
		return header;
	}



	public String getIptu() {
		return iptu;
	}

	public void setIptu(String iptu) {
		this.iptu = iptu;
	}

}
