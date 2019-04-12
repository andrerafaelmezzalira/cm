package br.com.samaia.cm.exception;

import javax.ejb.ApplicationException;

import java.util.logging.Logger;

/**
 * Exceção para quando o usuario for invalido
 * 
 * @author andrerafaelmezzalira
 *
 */
@ApplicationException(rollback = true)
public class UsuarioInvalidoException extends Exception {

	private Logger log = Logger.getLogger(this.getClass().getName());
	private static final long serialVersionUID = 1L;

	public UsuarioInvalidoException(String mensagem) {
		super(mensagem);
		log.info(mensagem);
	}
}
