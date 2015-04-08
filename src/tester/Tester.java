package tester;

import java.text.DecimalFormat;




public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DecimalFormat df = new DecimalFormat("#.##");
		double d = 13.14159;
		System.out.println(df.format(d));
	}

}
