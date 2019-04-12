package br.com.samaia.cm.file;

import javax.inject.Inject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import br.com.samaia.cm.constants.Constants;
import br.com.samaia.cm.utils.Utils;

/**
 * Treinar webdriver site
 * 
 * @author andrerafaelmezzalira
 *
 */
public class TreinaSite {

	public static void main(String[] args) throws Exception {

		FirefoxOptions options = new FirefoxOptions();
		options.addPreference("browser.download.folderList", 2);
		options.addPreference("browser.download.dir", Constants.PATH_DOWNLOAD);
		options.addPreference("browser.download.useDownloadDir", true);
		options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
		options.addPreference("pdfjs.disabled", true); // disable the built-in PDF viewer
		options.addArguments("--headless");

		WebDriver webDriver = new FirefoxDriver(options);

		webDriver.get("https://portalfinancas.recife.pe.gov.br/extratoDebitos/1");

		webDriver.findElement(By.id("inscricao")).sendKeys("1341499");
		Utils.sleep(1000);

		WebElement elementImagemCaptcha = webDriver.findElement(By.id("captcha_image"));
		Utils.sleep(1000);
		webDriver.findElement(By.xpath("//*[@id=\"login-form\"]/div[3]/div/button[2]")).submit();
		Utils.sleep(12000);

		if (!webDriver.getCurrentUrl().equals("https://portalfinancas.recife.pe.gov.br/extratoDebitos/1")) {
			
		}

		
		Utils.sleep(1000);
		webDriver.switchTo().frame(1);
		webDriver.findElement(By.name("FormDados")).submit();
		Utils.sleep(5000);
		webDriver.findElement(By.name("Imprimir")).click();

	}
}
