package br.com.samaia.cm.scheduler;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import java.util.logging.Logger;

import br.com.samaia.cm.service.scheduler.RoboSchedulerService;

/**
 * 
 * Timer que reprocessa os parametros não processados
 * 
 * @author andrerafaelmezzalira
 *
 */
@Singleton
@Startup
public class RoboScheduler {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	private RoboSchedulerService roboSchedulerService;
	
	@Schedule(hour="*", minute="*/58")
	public void reprocessar() {
		log.info("iniciando reprocessamento de parametros que não deram certo");
		roboSchedulerService.call();
		log.info("finalizado reprocessamento de parametros que não deram certo");
	}

}
