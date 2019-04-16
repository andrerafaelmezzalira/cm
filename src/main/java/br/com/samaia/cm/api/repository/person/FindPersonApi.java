package br.com.samaia.cm.api.repository.person;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.samaia.cm.domain.entity.Person;
import br.com.samaia.cm.exception.InvalidPersonException;
import br.com.samaia.cm.service.domain.PersonService;
import br.com.samaia.cm.utils.JsonUtils;

/**
 * API que valida um usuario
 * 
 * @author andrerafaelmezzalira
 *
 */
@Path("/validarUsuario")
public class FindPersonApi {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private PersonService service;

	@GET
	public Response validarUsuario(@QueryParam("usuario") String json) throws JsonProcessingException {

		try {
			Person usuario = JsonUtils.fromJson(json, Person.class);

			log.info(" validando usuario \n " + JsonUtils.toJson(usuario));
			service.loadPerson(usuario);
			log.info(" usuario validado  \n " + JsonUtils.toJson(usuario));
			return Response.ok().entity(usuario).build();
		} catch (InvalidPersonException | IOException e) {
			log.severe(e.getMessage());
			return Response.serverError().entity(JsonUtils.toJson(e.getMessage())).build();
		}
	}
}
