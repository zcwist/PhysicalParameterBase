package cmd;

import interpreter.ObjectType;
import interpreter.RecordSampleType;
import interpreter.SampleType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import excelutil.ObjectTypeExcel;
import excelutil.RecordSampleTypeExcel;
import excelutil.SampleTypeExcel;

public class ExcelUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int index = Integer.valueOf(args[0]);
		boolean generate = Boolean.valueOf(args[1]);
		int modelIndex = Integer.valueOf(args[2]);
		tester.test(index, generate, modelIndex);
		System.out.println(index);
		System.out.println(generate);
		System.out.println(modelIndex);
	}

	private static ExcelUtil tester = new ExcelUtil();
	
	private String[] functionList  = {"ObjectType","SampleType","RecordSample" };
	
	private void test(int index, boolean generate, int modelIndex){
		String function = functionList[index];
		try {
			Method method = ExcelUtil.class.getDeclaredMethod(function , boolean.class, int.class);
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
	
	public static void ObjectType(boolean generate, int modelIndex){
		
		ObjectTypeExcel ote = new ObjectTypeExcel(modelIndex,new ObjectType());//初始化时要明确模板
		ote.setPath("/home/kiwi/Documents/research/GRGBanking/");
		if (generate) ote.generateXLS();
		else ote.readXLS();
	}
	
	public static void SampleType(boolean generate, int modelIndex){
		SampleTypeExcel ste = new SampleTypeExcel(modelIndex, new SampleType());
		ste.setPath("/home/kiwi/Documents/research/GRGBanking/");
		if (generate) ste.generateXLS();
		else ste.readXLS();
	}
	public static void RecordSample(boolean generate, int modelIndex){
		RecordSampleType recordSampleType = new RecordSampleType();
		RecordSampleTypeExcel rste = new RecordSampleTypeExcel(recordSampleType.getFields(modelIndex));
		rste.setPath("/home/kiwi/Documents/research/GRGBanking/");
		if (generate) rste.generateXLS();
		else rste.readXLS();
	}
	

}
