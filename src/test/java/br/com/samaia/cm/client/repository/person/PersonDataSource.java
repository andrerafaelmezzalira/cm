package br.com.samaia.cm.client.repository.person;

import br.com.samaia.cm.domain.entity.Person;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
public class PersonDataSource {

	public static final Person getObjectDataSource() {
		Person usuario = new Person();
		usuario.setCpf("66666666666669");

		return usuario;
	}
}
