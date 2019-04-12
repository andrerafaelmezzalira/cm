package br.com.samaia.cm.vo;

import java.io.Serializable;

/**
 * Value Object que representa os parametros de entrada da Conferencia de Débito
 * 
 * @author ricardoschmidt
 *
 */
public class DebitoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String status;
	private String faturaAtrasada;
	private String totalFatura;
	private String totalFaturaAtrasada;
	private String valorDebito;
	private String valorDebitoAtrasado;
	
	public void setValorDebitoAtrasado(String valorDebitoAtrasado) {
		this.valorDebitoAtrasado = valorDebitoAtrasado;
	}
	
	public String getValorDebitoAtrasado() {
		return valorDebitoAtrasado;
	}	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFaturaAtrasada() {
		return faturaAtrasada;
	}

	public void setFaturaAtrasada(String faturaAtrasada) {
		this.faturaAtrasada = faturaAtrasada;
	}

	public String getTotalFatura() {
		return totalFatura;
	}

	public void setTotalFatura(String totalFatura) {
		this.totalFatura = totalFatura;
	}

	public String getTotalFaturaAtrasada() {
		return totalFaturaAtrasada;
	}

	public void setTotalFaturaAtrasada(String totalFaturaAtrasada) {
		this.totalFaturaAtrasada = totalFaturaAtrasada;
	}

	public String getValorDebito() {
		return valorDebito;
	}

	public void setValorDebito(String totalDebito) {
		this.valorDebito = totalDebito;
	}

}
