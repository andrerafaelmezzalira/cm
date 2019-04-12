package br.com.samaia.cm.domain.repository;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.TypedQuery;

import br.com.samaia.cm.arq.domain.AbstractRepository;
import br.com.samaia.cm.domain.entity.Header;
import br.com.samaia.cm.domain.entity.Parametro;
import br.com.samaia.cm.domain.entity.Reprocessamento;
import br.com.samaia.cm.vo.ConsultaHeaderVO;

/**
 * Acessa a tabela Header
 * 
 * @author andrerafaelmezzalira
 *
 */
public class HeaderRepository extends AbstractRepository<Header> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	public HeaderRepository() {
		super(Header.class);
	}

	public List<Header> consultar(ConsultaHeaderVO consultaHeader) {
		StringBuilder jpql = new StringBuilder("select a from Header a where a.usuario.cpfCnpj = '"
				+ consultaHeader.getCpfCnpj() + "' " + " and a.dataHoraInicio >= '" + consultaHeader.getDataHora()
				+ "' and upper(a.site.nome) = '" + consultaHeader.getSite().toUpperCase()
				+ "' and upper(a.site.servico) = '" + consultaHeader.getServico().toUpperCase() + "'");
		log.info(jpql.toString());
		TypedQuery<Header> query = entityManager.createQuery(jpql.toString(), Header.class);
		return query.getResultList();
	}

	public List<Reprocessamento> getAllParametersInvalids() {
		log.info(" rodando List<Reprocessamento> getAllParametersInvalids() ");
		TypedQuery<Reprocessamento> typedQuery = entityManager.createNamedQuery(Reprocessamento.GET_HEADER_INVALIDOS,
				Reprocessamento.class);
		return typedQuery.getResultList();
	}

	public Collection<? extends Parametro> findByParametros(Integer idHeader, List<Reprocessamento> value) {
		StringBuilder jpql = new StringBuilder("select b from Header a join a." + value.get(0).getEntity()
				+ " b where a.id = " + idHeader + " and b.id in (" + getIdParameters(value) + ")");
		log.info(jpql.toString());
		TypedQuery<? extends Parametro> query = entityManager.createQuery(jpql.toString(), Parametro.class);
		return query.getResultList();
	}

	private final String getIdParameters(final List<Reprocessamento> value) {
		final StringBuilder sb = new StringBuilder();
		for (Reprocessamento reprocessamento : value) {
			if (sb.length() != 0) {
				sb.append(",");
			}
			sb.append(reprocessamento.getParametro());
		}
		return sb.toString();
	}
}