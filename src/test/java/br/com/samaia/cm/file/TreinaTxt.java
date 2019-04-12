package br.com.samaia.cm.file;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Treinar regexp com txt
 * 
 * @author ricardoschmidt
 *
 */
public class TreinaTxt {

	private static Logger log = Logger.getLogger(TreinaTxt.class.getName());

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		try {
			String text = new Scanner(new File("/home/suporte/Área de Trabalho/textPdf.txt")).useDelimiter("\\A").next();

			Matcher matcher = Pattern.compile("(?!/em\\s\\(/)+?em\\s\\((\\d{1,})x\\)(.+)", Pattern.DOTALL).matcher(text);

			while (matcher.find()) {
				log.info(matcher.group(2));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
