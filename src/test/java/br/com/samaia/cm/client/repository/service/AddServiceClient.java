package br.com.samaia.cm.client.repository.service;

import br.com.samaia.cm.client.AbstractClient;
import br.com.samaia.cm.domain.entity.Service;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
public class AddServiceClient extends AbstractClient<Service> {

	private static final String URL_SAVE_SITE = "http://localhost:8080/cm/api/saveSite";

	public static void main(String[] args) {
		AddServiceClient client = new AddServiceClient();
		client.log(client.post(URL_SAVE_SITE, client.getDataSource()));
	}

	@Override
	protected Service getDataSource() {
		return ServiceDataSource.getObjectDataSource();
	}
}
