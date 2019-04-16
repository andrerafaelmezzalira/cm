package br.com.samaia.cm.api.repository.person;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.samaia.cm.domain.entity.Person;
import br.com.samaia.cm.service.domain.PersonService;
import br.com.samaia.cm.utils.JsonUtils;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
@Path("/saveUsuario")
public class AddPersonApi {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private PersonService service;

	@POST
	public Response saveUsuario(Person person) throws JsonProcessingException {
		log.info(" salvando usuario \n " + JsonUtils.toJson(person));
		service.save(person);
		log.info(" usuario salvo  \n " + JsonUtils.toJson(person));
		return Response.ok().entity(person).build();

	}
}
