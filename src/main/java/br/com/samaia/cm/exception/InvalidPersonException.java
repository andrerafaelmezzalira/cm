package br.com.samaia.cm.exception;

import javax.ejb.ApplicationException;

import java.util.logging.Logger;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
@ApplicationException(rollback = true)
public class InvalidPersonException extends Exception {

	private Logger log = Logger.getLogger(this.getClass().getName());
	private static final long serialVersionUID = 1L;

	public InvalidPersonException(String message) {
		super(message);
		log.info(message);
	}
}
