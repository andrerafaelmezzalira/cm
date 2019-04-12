package br.com.samaia.cm.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe para métodos utilitários
 * 
 * @author andrerafaelmezzalira
 *
 */
public class RegexpUtils {

	private static final Logger log = Logger.getLogger(RegexpUtils.class.getName());

	public static final List<String> executeRegexpList(final String text, final String er, final int group) {
		List<String> matchs = new ArrayList<>();
		final Matcher matcher = Pattern.compile(er, Pattern.DOTALL).matcher(text);
		while (matcher.find()) {
			matchs.add(matcher.group(group));
		}
		if (matchs.isEmpty()) {
			log.info("no match " + er);
		}
		return matchs;
	}

	public static final String executeRegexp(final String text, final String er, final int group) {
		final Matcher matcher = Pattern.compile(er, Pattern.DOTALL).matcher(text);
		final boolean match = matcher.find();
		if (!match) {
			log.info("no match " + er);
			return "";
		}
		return matcher.group(group);
	}
}
