package br.com.samaia.cm.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

import br.com.samaia.cm.constants.Constants;

/**
 * Classe para métodos utilitários para file
 * 
 * @author andrerafaelmezzalira
 *
 */
public class FileUtils {

	public static String gerarPdf(String source) throws IOException, DocumentException {
		return String
				.valueOf(encode(createPdf(Constants.PATH_DOWNLOAD + "pdf_" + Utils.getDataHora() + ".pdf", source)));
	}

	private static final File createPdf(String absolutePath, final String source)
			throws IOException, DocumentException {
		Tidy tidy = new Tidy();
		tidy.setInputEncoding("utf-8");
		tidy.setOutputEncoding("utf-8");
		tidy.setShowErrors(0);
		tidy.setForceOutput(true);
		tidy.setShowWarnings(false);
		tidy.setQuiet(true);
		tidy.setErrout(new PrintWriter(new ByteArrayOutputStream()));
		Document doc = tidy.parseDOM(new ByteArrayInputStream(source.getBytes()), null);
		ITextRenderer rendered = new ITextRenderer();
		rendered.setDocument(doc, null);
		rendered.layout();
		File filePermission = new File(absolutePath);
		filePermission.setWritable(Boolean.TRUE, Boolean.FALSE);
		rendered.createPDF(new FileOutputStream(filePermission));
		return filePermission;
	}

	@SuppressWarnings("resource")
	public static String encode(File sourceFile) throws IOException {
		byte[] decodedBytes = Base64.encodeBase64(loadFileAsBytesArray(sourceFile), true);
		File fTxt;
		BufferedOutputStream writer = new BufferedOutputStream(
				new FileOutputStream(fTxt = new File(Constants.PATH_DOWNLOAD + "txt_" + Utils.getDataHora() + ".txt")));
		writer.write(decodedBytes);
		writer.flush();
		writer.close();
		String text = new Scanner(fTxt).useDelimiter("\\A").next();
		fTxt.delete();
		return text.replaceAll("\r\n", "");
	}

	public static void decode(File sourceFile, String targetFile) throws IOException {
		byte[] decodedBytes = Base64.decodeBase64(loadFileAsBytesArray(sourceFile));
		File file = new File(targetFile);
		BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
		writer.write(decodedBytes);
		writer.flush();
		writer.close();
	}
	
	public static void decode(String sourceFile, String targetFile) throws IOException {
		byte[] decodedBytes = Base64.decodeBase64(sourceFile);
		File file = new File(targetFile);
		BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
		writer.write(decodedBytes);
		writer.flush();
		writer.close();
	}

	public static final String getTextFromPdf(final PDDocument pdDocument) throws IOException {
		final PDFTextStripper pdfStripper = new PDFTextStripper();
		pdfStripper.setSortByPosition(true);
		return pdfStripper.getText(pdDocument);
	}

	private static byte[] loadFileAsBytesArray(File file) throws IOException {
		int length = (int) file.length();
		BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
		byte[] bytes = new byte[length];
		reader.read(bytes, 0, length);
		reader.close();
		file.delete();
		return bytes;
	}
	
	public static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
		if (fileToZip.isHidden()) {
			return;
		}
		if (fileToZip.isDirectory()) {
			if (fileName.endsWith("/")) {
				zipOut.putNextEntry(new ZipEntry(fileName));
				zipOut.closeEntry();
			} else {
				zipOut.putNextEntry(new ZipEntry(fileName + "/"));
				zipOut.closeEntry();
			}
			File[] children = fileToZip.listFiles();
			for (File childFile : children) {
				zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
			}
			return;
		}
		FileInputStream fis = new FileInputStream(fileToZip);
		ZipEntry zipEntry = new ZipEntry(fileName);
		zipOut.putNextEntry(zipEntry);
		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zipOut.write(bytes, 0, length);
		}
		fis.close();
	}
}
