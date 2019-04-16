package br.com.samaia.cm.service.scheduler;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.samaia.cm.domain.entity.Header;
import br.com.samaia.cm.exception.RobotException;
import br.com.samaia.cm.service.domain.HeaderService;
import br.com.samaia.cm.service.robot.RobotApiService;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
@Stateless
public class RobotSchedulerService {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private HeaderService headerService;

	@Inject
	private RobotApiService roboApiService;

	public void call() {
		final List<Header> headers = headerService.get();
		log.info("reprocessing numbers of headers " + headers.size());
		for (final Header header : headers) {
			try {
				roboApiService.call(header);
			} catch (RobotException e) {
				log.severe("problem call scheduler");
			}
		}
		log.info("reprocessing of headers done:  " + headers.size());
	}
}
