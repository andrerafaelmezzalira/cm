package br.com.samaia.cm.client.robo.enel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import br.com.samaia.cm.client.robo.HeaderFonteDados;
import br.com.samaia.cm.constants.Constants;
import br.com.samaia.cm.domain.entity.Enel;
import br.com.samaia.cm.utils.Utils;
import br.com.samaia.cm.vo.HeaderVO;

/**
 * Fonte de dados para Enel
 * 
 * @author andrerafaelmezzalira
 *
 */
public class EnelFonteDados {

	private static final String ARQUIVO_IMPORTACAO_EXCEL = "/importacao/enel.xls";

	public static final HeaderVO getFonteDadosObject(String servico) {
		HeaderVO header = HeaderFonteDados.getHeader(Constants.ENEL, servico);
		List<Enel> enels = new ArrayList<>();
		Enel enel2 = new Enel();
		enel2.setUnidadeConsumidora("13028285");
		enel2.setCpfCnpj("04418640150");
		enel2.setCodigoImovel("codigoImovel");
		enel2.setNumeroContrato("numeroContrato");
		enels.add(enel2);
		Enel enel4 = new Enel();
		enel4.setUnidadeConsumidora("12052309");
		enel4.setCpfCnpj("02.628.090/0001-95");
		enel4.setCodigoImovel("sdfsd");
		enels.add(enel4);
		Enel enel3 = new Enel();
		enel3.setUnidadeConsumidora("11392721");
		enel3.setCpfCnpj("04461568000160");
		enel3.setCodigoImovel("sdfsd");
		enels.add(enel3);
		Enel enel = new Enel();
		enel.setUnidadeConsumidora("10970186");
		enel.setCpfCnpj("52967344168");
		enel.setCodigoImovel("sdfsd");
		enels.add(enel);
		header.setParametros(enels);
		return header;
	}

	public static final HeaderVO getFonteDadosExcel(String servico) {
		InputStream in = EnelFonteDados.class.getResourceAsStream(ARQUIVO_IMPORTACAO_EXCEL);
		Workbook workbook;
		Sheet sheet = null;
		try {
			workbook = WorkbookFactory.create(in);
			sheet = workbook.getSheetAt(0);
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		Iterator<Row> rowIterator = sheet.iterator();
		HeaderVO header = HeaderFonteDados.getHeader(Constants.ENEL, servico);
		List<Enel> enels = new ArrayList<>();
		int count = 0;
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if (count++ == 0) {
				continue;
			}
			if (count == 30) {
				break;
			}
			Enel enel = new Enel();
			enel.setCodigoImovel("C");
			enel.setNumeroContrato("D");
			if (row.getCell(1) != null) {
				row.getCell(1).setCellType(CellType.STRING);
				if (row.getCell(1).toString().length() > 18) {
					continue;
				}
				enel.setCpfCnpj(row.getCell(1).toString());
			}
			if (row.getCell(2) != null) {
				row.getCell(2).setCellType(CellType.STRING);
				if (row.getCell(2).toString().length() > 15 || !row.getCell(2).toString().matches("^\\d.+$")) {
					continue;
				}
				enel.setUnidadeConsumidora(row.getCell(2).toString());
			}
			if (!(Utils.isValidString(enel.getCpfCnpj()) && Utils.isValidString(enel.getUnidadeConsumidora())
					&& isUnique(enels, enel))) {
				continue;
			}
			enels.add(enel);
		}
		header.setParametros(enels);
		return header;
	}

	private static boolean isUnique(List<Enel> enels, Enel enel) {
		boolean unique = true;
		for (Enel b : enels) {
			if (b.getCpfCnpj().equals(enel.getCpfCnpj())
					&& b.getUnidadeConsumidora().equals(enel.getUnidadeConsumidora())) {
				unique = false;
				break;
			}
		}
		return unique;
	}

}
