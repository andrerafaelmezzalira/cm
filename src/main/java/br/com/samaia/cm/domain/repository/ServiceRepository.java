package br.com.samaia.cm.domain.repository;

import br.com.samaia.cm.arq.domain.AbstractRepository;
import br.com.samaia.cm.domain.entity.Service;

/**
 * 
 * @author andrerafaelmezzalira
0 *
 */
public class ServiceRepository extends AbstractRepository<Service> {

	public ServiceRepository() {
		super(Service.class);
	}
}