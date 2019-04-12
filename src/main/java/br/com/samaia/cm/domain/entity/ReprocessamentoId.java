package br.com.samaia.cm.domain.entity;

import java.io.Serializable;

/**
 * Representa o id da view Reprocessamento
 * 
 * @author andrerafaelmezzalira
 *
 */
public class ReprocessamentoId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer headers;

	private Integer parametro;

	public void setHeaders(Integer headers) {
		this.headers = headers;
	}

	public Integer getHeaders() {
		return headers;
	}

	public void setParametro(Integer parametro) {
		this.parametro = parametro;
	}

	public Integer getParametro() {
		return parametro;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
