package tester.dao;

import dao.RecordTypeDao;

public class DaoTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RecordTypeDaoTester();
		
	}
	
	public static void RecordTypeDaoTester(){
		System.out.println(new RecordTypeDao().getItemsByOid("1"));
	}

}
