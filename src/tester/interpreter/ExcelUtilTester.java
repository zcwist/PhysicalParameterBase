package tester.interpreter;

import interpreter.ObjectType;
import interpreter.RecordSampleType;
import interpreter.SampleType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import excelutil.ObjectTypeExcel;
import excelutil.RecordSampleTypeExcel;
import excelutil.SampleTypeExcel;

public class ExcelUtilTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
//		ObjectTypeExcelTester();
//		SampleTypeExcelTester();
		//"ObjectTypeExcelTester","SampleTypeExcelTester","RecordSampleTypeExcelTester"
		tester.test(2,true,5);


	}
	
	private static ExcelUtilTester tester = new ExcelUtilTester();
	
	private String[] functionList  = {"ObjectTypeExcelTester","SampleTypeExcelTester","RecordSampleTypeExcelTester" };
	
	private void test(int index, boolean generate, int modelIndex){
		String function = functionList[index];
		try {
			Method method = ExcelUtilTester.class.getDeclaredMethod(function , boolean.class, int.class);
			method.invoke(tester, generate, modelIndex);
			
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void ObjectTypeExcelTester(boolean generate, int modelIndex){
		
		ObjectTypeExcel ote = new ObjectTypeExcel(modelIndex,new ObjectType());//初始化时要明确模板
		ote.setPath("/home/kiwi/Documents/research/GRBBanking/");
		if (generate) ote.generateXLS();
		else ote.readXLS();
	}
	
	public static void SampleTypeExcelTester(boolean generate, int modelIndex){
//		SampleType sampleType = new SampleType();
//		SampleTypeExcel ste = new SampleTypeExcel(sampleType.getFields());
		SampleTypeExcel ste = new SampleTypeExcel(modelIndex, new SampleType());
		ste.setPath("/home/kiwi/Documents/research/GRBBanking/");
		if (generate) ste.generateXLS();
		else ste.readXLS();
	}
	public static void RecordSampleTypeExcelTester(boolean generate, int modelIndex){
		RecordSampleType recordSampleType = new RecordSampleType();
		RecordSampleTypeExcel rste = new RecordSampleTypeExcel(recordSampleType.getFields(modelIndex));
		rste.setPath("/home/kiwi/Documents/research/GRBBanking/");
		if (generate) rste.generateXLS();
		else rste.readXLS();
	}
	

}
