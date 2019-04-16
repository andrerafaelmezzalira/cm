package br.com.samaia.cm.domain.repository;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.TypedQuery;

import br.com.samaia.cm.arq.domain.AbstractRepository;
import br.com.samaia.cm.domain.entity.Header;
import br.com.samaia.cm.domain.entity.Parameter;
import br.com.samaia.cm.domain.entity.Reprocessing;
import br.com.samaia.cm.vo.FindHeaderVO;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
public class HeaderRepository extends AbstractRepository<Header> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	public HeaderRepository() {
		super(Header.class);
	}

	public List<Header> consultar(FindHeaderVO consultaHeader) {
		StringBuilder jpql = new StringBuilder("select a from Header a where a.person.cpf = '" + consultaHeader.getCpf()
				+ "' " + " and a.dateTimeInit >= '" + consultaHeader.getDateTime() + "' and upper(a.service.name) = '"
				+ consultaHeader.getService().toUpperCase() + "' ");
		log.info(jpql.toString());
		TypedQuery<Header> query = entityManager.createQuery(jpql.toString(), Header.class);
		return query.getResultList();
	}

	public List<Reprocessing> get() {
		TypedQuery<Reprocessing> typedQuery = entityManager.createNamedQuery(Reprocessing.GET, Reprocessing.class);
		return typedQuery.getResultList();
	}

	public Collection<? extends Parameter> findByParameter(Integer idHeader, List<Reprocessing> value) {
		StringBuilder jpql = new StringBuilder("select b from Header a join a." + value.get(0).getEntity()
				+ " b where a.id = " + idHeader + " and b.id in (" + getIdParameters(value) + ")");
		log.info(jpql.toString());
		TypedQuery<? extends Parameter> query = entityManager.createQuery(jpql.toString(), Parameter.class);
		return query.getResultList();
	}

	private final String getIdParameters(final List<Reprocessing> value) {
		final StringBuilder sb = new StringBuilder();
		for (Reprocessing reprocessing : value) {
			if (sb.length() != 0) {
				sb.append(",");
			}
			sb.append(reprocessing.getParameter());
		}
		return sb.toString();
	}
}