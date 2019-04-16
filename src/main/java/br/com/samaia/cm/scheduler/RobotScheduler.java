package br.com.samaia.cm.scheduler;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import java.util.logging.Logger;

import br.com.samaia.cm.service.scheduler.RobotSchedulerService;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
@Singleton
@Startup
public class RobotScheduler {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private RobotSchedulerService roboSchedulerService;

	@Schedule(hour = "*", minute = "*/58")
	public void reprocessar() {
		log.info("initializing reprocessing parameters that not working");
		roboSchedulerService.call();
		log.info("finish reprocessing parameters that not working");
	}

}
