package br.com.samaia.cm.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
@Entity
@IdClass(ReprocessingId.class)
@NamedQueries({ @NamedQuery(name = Reprocessing.GET, query = "select a from Reprocessing a") })
public class Reprocessing implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String GET = "Reprocessing.get";

	@Id
	private Integer headers;

	@Id
	private Integer parameter;

	private String entity;

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getEntity() {
		return entity;
	}

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
}
