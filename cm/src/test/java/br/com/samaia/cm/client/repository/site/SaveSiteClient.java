package br.com.samaia.cm.client.repository.site;

import br.com.samaia.cm.client.AbstractClient;
import br.com.samaia.cm.domain.entity.Site;

/**
 * Client que cria um site
 * 
 * @author andrerafaelmezzalira
 *
 */
public class SaveSiteClient extends AbstractClient<Site> {

	private static final String URL_SAVE_SITE = "http://localhost:8080/cm/api/saveSite";

	public static void main(String[] args) {
		SaveSiteClient client = new SaveSiteClient();
		client.log(client.post(URL_SAVE_SITE, client.getFonteDeDados()));
	}

	@Override
	protected Site getFonteDeDados() {
		return SiteJson.getFonteDadosObject();
	}
}
