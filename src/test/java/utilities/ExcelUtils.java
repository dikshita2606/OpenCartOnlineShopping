package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public FileInputStream fin;
	public FileOutputStream fout;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFCell cell;
	public XSSFRow row;
	public XSSFCellStyle cellstyle;
	String path;
	
	public ExcelUtils(String path) {
		this.path = path;
	}
	
	public int getRowCount(String sheetName) throws Exception {
		fin = new FileInputStream(path);
		workbook = new XSSFWorkbook(fin);
		sheet = workbook.getSheet(sheetName);
		//System.out.println("Sheetname = "+sheet);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fin.close();
		return rowCount;
	}
		
	public int getCellCount(String sheetName, int rowCount) throws Exception {
		fin = new FileInputStream(path);
		workbook = new XSSFWorkbook(fin);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowCount);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fin.close();
		return cellCount;
	}
	
	public String getCellData(String sheetName, int rowCount,int cellCount) throws Exception {
		fin = new FileInputStream(path);
		workbook = new XSSFWorkbook(fin);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowCount);
		cell = row.getCell(cellCount);
		
		DataFormatter ds = new DataFormatter();
		String data;
		try {
			data = ds.formatCellValue(cell); //returns cell value in frmated format as string
		}
		catch(Exception ex){
			data ="";
		}		
		workbook.close();
		fin.close();
		return data;
	}
	
	public void setCellData(String sheetName, int rowNum,int CellNum,String data) throws Exception {
		File xlfile = new File(path);
		if(!xlfile.exists())  // if file doesn't exist then create new file
		{
			workbook = new XSSFWorkbook(fin);
			fout = new FileOutputStream(path);
			workbook.write(fout);
		}
		fin = new FileInputStream(path);
		workbook = new XSSFWorkbook(fin);
		
		if(workbook.getSheetIndex(sheetName)==-1)  // if sheet doesn't exist then create new sheet
		{
			workbook.createSheet(sheetName);
		}
		
		sheet = workbook.getSheet(sheetName);
		
		if(sheet.getRow(rowNum)==null)
		{
			sheet.createRow(rowNum);
		}
		
		row = sheet.getRow(rowNum);
		
		cell = row.createCell(CellNum);
		cell.setCellValue(data);
		fout = new FileOutputStream(path);
		workbook.write(fout);
		workbook.close();
		fin.close();
		fout.close();
	}
	
	public void setGreenColor(String sheetName, int rowNum,int CellNum) throws Exception {
		fin = new FileInputStream(path);
		workbook = new XSSFWorkbook(fin);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(CellNum);
		
		cellstyle = workbook.createCellStyle();
		
		cellstyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		cellstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(cellstyle);
		workbook.write(fout);				
		workbook.close();
		fin.close();
	}
	
	public void setRedColor(String sheetName, int rowNum,int CellNum) throws Exception {
		fin = new FileInputStream(path);
		workbook = new XSSFWorkbook(fin);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(CellNum);
		
		cellstyle = workbook.createCellStyle();
		
		cellstyle.setFillForegroundColor(IndexedColors.RED.getIndex());
		cellstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);		
		cell.setCellStyle(cellstyle);
		workbook.write(fout);				
		workbook.close();
		fin.close();
	}
	
	
}
