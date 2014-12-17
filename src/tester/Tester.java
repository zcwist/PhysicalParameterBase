package tester;

import java.net.UnknownHostException;

import dao.MongoWrapper;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			MongoWrapper mw = new MongoWrapper();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
