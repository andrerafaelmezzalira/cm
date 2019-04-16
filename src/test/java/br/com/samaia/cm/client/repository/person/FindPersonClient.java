package br.com.samaia.cm.client.repository.person;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.samaia.cm.client.AbstractClient;
import br.com.samaia.cm.domain.entity.Person;
import br.com.samaia.cm.utils.JsonUtils;

/**
 * @author andrerafaelmezzalira
 *
 */
public class FindPersonClient extends AbstractClient<Person> {

	private static final String URL_VALIDA_USUARIO = "http://localhost:8080/cm/api/validarUsuario";

	public static void main(String[] args) throws UnsupportedEncodingException, JsonProcessingException {
		FindPersonClient client = new FindPersonClient();
		client.log(client.get(URL_VALIDA_USUARIO, "?usuario=" + URLEncoder.encode(JsonUtils.toJson(client.getDataSource()), "UTF-8")));
	}

	@Override
	protected Person getDataSource() {
		return PersonDataSource.getObjectDataSource();

	}

}
