package br.com.samaia.cm.domain.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author andrerafaelmezzalira
 *
 */
@Entity
public class Unity extends Parameter {

	private static final long serialVersionUID = 1L;

	private Integer idUnity;

	private String name;

	private Boolean hasStorage;

	@JsonIgnore
	@ManyToOne
	private Header header;

	public void setHeader(Header header) {
		this.header = header;
	}

	public Header getHeader() {
		return header;
	}

	public void setIdUnity(Integer idUnity) {
		this.idUnity = idUnity;
	}

	public Integer getIdUnity() {
		return idUnity;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setHasStorage(Boolean hasStorage) {
		this.hasStorage = hasStorage;
	}

	public Boolean getHasStorage() {
		return hasStorage;
	}
}
