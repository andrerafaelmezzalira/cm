package br.com.samaia.cm.arq.producer;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * @author andrerafaelmezzalira
 *
 */
@ApplicationScoped
public class PropertyProducer {

	private Logger log = Logger.getLogger(this.getClass().getName());

	private static final String CONFIG_PROPERTIES = "/properties/config.properties";
	private static final String PROPERTIES = "properties";
	private static final Properties properties = new Properties();

	@PostConstruct
	public void init() {
		if (properties.isEmpty()) {
			populate(CONFIG_PROPERTIES);
			final List<String> props = Arrays.asList(properties.get(PROPERTIES).toString().split(","));
			for (final String prop : props) {
				populate("/" + PROPERTIES + "/" + prop.trim() + "." + PROPERTIES);
			}
		}
	}

	@Property
	@Produces
	public String produceString(final InjectionPoint ip) {
		return properties.getProperty(getKey(ip));
	}

	@Property
	@Produces
	public int produceInt(final InjectionPoint ip) {
		return Integer.valueOf(properties.getProperty(getKey(ip)));
	}

	@Property
	@Produces
	public boolean produceBoolean(final InjectionPoint ip) {
		return Boolean.valueOf(properties.getProperty(getKey(ip)));
	}

	@Property
	@Produces
	public Map<String, String> produceMap() {
		Map<String, String> map = new HashMap<>();
		for (final String name : properties.stringPropertyNames()) {
			map.put(name, properties.getProperty(name));
		}
		return map;
	}

	private String getKey(final InjectionPoint ip) {
		return (ip.getAnnotated().isAnnotationPresent(Property.class)
				&& !ip.getAnnotated().getAnnotation(Property.class).value().isEmpty())
						? ip.getAnnotated().getAnnotation(Property.class).value()
						: ip.getMember().getName();
	}

	private void populate(final String file) {
		log.info("loading properties " + file);
		try {
			properties.load(PropertyProducer.class.getResourceAsStream(file));
		} catch (IOException e) {
			e.printStackTrace();
			log.severe("problems properties ");
		}
	}
}