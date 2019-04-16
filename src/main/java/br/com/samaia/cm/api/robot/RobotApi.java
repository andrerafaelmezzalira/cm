package br.com.samaia.cm.api.robot;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.samaia.cm.exception.InvalidPersonException;
import br.com.samaia.cm.exception.RequiredFieldException;
import br.com.samaia.cm.exception.RobotException;
import br.com.samaia.cm.service.robot.RobotApiService;
import br.com.samaia.cm.utils.JsonUtils;
import br.com.samaia.cm.vo.HeaderVO;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
@Path("/process")
public class RobotApi {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private RobotApiService service;

	@POST
	public Response processar(HeaderVO headerVO) throws JsonProcessingException {

		try {
			log.info(" init API \n" + JsonUtils.toJson(headerVO));
			service.call(headerVO);
			log.info(" end API \n " + JsonUtils.toJson(headerVO));
			return Response.ok().entity(JsonUtils.toJson(headerVO)).build();
		} catch (RequiredFieldException | RobotException | InvalidPersonException e) {
			log.severe("problem robot api " + e.getMessage());
			return Response.serverError().entity(JsonUtils.toJson(e.getMessage())).build();
		}
	}
}
