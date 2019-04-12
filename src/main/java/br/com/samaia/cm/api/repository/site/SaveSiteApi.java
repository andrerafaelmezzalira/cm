package br.com.samaia.cm.api.repository.site;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.samaia.cm.domain.entity.Site;
import br.com.samaia.cm.service.domain.SiteService;
import br.com.samaia.cm.utils.JsonUtils;

/**
 * API que salva um site
 * 
 * @author andrerafaelmezzalira
 *
 */
@Path("/saveSite")
public class SaveSiteApi {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private SiteService service;

	@POST
	public Response saveSite(Site site) throws JsonProcessingException {

		log.info(" salvando site \n " + JsonUtils.toJson(site));

		service.save(site);
		log.info(" site salvo  \n " + JsonUtils.toJson(site));
		return Response.ok().entity(site).build();

	}
}
