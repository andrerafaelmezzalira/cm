

package br.com.samaia.cm.file;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;

import br.com.samaia.cm.utils.FileUtils;

/**
 * Treinar regexp para pdf
 * 
 * @author andrerafaelmezzalira
 *
 */
public class TreinaPdf {

	private static Logger log = Logger.getLogger(TreinaPdf.class.getName());

	public static void main(String[] args) {

		try {
			PDDocument document = PDDocument.load(new File("/Users/programacao/desktop/saoJose.pdf"));
			String text = FileUtils.getTextFromPdf(document);
			Matcher matcher = Pattern.compile("(?!/PARCELA/)+?PARCELA(.+?)\\s", Pattern.DOTALL).matcher(text);
			while (matcher.find()) {
				log.info(matcher.group(1));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

//		(?!/VILLA\\sVECCHIA\\sRISTORANTE\\sLTDA\\sME\\n/)+?VILLA\\sVECCHIA\\sRISTORANTE\\sLTDA\\sME\\n(.+?)-