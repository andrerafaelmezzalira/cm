package br.com.samaia.cm.service.domain;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.samaia.cm.arq.producer.Property;
import br.com.samaia.cm.domain.entity.Person;
import br.com.samaia.cm.domain.repository.PersonRepository;
import br.com.samaia.cm.exception.InvalidPersonException;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
@Stateless
public class PersonService {

	@Inject
	@Property("usuarioInvalido")
	private String USUARIO_INVALIDO;

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private PersonRepository repository;

	public void save(Person person) throws JsonProcessingException {
		log.info("method save insert " + String.valueOf(person.getId() == null));
		if (person.getId() == null) {
			repository.insert(person);
		} else {
			repository.update(person);
		}
	}

	public void loadPerson(Person person) throws InvalidPersonException {
		List<Person> persons = repository.findByCpf(person);
		if (!persons.isEmpty()) {
			log.info(String.valueOf(persons.get(0).getServices().size()));
			person.setServices(persons.get(0).getServices());
			person.setName(persons.get(0).getName());
		} else {
			log.severe("invalid person ");
			throw new InvalidPersonException(USUARIO_INVALIDO);
		}
	}
}