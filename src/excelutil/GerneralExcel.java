package excelutil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONObject;

public class GerneralExcel{
	protected String tableName;
	protected String path;
	protected JSONObject template;
	public GerneralExcel(JSONObject template){
		this.tableName = this.getClass().getSimpleName();
		this.template = template;
		path = "/";
	}
	
	/**
	 * Generate a xls with a template
	 * @param template
	 */
	public void generateXLS(){
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet(tableName);
		Row row = sheet.createRow(0);
		@SuppressWarnings("rawtypes")
		Iterator iter = template.keys();
		int i = 0;
		while (iter.hasNext()){
			String key = iter.next().toString();
			row.createCell(i).setCellValue(key);
			i ++;
		}
		try {
			FileOutputStream fileOut = new FileOutputStream(path + tableName + ".xls");
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Read a xls for the template, and insert the data to db
	 * @param path
	 */
	public void readXLS(){
		
	}
	
	public void setPath(String path){
		this.path = path;
	}

}
