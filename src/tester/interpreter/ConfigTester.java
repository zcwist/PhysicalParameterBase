package tester.interpreter;

import interpreter.AttributeType;
import interpreter.ObjectType;

public class ConfigTester {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		attributeTypeTester();
		objectTypeTester();
//		mongoAndObjectTypeTester();
	}
	
	public static void attributeTypeTester(){
		System.out.println(AttributeType.getInstance().getConfig());
		System.out.println(AttributeType.getInstance().getAttribute("环压强度"));
		
	}
	
	public static void objectTypeTester(){
		ObjectType objectType = new ObjectType();
		System.out.println(objectType.getPublicList());
		System.out.println(objectType.getConfigList());
		System.out.println(objectType.getFields(1));
//		JSONObject aObject;
//		try {
//			aObject = new JSONObject("{\"介质类型\":\"美元\",\"oid\":\"1\",\"新旧程度\":\"五成新\",\"ObjectName\":\"五成新美元\"}");
//			objectType.insert(aObject);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	public static void mongoAndObjectTypeTester(){
		ObjectType objectType = new ObjectType();
		objectType.insert2db(AttributeType.getInstance().getConfig());
	}
	
}
