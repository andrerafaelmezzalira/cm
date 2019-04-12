package br.com.samaia.cm.vo;

import java.io.Serializable;

/**
 * Value Object Certidão Negativa de Débitos (cnd) VO
 * 
 * @author vitoralmeida
 *
 */
public class CndVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String status;

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
}
