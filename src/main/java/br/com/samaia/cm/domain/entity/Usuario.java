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
 * Tabela Usuario
 * 
 * @author andrerafaelmezzalira
 *
 */
@Entity
@NamedQueries({
		@NamedQuery(name = Usuario.VALIDAR_USUARIO, query = "select a from Usuario a join a.sites s where a.cpfCnpj = :cpfCnpj and s.nome = :site and s.servico = :servico"),
		@NamedQuery(name = Usuario.CARREGAR_USUARIO, query = "select a from Usuario a where a.cpfCnpj = :cpfCnpj") })
public class Usuario implements AbstractEntity<Integer> {

	private static final long serialVersionUID = 1L;

	public static final String VALIDAR_USUARIO = "Usuario.isUsuarioValido";
	public static final String CARREGAR_USUARIO = "Usuario.carregarUsuario";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String cpfCnpj;

	private String nome;

	@ManyToMany
	private List<Site> sites;

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

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	public List<Site> getSites() {
		return sites;
	}
}
