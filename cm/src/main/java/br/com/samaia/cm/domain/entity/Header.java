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
 * Tabela Header
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
	private Usuario usuario;

	@JsonIgnore
	@ManyToOne
	private Site site;

	@JsonIgnore
	private String servico;

	@JsonIgnore
	private String ip;

	private String parcelas;

	@JsonIgnore
	@ManyToOne
	private UsuarioCelesc usuarioCelesc;

	@JsonIgnore
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dataHoraInicio;

	@JsonIgnore
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dataHoraFim;

	@OneToMany(mappedBy = "header", cascade = CascadeType.MERGE)
	private List<Enel> enels;

	@OneToMany(mappedBy = "header", cascade = CascadeType.MERGE)
	private List<Celesc> celescs;

	@OneToMany(mappedBy = "header", cascade = CascadeType.MERGE)
	private List<Goiania> goianias;

	@OneToMany(mappedBy = "header", cascade = CascadeType.MERGE)
	private List<BombeiroPernambuco> bombeirosPernambuco;

	@OneToMany(mappedBy = "header", cascade = CascadeType.MERGE)
	private List<PatrimonioDeTodos> patrimoniosDeTodos;

	@OneToMany(mappedBy = "header", cascade = CascadeType.MERGE)
	private List<Compesa> compesas;

	@OneToMany(mappedBy = "header", cascade = CascadeType.MERGE)
	private List<FazendaBrasilia> fazendasBrasilia;

	@OneToMany(mappedBy = "header", cascade = CascadeType.MERGE)
	private List<Celpe> celpes;
	
	@OneToMany(mappedBy = "header", cascade = CascadeType.MERGE)
	private List<PortalFinancas> portalFinancas;
	
	@OneToMany(mappedBy = "header", cascade = CascadeType.MERGE)
	private List<Saneago> saneagos;
	
	@OneToMany(mappedBy = "header", cascade = CascadeType.MERGE)
	private List<Caesb> caesbs;
	
	@OneToMany(mappedBy = "header", cascade = CascadeType.MERGE)
	private List<Casan> casans;
	
	@OneToMany(mappedBy = "header", cascade = CascadeType.MERGE)
	private List<Floripa> floripas;
	
	@OneToMany(mappedBy = "header", cascade = CascadeType.MERGE)
	private List<Criciuma> criciumas;
	
	@OneToMany(mappedBy = "header", cascade = CascadeType.MERGE)
	private List<SaoJose> saoJoses;
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsuarioCelesc(UsuarioCelesc usuarioCelesc) {
		this.usuarioCelesc = usuarioCelesc;
	}

	public UsuarioCelesc getUsuarioCelesc() {
		return usuarioCelesc;
	}

	public void setParcelas(String parcelas) {
		this.parcelas = parcelas;
	}

	public String getParcelas() {
		return parcelas;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public String getServico() {
		return servico;
	}

	public void setDataHoraFim(LocalDateTime dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	public LocalDateTime getDataHoraFim() {
		return dataHoraFim;
	}

	public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public LocalDateTime getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}

	public void setEnels(List<Enel> enels) {
		this.enels = enels;
	}

	public List<Enel> getEnels() {
		return enels;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Site getSite() {
		return site;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setCelescs(List<Celesc> celescs) {
		this.celescs = celescs;
	}

	public List<Celesc> getCelescs() {
		return celescs;
	}

	public void setGoianias(List<Goiania> goianias) {
		this.goianias = goianias;
	}

	public List<Goiania> getGoianias() {
		return goianias;
	}

	public void setBombeirosPernambuco(List<BombeiroPernambuco> bombeirosPernambuco) {
		this.bombeirosPernambuco = bombeirosPernambuco;
	}

	public List<BombeiroPernambuco> getBombeirosPernambuco() {
		return bombeirosPernambuco;
	}

	public void setPatrimoniosDeTodos(List<PatrimonioDeTodos> patrimoniosDeTodos) {
		this.patrimoniosDeTodos = patrimoniosDeTodos;
	}

	public List<PatrimonioDeTodos> getPatrimoniosDeTodos() {
		return patrimoniosDeTodos;
	}

	public void setCompesas(List<Compesa> compesas) {
		this.compesas = compesas;
	}

	public List<Compesa> getCompesas() {
		return compesas;
	}

	public List<FazendaBrasilia> getFazendasBrasilia() {
		return fazendasBrasilia;
	}

	public void setFazendasBrasilia(List<FazendaBrasilia> fazendasBrasilia) {
		this.fazendasBrasilia = fazendasBrasilia;
	}

	public List<PortalFinancas> getPortalFinancas() {
		return portalFinancas;
	}

	public void setPortalFinancas(List<PortalFinancas> portalFinancas) {
		this.portalFinancas = portalFinancas;
	}

	public List<Celpe> getCelpes() {
		return celpes;
	}

	public void setCelpes(List<Celpe> celpes) {
		this.celpes = celpes;
	}

	public List<Saneago> getSaneagos() {
		return saneagos;
	}

	public void setSaneagos(List<Saneago> saneagos) {
		this.saneagos = saneagos;
	}

	public List<Caesb> getCaesbs() {
		return caesbs;
	}

	public void setCaesbs(List<Caesb> caesbs) {
		this.caesbs = caesbs;
	}

	public List<Casan> getCasans() {
		return casans;
	}

	public void setCasans(List<Casan> casans) {
		this.casans = casans;
	}
	
	public List<Floripa> getFloripas() {
		return floripas;
	}
	
	public void setFloripas(List<Floripa> floripas) {
		this.floripas = floripas;
	}

	public List<Criciuma> getCriciumas() {
		return criciumas;
	}

	public void setCriciumas(List<Criciuma> criciumas) {
		this.criciumas = criciumas;
	}

	public List<SaoJose> getSaoJoses() {
		return saoJoses;
	}

	public void setSaoJoses(List<SaoJose> saoJoses) {
		this.saoJoses = saoJoses;
	}
	
	

	
}
