package br.com.samaia.cm.service.scheduler;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.samaia.cm.domain.entity.Header;
import br.com.samaia.cm.exception.RoboException;
import br.com.samaia.cm.service.domain.HeaderService;
import br.com.samaia.cm.service.robo.RoboApiService;

/**
 * 
 * Serviço que reprocessa parametros que não tiveram sucesso
 * 
 * @author andrerafaelmezzalira
 *
 */
@Stateless
public class RoboSchedulerService {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private HeaderService headerService;

	@Inject
	private RoboApiService roboApiService;

	public void call() {
		final List<Header> headers = headerService.getAllParametersInvalids();
		log.info("reprocessando numeros de header " + headers.size());
		for (final Header header : headers) {
			try {
				roboApiService.call(header);
			} catch (RoboException e) {
				log.severe("problema call scheduler");
			}
		}
		log.info("reprocessamento de headers feito:  " + headers.size());
	}
}
