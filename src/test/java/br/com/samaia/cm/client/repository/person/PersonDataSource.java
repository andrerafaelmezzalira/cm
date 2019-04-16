package br.com.samaia.cm.client.repository.person;

import br.com.samaia.cm.domain.entity.Person;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
public class PersonDataSource {

	public static final Person getObjectDataSource() {
		Person person = new Person();
		person.setCpf("00000000000");
		person.setName("Default");
		return person;
	}
}
