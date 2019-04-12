
package br.com.samaia.cm.utils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;

import br.com.samaia.cm.constants.Constants;
import br.com.samaia.cm.domain.entity.Header;
import br.com.samaia.cm.domain.entity.Parametro;

/**
 * Classe para métodos utilitários
 * 
 * @author andrerafaelmezzalira
 *
 */
public class Utils {

	private static final Logger log = Logger.getLogger(Utils.class.getName());

	public static final LocalDate formatDate(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(data, formatter);
	}

	public static final String getDataHora() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));
	}

	public static final String formatCpfCnpjOnlyNumbers(final String cpfCnpj) {
		if (!isValidString(cpfCnpj)) {
			return "";
		}
		return cpfCnpj.replaceAll("\\.", "").replaceAll("/", "").replaceAll("-", "");
	}

	public static final boolean isValidString(final String string) {
		return string != null && !"".equals(string);
	}

	public static final void sleep(final int sleep) throws InterruptedException {
		Thread.sleep(sleep);
	}

	@SuppressWarnings("unchecked")
	public static final Collection<? extends Parametro> getParametros(final Header header)
			throws IllegalArgumentException, IllegalAccessException {
		for (final Field field : header.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			if (field.get(header) instanceof Collection<?> && ((Collection<?>) field.get(header)).size() != 0) {
				return (Collection<? extends Parametro>) field.get(header);
			}
		}
		return new ArrayList<>();
	}

	public static WebDriver getWebDriver(boolean headless) {

		log.info("driver instanciado ");

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
		log.info("driver instanciado ");

		return driver;

	}

	public static WebDriver getWebDriverParameterPath(boolean headless, String paramDownload) {

		log.info("driver instanciado ");

		FirefoxOptions options = new FirefoxOptions();
		options.addPreference("browser.download.folderList", 2);
		options.addPreference("browser.download.dir", paramDownload);
		options.addPreference("browser.download.useDownloadDir", true);
		options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
		options.addPreference("pdfjs.disabled", true);
		options.setLogLevel(FirefoxDriverLogLevel.FATAL);
		if (headless) {
			options.addArguments("--headless");
		}

		WebDriver driver = new FirefoxDriver(options);

		log.info("driver instanciado ");

		return driver;

	}

	public static final void closeWindow(WebDriver webDriver) {
		Set<String> set = webDriver.getWindowHandles();
		log.info("close window " + set.size());
		if (set.size() > 1) {
			Iterator<String> it = set.iterator();
			String parent = it.next();
			String child = it.next();
			webDriver.switchTo().window(child).close();
			webDriver.switchTo().window(parent);
		}
	}

}
