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
 * Tabela Parametro
 * 
 * @author andrerafaelmezzalira
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Parametro implements AbstractEntity<Integer> {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String codigoImovel;

	private String numeroContrato;

	private String pdfs;
	
	private String resultado;

	private String estado;

	@JsonIgnore
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dataHoraInicioReprocessamento;

	@JsonIgnore
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dataHoraFimReprocessamento;

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	public void setDataHoraFimReprocessamento(LocalDateTime dataHoraFimReprocessamento) {
		this.dataHoraFimReprocessamento = dataHoraFimReprocessamento;
	}

	public LocalDateTime getDataHoraFimReprocessamento() {
		return dataHoraFimReprocessamento;
	}

	public void setDataHoraInicioReprocessamento(LocalDateTime dataHoraInicioReprocessamento) {
		this.dataHoraInicioReprocessamento = dataHoraInicioReprocessamento;
	}

	public LocalDateTime getDataHoraInicioReprocessamento() {
		return dataHoraInicioReprocessamento;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

	public void setPdfs(String pdfs) {
		this.pdfs = pdfs;
	}

	public String getPdfs() {
		return pdfs;
	}

	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	public String getCodigoImovel() {
		return codigoImovel;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getResultado() {
		return resultado;
	}

}
