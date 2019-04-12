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
 * API para processamento de sites São José
 * 
 * @author ricardoschmidt
 *
 */
@Path("/saoJose")
public class SaoJoseRoboApi {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private RoboApiService service;

	@POST
	@Path("/cnd")
	public Response processarCnd(HeaderVO header, @Context HttpServletRequest request)
			throws JsonProcessingException {

		try {
			log.info(" inicio do processamento São José CND da API \n" + JsonUtils.toJson(header));
			log.info(" seta o ip: " + request.getRemoteAddr());
			header.setIp(request.getRemoteAddr());
			header.setServico(Constants.CND);
			header.setSite(Constants.SAO_JOSE);
			service.call(header);
			log.info(" fim do processamento São José CND da API \n " + JsonUtils.toJson(header));
			return Response.ok().entity(JsonUtils.toJson(header)).build();
		} catch (CampoObrigatorioException | RoboException | UsuarioInvalidoException e) {
			log.severe("problema robo São José CND da api " + e.getMessage());
			return Response.serverError().entity(JsonUtils.toJson(e.getMessage())).build();
		}
	}
	
	@POST
	@Path("/debito")
	public Response processarDebito(HeaderVO header, @Context HttpServletRequest request)
			throws JsonProcessingException {
		
		try {
			log.info(" inicio do processamento São José debito da API \n" + JsonUtils.toJson(header));
			log.info(" seta o ip: " + request.getRemoteAddr());
			header.setIp(request.getRemoteAddr());
			header.setServico(Constants.DEBITO);
			header.setSite(Constants.SAO_JOSE);
			service.call(header);
			log.info(" fim do processamento São José debito da API \n " + JsonUtils.toJson(header));
			return Response.ok().entity(JsonUtils.toJson(header)).build();
		} catch (CampoObrigatorioException | RoboException | UsuarioInvalidoException e) {
			log.severe("problema robo São José debito da api " + e.getMessage());
			return Response.serverError().entity(JsonUtils.toJson(e.getMessage())).build();
		}
	}
	
	@POST
	@Path("/dam")
	public Response processarDam(HeaderVO header, @Context HttpServletRequest request)
			throws JsonProcessingException {
		
		try {
			log.info(" inicio do processamento São José dam da API \n" + JsonUtils.toJson(header));
			log.info(" seta o ip: " + request.getRemoteAddr());
			header.setIp(request.getRemoteAddr());
			header.setServico(Constants.DAM);
			header.setSite(Constants.SAO_JOSE);
			service.call(header);
			log.info(" fim do processamento São José DAM da API \n " + JsonUtils.toJson(header));
			return Response.ok().entity(JsonUtils.toJson(header)).build();
		} catch (CampoObrigatorioException | RoboException | UsuarioInvalidoException e) {
			log.severe("problema robo São José DAM da api " + e.getMessage());
			return Response.serverError().entity(JsonUtils.toJson(e.getMessage())).build();
		}
	}
}
