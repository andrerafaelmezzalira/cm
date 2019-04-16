package br.com.samaia.cm.domain.repository;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.TypedQuery;

import br.com.samaia.cm.arq.domain.AbstractRepository;
import br.com.samaia.cm.domain.entity.Person;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
public class PersonRepository extends AbstractRepository<Person> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	public PersonRepository() {
		super(Person.class);
	}

	public List<Person> findByCpf(Person person) {
		TypedQuery<Person> typedQuery = entityManager.createNamedQuery(Person.GET, Person.class);
		typedQuery.setParameter("cpf", person.getCpf());
		List<Person> persons = typedQuery.getResultList();
		log.info(" valid user? " + (persons.size() != 0));
		return persons;
	}
}