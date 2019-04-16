package br.com.samaia.cm.service.robot;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.openqa.selenium.WebDriver;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.samaia.cm.arq.producer.Property;
import br.com.samaia.cm.constants.enums.State;
import br.com.samaia.cm.domain.entity.Header;
import br.com.samaia.cm.domain.entity.Parameter;
import br.com.samaia.cm.utils.JsonUtils;
import br.com.samaia.cm.utils.Utils;

/**
 * @author andrerafaelmezzalira
 *
 */
public abstract class AbstractRobotService implements RobotService {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@Inject
	@Property
	private Map<String, String> properties;

	public void call(WebDriver webDriver, Header header) {
		Collection<? extends Parameter> parameters = null;
		try {
			parameters = Utils.getParameters(header);
		} catch (IllegalArgumentException | IllegalAccessException e1) {
			log.severe(e1.getMessage());
		}
		int i = 0;
		for (Parameter parameter : parameters) {
			try {
				final Field field = parameter.getClass().getDeclaredField("header");
				field.setAccessible(true);
				parameter.getClass().getMethod("setHeader", Header.class).invoke(parameter, header);
				log.info("processing parameter: " + ++i + " of " + Utils.getParameters(header).size() + "\n"
						+ JsonUtils.toJson(parameter));
				executeService(webDriver, parameter);
				parameter.setState(JsonUtils.toJson(State.PROCESSED));
				log.info("parameter processed successfully! ");
			} catch (Exception e) {
				log.severe("-------------- problem call AbstractRoboApiService -------------");
				log.severe(e.getClass().getName());
				log.severe(e.getMessage());
				try {
					parameter.setState(JsonUtils.toJson(State.REPROCESS));
				} catch (JsonProcessingException e1) {
					log.severe("jsonprocessing in abstractroboservice");
				}
			} finally {
				log.info("result: " + parameter.getResult());
				try {
					log.info("parameter state: " + JsonUtils.toJson(parameter.getState()));
				} catch (JsonProcessingException e) {
					log.severe(e.getMessage());
				}
				Header h = new Header();
				h.setId(header.getId());
				try {
					parameter.getClass().getMethod("setHeader", Header.class).invoke(parameter, h);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException e) {
					log.severe(e.getMessage());
				}
			}
		}
		log.info("finish call abstractroboservice");
	}

	protected String getProperty(String name) {
		return properties.get(name);
	}

	public abstract void executeService(WebDriver webDriver, Parameter parameter) throws Exception;
}
