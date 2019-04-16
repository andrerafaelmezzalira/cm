package br.com.samaia.cm.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.samaia.cm.arq.convert.LocalDateTimeDeserializer;
import br.com.samaia.cm.arq.convert.LocalDateTimeSerializer;
import br.com.samaia.cm.arq.domain.AbstractEntity;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
@Entity
public class Header implements AbstractEntity<Integer> {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonIgnore
	@ManyToOne
	private Person person;

	@JsonIgnore
	@ManyToOne
	private Service service;

	@JsonIgnore
	private String login;

	@JsonIgnore
	private String password;

	@JsonIgnore
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dateTimeInit;

	@JsonIgnore
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dateTimeEnd;

	@OneToMany(mappedBy = "header", cascade = CascadeType.MERGE)
	private List<Unity> units;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public void setDateTimeEnd(LocalDateTime dateTimeEnd) {
		this.dateTimeEnd = dateTimeEnd;
	}
	
	public LocalDateTime getDateTimeEnd() {
		return dateTimeEnd;
	}
	
	public void setDateTimeInit(LocalDateTime dateTimeInit) {
		this.dateTimeInit = dateTimeInit;
	}
	
	public LocalDateTime getDateTimeInit() {
		return dateTimeInit;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Person getPerson() {
		return person;
	}
	
	public void setService(Service service) {
		this.service = service;
	}
	
	public Service getService() {
		return service;
	}
	
	public void setUnits(List<Unity> units) {
		this.units = units;
	}
	
	public List<Unity> getUnits() {
		return units;
	}
}
