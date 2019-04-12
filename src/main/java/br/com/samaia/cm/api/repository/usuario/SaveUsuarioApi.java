package br.com.samaia.cm.api.repository.usuario;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.samaia.cm.domain.entity.Usuario;
import br.com.samaia.cm.service.domain.UsuarioService;
import br.com.samaia.cm.utils.JsonUtils;

/**
 * API que salva um usuario
 * 
 * @author andrerafaelmezzalira
 *
 */
@Path("/saveUsuario")
public class SaveUsuarioApi {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private UsuarioService service;

	@POST
	public Response saveUsuario(Usuario usuario) throws JsonProcessingException {
		log.info(" salvando usuario \n " + JsonUtils.toJson(usuario));
		service.save(usuario);
		log.info(" usuario salvo  \n " + JsonUtils.toJson(usuario));
		return Response.ok().entity(usuario).build();

	}
}
