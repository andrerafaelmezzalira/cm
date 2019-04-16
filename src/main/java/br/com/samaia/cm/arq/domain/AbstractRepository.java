package br.com.samaia.cm.arq.domain;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.samaia.cm.utils.JsonUtils;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
public class AbstractRepository<T extends AbstractEntity<?>> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	protected EntityManager entityManager;

	private Class<T> entityClass;

	public AbstractRepository(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public T insert(final T entity) throws JsonProcessingException {
		log.info("insert " + entity.getClass().getName());
		entityManager.persist(entity);
		entityManager.flush();
		log.info("result insert \n" + JsonUtils.toJson(entity));
		return entity;
	}

	public T update(final T entity) throws JsonProcessingException {
		log.info("update " + entity.getClass().getName() + "\n " + JsonUtils.toJson(entity));
		entityManager.merge(entity);
		entityManager.flush();
		log.info("result update \n" + JsonUtils.toJson(entity));
		return entity;
	}

	public T delete(final T entity) throws JsonProcessingException {
		log.info("delete " + entity.getClass().getName() + " \n " + JsonUtils.toJson(entity));
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
		entityManager.flush();
		log.info("result delete \n" + JsonUtils.toJson(entity));
		return entity;
	}

	public T findById(final Serializable id) {
		log.info("findById " + entityClass.getName() + " id " + id);
		final T t = entityManager.find(entityClass, id);
		log.info("result findById \n" + t.toString());
		return t;
	}

	public List<T> listAll() {
		log.info("listAll " + entityClass.getName());
		final CriteriaQuery<T> q = entityManager.getCriteriaBuilder().createQuery(entityClass);
		final TypedQuery<T> tq = entityManager.createQuery(q.select(q.from(entityClass)));
		final List<T> list = tq.getResultList();
		log.info("result listAll \n");
		int i = 0;
		for (final T t : list) {
			++i;
			log.info(i + " - ");
			log.info(t.toString() + "\n");
		}
		return list;
	}
}
