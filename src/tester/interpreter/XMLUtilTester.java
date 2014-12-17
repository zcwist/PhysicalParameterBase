package tester.interpreter;

import interpreter.XMLUtil;

import org.json.JSONException;

public class XMLUtilTester {

	/**
	 * @param args
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws JSONException {
		// TODO Auto-generated method stub
		XMLUtil xmlUtil = new XMLUtil("AttributeType");
		System.out.println(xmlUtil.getContentInJson());
	}

}
