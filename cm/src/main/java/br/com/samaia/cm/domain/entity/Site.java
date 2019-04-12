package br.com.samaia.cm.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.samaia.cm.arq.domain.AbstractEntity;

/**
 * Tabela Site
 * 
 * @author andrerafaelmezzalira
 *
 */
@Entity
public class Site implements AbstractEntity<Integer> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private String servico;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public String getServico() {
		return servico;
	}
}
