package br.com.samaia.cm.domain.repository;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.TypedQuery;

import br.com.samaia.cm.arq.domain.AbstractRepository;
import br.com.samaia.cm.domain.entity.Usuario;

/**
 * Acessa a tabela Usuario
 * 
 * @author andrerafaelmezzalira
 *
 */
public class UsuarioRepository extends AbstractRepository<Usuario> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	public UsuarioRepository() {
		super(Usuario.class);
	}

	public List<Usuario> isUsuarioValido(String cpfCnpj, String site, String servico) {
		TypedQuery<Usuario> typedQuery = entityManager.createNamedQuery(Usuario.VALIDAR_USUARIO, Usuario.class);
		typedQuery.setParameter("cpfCnpj", cpfCnpj);
		typedQuery.setParameter("site", site);
		typedQuery.setParameter("servico", servico);
		List<Usuario> usuarios = typedQuery.getResultList();
		log.info(" usuario válido? " + usuarios.size());
		return usuarios;
	}

	public List<Usuario> findByCpfCnpj(Usuario usuario) {
		TypedQuery<Usuario> typedQuery = entityManager.createNamedQuery(Usuario.CARREGAR_USUARIO, Usuario.class);
		typedQuery.setParameter("cpfCnpj", usuario.getCpfCnpj());
		List<Usuario> usuarios = typedQuery.getResultList();
		log.info(" usuario válido? " + usuarios.size());
		return usuarios;
	}
}