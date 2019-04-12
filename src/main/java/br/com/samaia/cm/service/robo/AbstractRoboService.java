package br.com.samaia.cm.service.robo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.openqa.selenium.WebDriver;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.samaia.cm.arq.producer.Property;
import br.com.samaia.cm.constants.enums.Estado;
import br.com.samaia.cm.domain.entity.Header;
import br.com.samaia.cm.domain.entity.Parametro;
import br.com.samaia.cm.exception.CampoObrigatorioException;
import br.com.samaia.cm.utils.JsonUtils;
import br.com.samaia.cm.utils.Utils;

/**
 * Inicializa o robo e processa os parametros
 * 
 * @author andrerafaelmezzalira
 *
 */
public abstract class AbstractRoboService implements RoboService {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	@Property
	private Map<String, String> properties;

	public void call(WebDriver webDriver, Header header) {
		Collection<? extends Parametro> parametros = null;
		try {
			parametros = Utils.getParametros(header);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			log.severe(e1.getMessage());
		}
		int i = 0;
		for (Parametro parametro : parametros) {
			try {
				final Field field = parametro.getClass().getDeclaredField("header");
				field.setAccessible(true);
				parametro.getClass().getMethod("setHeader", Header.class).invoke(parametro, header);
				log.info("processando o parametro: " + ++i + " de " + Utils.getParametros(header).size() + "\n"
						+ JsonUtils.toJson(parametro));
				executeSite(webDriver, parametro);
				parametro.setEstado(JsonUtils.toJson(Estado.PROCESSADO));
				log.info("parametro processado com sucesso ");
			} catch (Exception e) {
				log.severe("-------------- problema call AbstractRoboApiService -------------");
				log.severe(e.getClass().getName());
				log.severe(e.getMessage());
				try {
					parametro.setEstado(JsonUtils.toJson(Estado.REPROCESSAR));
				} catch (JsonProcessingException e1) {
					log.severe("jsonprocessing in abstractroboservice");
				}
			} finally {
				log.info("resultado: " + parametro.getResultado());
				try {
					log.info("parametro estado: " + JsonUtils.toJson(parametro.getEstado()));
				} catch (JsonProcessingException e) {
					log.severe(e.getMessage());
				}
				Header h = new Header();
				h.setId(header.getId());
				try {
					parametro.getClass().getMethod("setHeader", Header.class).invoke(parametro, h);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException e) {
					log.severe(e.getMessage());
				}
			}
		}
		log.info("finalizado call abstractroboservice");
	}

	protected void validations(Parametro parametro) throws CampoObrigatorioException {
		if (!Utils.isValidString(parametro.getCodigoImovel())) {
			throw new CampoObrigatorioException(properties.get("codigoImovelInvalido"));
		}
	}

	protected String getProperty(String name) {
		return properties.get(name);
	}

	public abstract void executeSite(WebDriver webDriver, Parametro parametro) throws Exception;
}
