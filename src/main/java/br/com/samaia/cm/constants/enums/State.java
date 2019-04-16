package br.com.samaia.cm.constants.enums;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum State {

	PROCESSED(1, "Processado"), //
	REPROCESS(2, "Reprocessar");

	private Integer id;

	private String state;

	private State(Integer id, String state) {
		this.id = id;
		this.state = state;
	}

	@JsonCreator
	public static State fromObject(Map<String, Object> data) {
		return State.getByState(data.get("state").toString());
	}

	private static State getByState(String str) {
		State state = null;
		if (str != null) {
			for (State iterator : State.values()) {
				if (iterator.getState().equalsIgnoreCase(str)) {
					state = iterator;
					break;
				}
			}
		}
		return state;
	}

	public Integer getId() {
		return id;
	}

	public String getState() {
		return state;
	}
}
