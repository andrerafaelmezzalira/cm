package br.com.samaia.cm.domain.repository;

import java.util.logging.Logger;

import br.com.samaia.cm.arq.domain.AbstractRepository;
import br.com.samaia.cm.domain.entity.Site;

/**
 * Acessa a tabela Site
 * 
 * @author andrerafaelmezzalira
 *
 */
public class SiteRepository extends AbstractRepository<Site> {

	private Logger log = Logger.getLogger(this.getClass().getName());

	public SiteRepository() {
		super(Site.class);
		log.info("not used ");
	}
}