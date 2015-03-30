package tester.util;

import webutil.CatagoryTree;

public class UtilTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CatagoryTreeTester();

	}

	public static void CatagoryTreeTester(){
		System.out.println(CatagoryTree.getChildrenNodes("5519408fe691add1f87ec843").toString());
	}
}
