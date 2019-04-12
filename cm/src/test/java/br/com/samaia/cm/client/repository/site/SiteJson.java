package br.com.samaia.cm.client.repository.site;

import br.com.samaia.cm.constants.Constants;
import br.com.samaia.cm.domain.entity.Site;

/**
 * Cria um site
 * 
 * @author andrerafaelmezzalira
 *
 */
public class SiteJson {

	public static final Site getFonteDadosObject() {
		Site site = new Site();
		site.setNome(Constants.SANEAGO);
		site.setServico(Constants.DEBITO);
		return site;
	}
}
