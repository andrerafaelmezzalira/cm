package br.com.samaia.cm.client.repository.person;

import br.com.samaia.cm.client.AbstractClient;
import br.com.samaia.cm.domain.entity.Person;

/**
 * @author andrerafaelmezzalira
 *
 */
public class AddPersonClient extends AbstractClient<Person> {

	private static final String URL = "http://localhost:8080/cm/api/person";

	public static void main(String[] args) {
		AddPersonClient client = new AddPersonClient();
		client.log(client.post(URL, client.getDataSource()));
	}

	@Override
	protected Person getDataSource() {
		return PersonDataSource.getObjectDataSource();

	}

}
