package br.com.samaia.cm.api;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.samaia.cm.domain.entity.Service;
import br.com.samaia.cm.service.domain.ServiceService;
import br.com.samaia.cm.utils.JsonUtils;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
@Path("/service")
public class ServiceApi {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private ServiceService service;

	@POST
	public Response saveSite(Service site) throws JsonProcessingException {

		log.info(" salvando site \n " + JsonUtils.toJson(site));

		service.save(site);
		log.info(" site salvo  \n " + JsonUtils.toJson(site));
		return Response.ok().entity(site).build();

	}
}
