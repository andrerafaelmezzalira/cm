package br.com.samaia.cm.domain.repository;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.TypedQuery;

import br.com.samaia.cm.arq.domain.AbstractRepository;
import br.com.samaia.cm.domain.entity.UsuarioCelesc;

/**
 * Acessa a tabela UsuarioCelesc
 * 
 * @author andrerafaelmezzalira
 *
 */
public class UsuarioCelescRepository extends AbstractRepository<UsuarioCelesc> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	public UsuarioCelescRepository() {
		super(UsuarioCelesc.class);
	}

	public List<UsuarioCelesc> isUsuarioCelescValido(String login, String senha, Integer idUsuario) {
		TypedQuery<UsuarioCelesc> typedQuery = entityManager.createNamedQuery(UsuarioCelesc.VALIDAR_USUARIO_CELESC,
				UsuarioCelesc.class);
		typedQuery.setParameter("login", login);
		typedQuery.setParameter("senha", senha);
		typedQuery.setParameter("usuario", idUsuario);
		List<UsuarioCelesc> usuariosCelesc = typedQuery.getResultList();
		log.info(" usuarioCelesc válido? " + usuariosCelesc.size());
		return usuariosCelesc;
	}
}