package br.com.samaia.cm.arq.domain;

import java.io.Serializable;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
public interface AbstractEntity<T> extends Serializable {

	T getId();

	void setId(T id);

}
