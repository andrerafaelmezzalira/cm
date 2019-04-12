package br.com.samaia.cm.api.robo;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.samaia.cm.constants.Constants;
import br.com.samaia.cm.exception.CampoObrigatorioException;
import br.com.samaia.cm.exception.RoboException;
import br.com.samaia.cm.exception.UsuarioInvalidoException;
import br.com.samaia.cm.service.robo.RoboApiService;
import br.com.samaia.cm.utils.JsonUtils;
import br.com.samaia.cm.vo.HeaderVO;

/**
 * API para processamento de sites ENEL
 * 
 * @author andrerafaelmezzalira
 *
 */
@Path("/enel")
public class EnelRoboApi {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private RoboApiService service;

	@POST
	@Path("/titularidade")
	public Response processarTitularidade(HeaderVO header, @Context HttpServletRequest request)
			throws JsonProcessingException {

		try {
			log.info(" inicio do processamento ENEL Titularidade da API \n" + JsonUtils.toJson(header));
			log.info(" seta o ip: " + request.getRemoteAddr());
			header.setIp(request.getRemoteAddr());
			header.setServico(Constants.TITULARIDADE);
			header.setSite(Constants.ENEL);
			service.call(header);
			log.info(" fim do processamento ENEL Titularidade da API \n " + JsonUtils.toJson(header));
			return Response.ok().entity(JsonUtils.toJson(header)).build();
		} catch (CampoObrigatorioException | RoboException | UsuarioInvalidoException e) {
			log.severe("problema robo ENEL Titularidade  da api " + e.getMessage());
			return Response.serverError().entity(JsonUtils.toJson(e.getMessage())).build();
		}
	}

	@POST
	@Path("/debito")
	public Response processarDebito(HeaderVO header, @Context HttpServletRequest request)
			throws JsonProcessingException {

		try {
			log.info(" inicio do processamento ENEL Debito da API \n" + JsonUtils.toJson(header));
			log.info(" seta o ip: " + request.getRemoteAddr());
			header.setIp(request.getRemoteAddr());
			header.setServico(Constants.DEBITO);
			header.setSite(Constants.ENEL);
			service.call(header);
			log.info(" fim do processamento ENEL Debito da API \n " + JsonUtils.toJson(header));
			return Response.ok().entity(JsonUtils.toJson(header)).build();
		} catch (CampoObrigatorioException | RoboException | UsuarioInvalidoException e) {
			log.severe("problema robo ENEL Debito da api " + e.getMessage());
			return Response.serverError().entity(JsonUtils.toJson(e.getMessage())).build();
		}
	}
	
	@POST
	@Path("/dam")
	public Response processarDam(HeaderVO header, @Context HttpServletRequest request)
			throws JsonProcessingException {

		try {
			log.info(" inicio do processamento ENEL Dam da API \n" + JsonUtils.toJson(header));
			log.info(" seta o ip: " + request.getRemoteAddr());
			header.setIp(request.getRemoteAddr());
			header.setServico(Constants.DAM);
			header.setSite(Constants.ENEL);
			service.call(header);
			log.info(" fim do processamento ENEL Dam da API \n " + JsonUtils.toJson(header));
			return Response.ok().entity(JsonUtils.toJson(header)).build();
		} catch (CampoObrigatorioException | RoboException | UsuarioInvalidoException e) {
			log.severe("problema robo ENEL Dam da api " + e.getMessage());
			return Response.serverError().entity(JsonUtils.toJson(e.getMessage())).build();
		}
	}

}
