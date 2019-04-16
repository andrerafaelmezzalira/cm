package br.com.samaia.cm.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.samaia.cm.arq.convert.LocalDateTimeDeserializer;
import br.com.samaia.cm.arq.convert.LocalDateTimeSerializer;
import br.com.samaia.cm.arq.domain.AbstractEntity;

/**
 * @author andrerafaelmezzalira
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Parameter implements AbstractEntity<Integer> {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String state;

	private String result;

	@JsonIgnore
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dateTimeInitReprocessing;

	@JsonIgnore
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dateTimeEndReprocessing;

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	public void setDateTimeEndReprocessing(LocalDateTime dateTimeEndReprocessing) {
		this.dateTimeEndReprocessing = dateTimeEndReprocessing;
	}
	
	public LocalDateTime getDateTimeEndReprocessing() {
		return dateTimeEndReprocessing;
	}
	
	public void setDateTimeInitReprocessing(LocalDateTime dateTimeInitReprocessing) {
		this.dateTimeInitReprocessing = dateTimeInitReprocessing;
	}
	
	public LocalDateTime getDateTimeInitReprocessing() {
		return dateTimeInitReprocessing;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getState() {
		return state;
	}
}
