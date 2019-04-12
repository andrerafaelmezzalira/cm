package br.com.samaia.cm.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * View que representa todos os parametros invalidos
 * 
 * @author andrerafaelmezzalira
 *
 */
@Entity
@IdClass(ReprocessamentoId.class)
@NamedQueries({ @NamedQuery(name = Reprocessamento.GET_HEADER_INVALIDOS, query = "select a from Reprocessamento a") })
public class Reprocessamento implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String GET_HEADER_INVALIDOS = "Reprocessamento.getAllParametersInvalids";

	@Id
	private Integer headers;

	@Id
	private Integer parametro;

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

	public void setParametro(Integer parametro) {
		this.parametro = parametro;
	}

	public Integer getParametro() {
		return parametro;
	}
}
