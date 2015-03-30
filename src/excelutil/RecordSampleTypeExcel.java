package excelutil;

import interpreter.AttributeType;
import interpreter.RecordSampleType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecordSampleTypeExcel extends GerneralExcel{

	public RecordSampleTypeExcel(JSONObject template) {
		super(template);
		// TODO Auto-generated constructor stub
	}
	
	public RecordSampleTypeExcel(int index, RecordSampleType recordSample){
		super(index, recordSample);
	}
	
	public void generateXLS(){
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet(tableName);
		Row row = sheet.createRow(0);
		@SuppressWarnings("rawtypes")
		Iterator iter = template.keys();
		System.out.println(template);
		int i = 0;
		while (iter.hasNext()){
			String key = iter.next().toString();
			
			if (!key.equals("pidList")){
				row.createCell(i).setCellValue(key);
				i++;
			} else {
				try {
					JSONArray pidList = template.getJSONArray(key);
					for (int j = 0; j < pidList.length(); j++){
						JSONObject at = pidList.getJSONObject(j).getJSONObject("at");
						row.createCell(i).setCellValue(at.getString("atType") + "/" + at.getString("unit"));
						i++;
//						JSONArray atList = pidList.getJSONObject(j).getJSONArray("at");
//						System.out.println(atList);
//						for (int k = 0; k < atList.length(); k++){
//							JSONObject at = atList.getJSONObject(k);
//							row.createCell(i).setCellValue(at.getString("atType") + "/" + at.getString("unit"));
//							i++;
//						}
						
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
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
		RecordSampleType recordSampleType = new RecordSampleType();
		InputStream is;
		try {
			is = new FileInputStream(path + tableName + ".xls");
			Workbook wb = new HSSFWorkbook(is);
			Sheet sheet = wb.getSheetAt(0);
			Row rowTitle = sheet.getRow(0);
			for (int i = 1; i <= sheet.getLastRowNum(); i++){
				Row row = sheet.getRow(i);
				JSONObject record = template;
//				System.out.println(record);
//				JSONArray atList = record.getJSONArray("pid").getJSONObject(0).getJSONArray("at");
//				record.getJSONArray("pidList").getJSONObject(0).remove("at");
				int atIndex = 0;
				JSONArray pidList = new JSONArray();
				for (int j = 0; j  < row.getLastCellNum(); j++){
					row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
					String title = rowTitle.getCell(j).getStringCellValue();
					if (record.has(title)){
						record.put(rowTitle.getCell(j).getStringCellValue(), row.getCell(j).getStringCellValue());
					} else {
						String pid = record.getJSONArray("pidList").getJSONObject(0).getString("pid");
						record.getJSONArray("pidList").remove(0);
						String name = title.substring(0,title.indexOf("/"));
						JSONObject parameter = new JSONObject();
						JSONObject attribute = AttributeType.getInstance().getAttribute(name);
						parameter.put("unit", attribute.get("unit"));
						parameter.put("type", attribute.get("type"));
						parameter.put("atType", name);
						parameter.put("value", row.getCell(j).getStringCellValue());
						JSONObject at = new JSONObject();
						at.accumulate("at", parameter);
						at.accumulate("pid", pid);
						record.getJSONArray("pidList").put(at);
					}

				}
//				record.getJSONArray("pid").getJSONObject(0).put("at", atList);
//				System.out.println(record);
				recordSampleType.insert(record, true);  
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
