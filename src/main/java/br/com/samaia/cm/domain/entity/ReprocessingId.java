package br.com.samaia.cm.domain.entity;

import java.io.Serializable;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
public class ReprocessingId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer headers;

	private Integer parameter;

	public void setHeaders(Integer headers) {
		this.headers = headers;
	}

	public Integer getHeaders() {
		return headers;
	}

	public void setParameter(Integer parameter) {
		this.parameter = parameter;
	}

	public Integer getParameter() {
		return parameter;
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
