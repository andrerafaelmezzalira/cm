package br.com.samaia.cm.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.samaia.cm.arq.convert.LocalDateTimeDeserializer;
import br.com.samaia.cm.arq.convert.LocalDateTimeSerializer;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
public class FindHeaderVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cpf;

	private String service;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dateTime;

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	
	public void setService(String service) {
		this.service = service;
	}
	
	public String getService() {
		return service;
	}
	
}
