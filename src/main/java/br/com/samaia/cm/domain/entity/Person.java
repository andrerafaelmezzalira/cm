package br.com.samaia.cm.domain.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import br.com.samaia.cm.arq.domain.AbstractEntity;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
@Entity
@NamedQueries({ @NamedQuery(name = Person.GET, query = "select a from Person a where a.cpf = :cpf") })
public class Person implements AbstractEntity<Integer> {

	private static final long serialVersionUID = 1L;

	public static final String GET = "Person.get";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String cpf;

	private String name;

	@ManyToMany
	private List<Service> services;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public List<Service> getServices() {
		return services;
	}
}
