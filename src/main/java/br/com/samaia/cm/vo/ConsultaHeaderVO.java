package br.com.samaia.cm.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.samaia.cm.arq.convert.LocalDateTimeDeserializer;
import br.com.samaia.cm.arq.convert.LocalDateTimeSerializer;

/**
 * Value Object que representa os parametros de entrada da api
 * 
 * @author andrerafaelmezzalira
 *
 */
public class ConsultaHeaderVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cpfCnpj;

	private String site;

	private String servico;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dataHora;

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}
	
	public String getServico() {
		return servico;
	}
	
	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}
}
