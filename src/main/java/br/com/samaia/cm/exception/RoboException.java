package br.com.samaia.cm.exception;

import javax.ejb.ApplicationException;

import java.util.logging.Logger;

/**
 * Exceção para robo
 * 
 * @author andrerafaelmezzalira
 *
 */
@ApplicationException(rollback = true)
public class RoboException extends Exception {

	private Logger log = Logger.getLogger(this.getClass().getName());
	private static final long serialVersionUID = 1L;

	public RoboException(String mensagem) {
		super(mensagem);
		log.info(mensagem);
	}
}
