package br.com.samaia.cm.service.domain;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.samaia.cm.arq.producer.Property;
import br.com.samaia.cm.domain.entity.Usuario;
import br.com.samaia.cm.domain.repository.UsuarioRepository;
import br.com.samaia.cm.exception.UsuarioInvalidoException;

/**
 * Operações na tabela Usuario
 * 
 * @author andrerafaelmezzalira
 *
 */
@Stateless
public class UsuarioService {

	@Inject
	@Property("usuarioInvalido")
	private String USUARIO_INVALIDO;

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private UsuarioRepository repository;

	public Usuario isUsuarioValido(String cpfCnpj, String site, String servico) {
		log.info("método isUsuarioValido cpfCnpj " + cpfCnpj + " site " + site + " servico " + servico);
		List<Usuario> usuarios = repository.isUsuarioValido(cpfCnpj, site, servico);
		return !usuarios.isEmpty() ? usuarios.get(0) : null;
	}

	public void save(Usuario usuario) throws JsonProcessingException {
		log.info("método save(Usuario usuario) - insert " + String.valueOf(usuario.getId() == null));
		if (usuario.getId() == null) {
			repository.insert(usuario);
		} else {
			repository.update(usuario);
		}
	}

	public void carregarUsuario(Usuario usuario) throws UsuarioInvalidoException {
		log.info("método carregarUsuario(Usuario usuario) cpfCnpj " + usuario.getCpfCnpj());
		List<Usuario> usuarios = repository.findByCpfCnpj(usuario);
		if (!usuarios.isEmpty()) {
			log.info(String.valueOf(usuarios.get(0).getSites().size()));
			usuario.setSites(usuarios.get(0).getSites());
			usuario.setNome(usuarios.get(0).getNome());
		} else {
			log.info("usuário inválido ");
			throw new UsuarioInvalidoException(USUARIO_INVALIDO);
		}
	}
}