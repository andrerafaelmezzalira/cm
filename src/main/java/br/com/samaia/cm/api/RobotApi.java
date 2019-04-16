package br.com.samaia.cm.api;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.samaia.cm.domain.entity.Header;
import br.com.samaia.cm.exception.InvalidPersonException;
import br.com.samaia.cm.exception.RequiredFieldException;
import br.com.samaia.cm.exception.RobotException;
import br.com.samaia.cm.service.robot.FindRobotApiService;
import br.com.samaia.cm.service.robot.RobotApiService;
import br.com.samaia.cm.utils.JsonUtils;
import br.com.samaia.cm.vo.FindHeaderVO;
import br.com.samaia.cm.vo.HeaderVO;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
@Path("/robot")
public class RobotApi {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private RobotApiService robotApiService;

	@Inject
	private FindRobotApiService findRobotApiSservice;

	@POST
	public Response process(HeaderVO headerVO) throws JsonProcessingException {

		try {
			log.info(" init API \n" + JsonUtils.toJson(headerVO));
			robotApiService.call(headerVO);
			log.info(" end API \n " + JsonUtils.toJson(headerVO));
			return Response.ok().entity(JsonUtils.toJson(headerVO)).build();
		} catch (RequiredFieldException | RobotException | InvalidPersonException e) {
			log.severe("problem robot api " + e.getMessage());
			return Response.serverError().entity(JsonUtils.toJson(e.getMessage())).build();
		}
	}

	@GET
	public Response find(@QueryParam("header") String json) throws JsonProcessingException {
		try {
			FindHeaderVO consultaHeader = JsonUtils.fromJson(json, FindHeaderVO.class);
			log.info(" inicio da consulta da API \n " + JsonUtils.toJson(consultaHeader));
			List<Header> headers = findRobotApiSservice.find(consultaHeader);
			log.info(" fim do processamento da API : tamanho do retorno da lista de headers:" + headers.size());
			return Response.ok().entity(JsonUtils.toJson(headers)).build();
		} catch (RequiredFieldException | InvalidPersonException | IOException e) {
			log.severe("problema consulta robo api" + e.getMessage());
			return Response.serverError().entity(JsonUtils.toJson(e.getMessage())).build();
		}
	}
}
