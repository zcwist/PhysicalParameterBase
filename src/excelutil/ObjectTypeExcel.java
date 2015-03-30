package excelutil;

import interpreter.ObjectType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONException;
import org.json.JSONObject;

public class ObjectTypeExcel extends GerneralExcel{

	public ObjectTypeExcel(JSONObject template) {
		super(template);
		// TODO Auto-generated constructor stub
	}
	
	public ObjectTypeExcel(int index, ObjectType objectType){
		super(index,objectType);
	}


	
	public void readXLS(){
		try {
			ObjectType objectType = new ObjectType();
			InputStream is = new FileInputStream(path + tableName + ".xls");
			Workbook wb = new HSSFWorkbook(is);
			Sheet sheet = wb.getSheetAt(0);
			Row rowTitle = sheet.getRow(0);
			for (int i = 1; i <= sheet.getLastRowNum(); i++){
				Row row = sheet.getRow(i);
				JSONObject record = template;
				for (int j = 0; j  < row.getLastCellNum(); j++){
					row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
					record.put(rowTitle.getCell(j).getStringCellValue(), row.getCell(j).getStringCellValue());
				}
				objectType.insert(record,templateIndex); 
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
