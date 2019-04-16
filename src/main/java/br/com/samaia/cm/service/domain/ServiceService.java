package br.com.samaia.cm.service.domain;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.samaia.cm.domain.entity.Service;
import br.com.samaia.cm.domain.repository.ServiceRepository;

/**
 * Operações na tabela Site
 * 
 * @author andrerafaelmezzalira
 *
 */
@Stateless
public class ServiceService {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private ServiceRepository repository;

	public void save(Service site) throws JsonProcessingException {
		log.info("método save(Site site) - insert " + String.valueOf(site.getId() == null));
		if (site.getId() == null) {
			repository.insert(site);
		} else {
			repository.update(site);
		}
	}
}