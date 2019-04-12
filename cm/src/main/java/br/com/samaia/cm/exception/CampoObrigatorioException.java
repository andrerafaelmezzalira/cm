package br.com.samaia.cm.exception;

import javax.ejb.ApplicationException;

import java.util.logging.Logger;

/**
 * Exceção para campos obrigatórios não preenchidos
 * 
 * @author andrerafaelmezzalira
 *
 */
@ApplicationException(rollback = true)
public class CampoObrigatorioException extends Exception {

	private Logger log = Logger.getLogger(this.getClass().getName());
	private static final long serialVersionUID = 1L;

	public CampoObrigatorioException(String mensagem) {
		super(mensagem);
		log.info(mensagem);
	}
}
