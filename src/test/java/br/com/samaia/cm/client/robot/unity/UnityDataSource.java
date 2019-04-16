package br.com.samaia.cm.client.robot.unity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import br.com.samaia.cm.client.robot.HeaderDataSource;
import br.com.samaia.cm.constants.Constants;
import br.com.samaia.cm.domain.entity.Unity;
import br.com.samaia.cm.utils.Utils;
import br.com.samaia.cm.vo.HeaderVO;

/**
 * 
 * @author andrerafaelmezzalira
 *
 */
public class UnityDataSource {

	private static final String IMPORT_EXCEL = "/import/unity.xls";

	public static final HeaderVO getObjectDataSource(String service) {
		HeaderVO header = HeaderDataSource.getHeader(service);
		List<Unity> units = new ArrayList<>();
		Unity unity = new Unity();
		unity.setName("teste nome unidade");
		unity.setHasStorage(true);
		units.add(unity);
		header.setParameters(units);
		return header;
	}

	public static final HeaderVO getExcelDataSource(String service) throws EncryptedDocumentException, IOException {
		Sheet sheet = WorkbookFactory.create(UnityDataSource.class.getResourceAsStream(IMPORT_EXCEL)).getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();
		HeaderVO header = HeaderDataSource.getHeader(Constants.ADD_UNITY);
		List<Unity> units = new ArrayList<>();
		int count = 0;
		while (rows.hasNext()) {
			Row row = rows.next();
			if (count++ == 0) {
				continue;
			}
			if (count == 30) {
				break;
			}
			Unity unity = new Unity();
			if (row.getCell(1) != null) {
				row.getCell(1).setCellType(CellType.STRING);
				unity.setName(row.getCell(1).toString());
			}
			if (!(Utils.isValidString(unity.getName()) && isUnique(units, unity))) {
				continue;
			}
			units.add(unity);
		}
		header.setParameters(units);
		return header;
	}

	private static boolean isUnique(List<Unity> units, Unity unity) {
		boolean unique = true;
		for (Unity b : units) {
			if (b.getName().equals(unity.getName())) {
				unique = false;
				break;
			}
		}
		return unique;
	}

}
