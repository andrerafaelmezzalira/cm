package br.com.samaia.cm.arq.domain;

import java.io.Serializable;

/**
 * Representa uma entidade do banco de dados
 * 
 * @author andrerafaelmezzalira
 *
 * @param <T> tipo do id da tabela (Long, Integer ...)
 */
public interface AbstractEntity<T> extends Serializable {

	T getId();

	void setId(T id);

}
