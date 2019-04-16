package br.com.samaia.cm.client.repository.service;

import br.com.samaia.cm.constants.Constants;
import br.com.samaia.cm.domain.entity.Service;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
public class ServiceDataSource {

	public static final Service getObjectDataSource() {
		Service site = new Service();
		site.setName(Constants.ADD_UNITY);
		return site;
	}
}
