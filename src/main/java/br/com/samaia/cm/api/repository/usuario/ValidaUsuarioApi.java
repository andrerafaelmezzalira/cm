package br.com.samaia.cm.api.repository.usuario;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.samaia.cm.domain.entity.Usuario;
import br.com.samaia.cm.exception.UsuarioInvalidoException;
import br.com.samaia.cm.service.domain.UsuarioService;
import br.com.samaia.cm.utils.JsonUtils;

/**
 * API que valida um usuario
 * 
 * @author andrerafaelmezzalira
 *
 */
@Path("/validarUsuario")
public class ValidaUsuarioApi {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private UsuarioService service;

	@GET
	public Response validarUsuario(@QueryParam("usuario") String json) throws JsonProcessingException {

		try {
			Usuario usuario = JsonUtils.fromJson(json, Usuario.class);

			log.info(" validando usuario \n " + JsonUtils.toJson(usuario));
			service.carregarUsuario(usuario);
			log.info(" usuario validado  \n " + JsonUtils.toJson(usuario));
			return Response.ok().entity(usuario).build();
		} catch (UsuarioInvalidoException | IOException e) {
			log.severe(e.getMessage());
			return Response.serverError().entity(JsonUtils.toJson(e.getMessage())).build();
		}
	}
}
