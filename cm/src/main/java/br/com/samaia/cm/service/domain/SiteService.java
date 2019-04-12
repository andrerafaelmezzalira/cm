package br.com.samaia.cm.service.domain;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.samaia.cm.domain.entity.Site;
import br.com.samaia.cm.domain.repository.SiteRepository;

/**
 * Operações na tabela Site
 * 
 * @author andrerafaelmezzalira
 *
 */
@Stateless
public class SiteService {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private SiteRepository repository;

	public void save(Site site) throws JsonProcessingException {
		log.info("método save(Site site) - insert " + String.valueOf(site.getId() == null));
		if (site.getId() == null) {
			repository.insert(site);
		} else {
			repository.update(site);
		}
	}
}