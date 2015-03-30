package tester.interpreter;

import interpreter.ObjectType;
import interpreter.RecordSampleType;
import interpreter.SampleType;
import excelutil.ObjectTypeExcel;
import excelutil.RecordSampleTypeExcel;
import excelutil.SampleTypeExcel;

public class ExcelUtilTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ObjectTypeExcelTester();
//		SampleTypeExcelTester();
//		RecordSampleTypeExcelTester();

	}
	
	public static void ObjectTypeExcelTester(){
		ObjectTypeExcel ote = new ObjectTypeExcel(0,new ObjectType());//初始化时要明确模板
		ote.setPath("/home/kiwi/Documents/research/GRBBanking/");
//		ote.generateXLS();
		ote.readXLS();
	}
	
	public static void SampleTypeExcelTester(){
//		SampleType sampleType = new SampleType();
//		SampleTypeExcel ste = new SampleTypeExcel(sampleType.getFields());
		SampleTypeExcel ste = new SampleTypeExcel(0, new SampleType());
		ste.setPath("/home/kiwi/Documents/research/GRBBanking/");
//		ste.generateXLS();
		ste.readXLS();
	}
	public static void RecordSampleTypeExcelTester(){
		RecordSampleType recordSampleType = new RecordSampleType();
		RecordSampleTypeExcel rste = new RecordSampleTypeExcel(recordSampleType.getFields(1));
		rste.setPath("/home/kiwi/Documents/research/GRBBanking/");
//		rste.generateXLS();
		rste.readXLS();
	}
	

}
