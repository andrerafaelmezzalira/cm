package br.com.samaia.cm.arq.producer;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Classe que realiza a injecao do entityManager
 * 
 * @author andrerafaelmezzalira
 *
 */
public class EntityManagerProducer {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@PersistenceContext
	private EntityManager entityManager;

	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		log.info("instance manager request scoped create");
		return entityManager;
	}
}