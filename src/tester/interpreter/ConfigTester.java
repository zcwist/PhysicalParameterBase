package tester.interpreter;

import interpreter.AttributeType;
import interpreter.ObjectType;
import interpreter.ParameterType;
import interpreter.RecordSampleType;
import interpreter.RecordType;
import interpreter.SampleType;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

import dao.MongoWrapper;

public class ConfigTester {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		attributeTypeTester();
		objectTypeTester();
//		mongoAndObjectTypeTester();
//		parameterTypeTester();
//		recordTypeTester();
//		recordSampleTypeTester(true);
//		sampleTypeTester();
//		recordType();
//		trial2();
//		mongoDemo();
		
	}
	
	public static void trial1(){
		try {
			JSONObject objA = new JSONObject().append("a", "a");
			objA.append("a", "a");
			JSONObject objB= new JSONObject().accumulate("b", "b");
			objB.accumulate("b", "b");
			System.out.println(objA);
			System.out.println(objB);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 	{"a":["a"]}
			{"b":"b"}
		 */
	}
	public static void trial2(){
		BasicDBObject objA = new BasicDBObject();
		objA.append("a", "a");
		objA.append("a", "c");
		BasicDBObject objB= new BasicDBObject();
		objB.put("b", "b");
		objB.put("b", "d");
		System.out.println(objA);
		System.out.println(objB);
	}
	
	
	public static void mongoDemo(){
		String json = "{\"atIndex\":\"1\"\"type:dolloar\":{\"olddegree\":[\"5$\"]}}";
		//BasicDBObject obj = (BasicDBObject)JSON.parse(json);
		JSONObject query = new JSONObject();
		
		JSONObject value = null;
		try {
			query.accumulate("atIndex", "1");
			value = new JSONObject(json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(value);
		MongoWrapper.getInstance().update(query, value, "tree");
	}
	
	public static void attributeTypeTester(){
		System.out.println(AttributeType.getInstance().getConfig());
		System.out.println(AttributeType.getInstance().getAttribute("环压强度"));
		
	}
	
	public static void objectTypeTester(){
		ObjectType objectType = new ObjectType();
//		System.out.println(objectType.getPublicList());
//		System.out.println(objectType.getConfigList());
//		System.out.println(objectType.getFields(1));
		JSONObject aObject;
		try {
			aObject = new JSONObject("{\"介质类型\":\"美元\",\"oid\":\"1\",\"新旧程度\":\"五\",\"ObjectName\":\"五成新美元\"}");
			objectType.insert(aObject,0);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(objectType.getAtList(0));
//		System.out.println(objectType.getConfig());
		
	}
	public static void sampleTypeTester(){
//		SampleType sampleType = new SampleType();
//		System.out.println(sampleType.getFields().toString().replace("\"", "'"));
		System.out.println(SampleType.getSampleTypeName("1"));
		
//		JSONObject aObject;
//		try {
//			aObject = new JSONObject("{'sid':'1','oid':'1','贯字号':'001'}");
//			sampleType.insert(aObject);
//		} catch (JSONException e){
//			e.printStackTrace();
//		}
		
	}
	public static void mongoAndObjectTypeTester(){
		ObjectType objectType = new ObjectType();
		objectType.insert(AttributeType.getInstance().getConfig());
	}
	public static void parameterTypeTester(){
		System.out.println(ParameterType.getInstance().getParametersName("1"));
	}
	public static void recordTypeTester(){
		RecordType record = new RecordType();
		System.out.println(record.getConfig());
	}
	public static void recordSampleTypeTester(boolean toInsert){
		RecordSampleType record = new RecordSampleType();
//		System.out.println(record.getFields(0).toString().replace("\"", "'"));
//		System.out.println(record.getFields(0).toString());
		if (toInsert){
			JSONObject aObject;
			try{
				aObject = new JSONObject("{'sid':'1','pidList':[{'at':{'atType':'杨氏模量','unit':'GPa','value':'0.66','type':'float'},'pid':'1'}],'测试条件描述':'helloword','rsid':'1','测试照片':'no'}");
				record.insert(aObject, true);
			}catch (JSONException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void recordType(){
		RecordType record = new RecordType();
		System.out.println(record.getConfig());
	}
	
}
