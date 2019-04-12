package br.com.samaia.cm.constants.enums;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Representa o estado do parametro apos processado.
 * 
 * @author andrerafaelmezzalira
 *
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Estado {

	PROCESSADO(1, "Processado"), //
	REPROCESSAR(2, "Reprocessar");

	private Integer id;

	private String mensagem;

	private Estado(Integer id, String mensagem) {
		this.id = id;
		this.mensagem = mensagem;
	}

	@JsonCreator
	public static Estado fromObject(Map<String, Object> data) {
		return Estado.getByMensagem(data.get("mensagem").toString());
	}

	private static Estado getByMensagem(String mensagem) {
		Estado status = null;
		if (mensagem != null) {
			for (Estado iterator : Estado.values()) {
				if (iterator.getMensagem().equalsIgnoreCase(mensagem)) {
					status = iterator;
					break;
				}
			}
		}
		return status;
	}

	public Integer getId() {
		return id;
	}

	public String getMensagem() {
		return mensagem;
	}
}
