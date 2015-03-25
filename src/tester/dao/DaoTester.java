package tester.dao;

import dao.RecordSampleTypeDao;
import dao.RecordTypeDao;

public class DaoTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		RecordTypeDaoTester();
		RecordSampleTypeTester();
		
	}
	
	public static void RecordTypeDaoTester(){
		System.out.println(new RecordTypeDao().getItemsByOid("1"));
	}

	public static void RecordSampleTypeTester(){
		System.out.println(new RecordSampleTypeDao().getRecordSampleByOidPid("1","3"));
	}
}
