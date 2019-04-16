
package br.com.samaia.cm.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import br.com.samaia.cm.constants.Constants;
import br.com.samaia.cm.domain.entity.Header;
import br.com.samaia.cm.domain.entity.Parameter;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
public class Utils {

	private static final Logger log = Logger.getLogger(Utils.class.getName());

	public static final boolean isValidString(final String string) {
		return string != null && !"".equals(string);
	}

	public static final void sleep(final int sleep) throws InterruptedException {
		Thread.sleep(sleep);
	}

	@SuppressWarnings("unchecked")
	public static final Collection<? extends Parameter> getParameters(final Header header)
			throws IllegalArgumentException, IllegalAccessException {
		for (final Field field : header.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			if (field.get(header) instanceof Collection<?> && ((Collection<?>) field.get(header)).size() != 0) {
				return (Collection<? extends Parameter>) field.get(header);
			}
		}
		return new ArrayList<>();
	}

	public static WebDriver getWebDriver(boolean headless) {

		log.info("driver instance init ");

		FirefoxOptions options = new FirefoxOptions();
		options.addPreference("browser.download.folderList", 2);
		options.addPreference("browser.download.dir", Constants.PATH_DOWNLOAD);
		options.addPreference("browser.download.useDownloadDir", true);
		options.addPreference("browser.helperApps.neverAsk.saveToDisk","application/pdf,text/plain,application/octet-stream,application/x-pdf,application/vnd.pdf,application/vnd.openxmlformats-officedocument.spreadsheethtml,text/csv,text/html,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel");
		options.addPreference("pdfjs.disabled", true);
		if (headless) {
			options.addArguments("--headless");
		}

		WebDriver driver = new FirefoxDriver(options);
		log.info("driver instance end ");

		return driver;

	}
}
