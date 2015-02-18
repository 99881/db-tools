package com.i94living.dbtools;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
* @ClassName: ReadExcelFile 
* @Description: 读取excel文件 
* @author hhy
* @date 2015年2月6日 下午12:02:07 
*
 */
public class ReadExcelFile {


	/**
	 * ���excel�õ�Table����
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Table> getTablesForExcel(String filePath) throws Exception {
		List<XSSFSheet> list = readSheetForExcel(filePath);
		return getTablesForSheets(list);
	}

	/**
	 * ��ȡһ��execl�������е�sheet
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public List<XSSFSheet> readSheetForExcel(String path) throws Exception {
		InputStream is = new FileInputStream(new File(path));
		XSSFWorkbook workBook = new XSSFWorkbook(is);
		// ��һ�� ��ȡ����ݵ�sheet
		List<XSSFSheet> sheets = new ArrayList<XSSFSheet>();
		for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
			if (workBook.getSheetAt(i) != null) {
				sheets.add(workBook.getSheetAt(i));
			}
		}
		return sheets;
	}

	/**
	 * ��ȡÿһ��sheet��������ݣ�����װ��Table����
	 * 
	 * @param sheets
	 * @return
	 */
	public List<Table> getTablesForSheets(List<XSSFSheet> sheets) {
		List<Table> tables = new ArrayList<Table>();
		for (XSSFSheet xssfSheet : sheets) {
			Table table = new Table();
			XSSFRow row0 = xssfSheet.getRow(0);
			// row0getMergedRegionValue
			table.setRemark(getCellValue(row0.getCell(0)));
			
			List<Column> columns = new ArrayList<Column>();
			table.setName(xssfSheet.getSheetName());
			for (int i = 2; i <= xssfSheet.getLastRowNum(); i++) {
				Column column = new Column();
				column.setName(getCellValue(xssfSheet.getRow(i).getCell(0)));
				column.setIsNum(getCellValue(xssfSheet.getRow(i).getCell(1)));
				column.setType(getCellValue(xssfSheet.getRow(i).getCell(2)));
				column.setRemark(getCellValue(xssfSheet.getRow(i).getCell(3)));
				column.setIsPK(getCellValue(xssfSheet.getRow(i).getCell(4)));
				columns.add(column);
			}
			table.setCulumns(columns);
			tables.add(table);
		}
		return tables;
	}

	/**
	 * ��ȡȥcell�����ֵ
	 * @param cell
	 * @return
	 */
	public String getCellValue(XSSFCell cell) {
		if (cell == null){
			return "";
		}
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			return cell.getCellFormula();
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		}
		return "";
	}
}
