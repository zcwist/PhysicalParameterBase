package interpreter;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

import dao.MongoWrapper;

public class HasPublicType extends GeneralTypeInterpreter {
	protected JSONObject publicList; //the public data of each type,  e.g "{"name":"ObjectName","key":"oid"}"
	protected String collName; //different type is inserted into different collection in db
	protected JSONObject recordRequest; //a json object with complete keys, but no values
	
	public HasPublicType(){
		super();
		collName = className;
		
		try {
			publicList = config.getJSONObject("public");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * By selecting the configuration, a set of fields will be formed into record with all of values null
	 */
	protected void formFields(int i){
		recordRequest = new JSONObject();
		
		//for public fields
		JSONObject publicFields = getPublicList();
		@SuppressWarnings("unchecked")
		Iterator<String> iter = publicFields.keys();
		while(iter.hasNext()){
			String key = iter.next();
			String fieldName;
			try {
				fieldName = publicFields.get(key).toString();
				recordRequest.put(fieldName, "");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public JSONObject getPublicList(){
		return publicList;
	}
	
	private void insert2db(JSONObject obj){
		BasicDBObject dbObj = (BasicDBObject)JSON.parse(obj.toString());
		
		MongoWrapper.getInstance().insertDatatoCollection(dbObj, collName);
	}
	protected JSONObject getAConfigByIndex(int i){
		try {
			return (JSONObject)getConfigList().get(i);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Check whether the obj to be insert into db is valid
	 * @param obj
	 * @return
	 */
	
	protected boolean isValid(JSONObject obj){
		return true;
	}
	
	/**
	 * Get the fields to be filled with
	 * @param the index of the config
	 * @return fields list
	 */
	public JSONObject getFields(int i){
		formFields(i);
		return recordRequest;
	}
	public JSONObject getFields(){
		formFields(0);
		return recordRequest;
	}
	
	/**
	 * Having filled with the fields, you got a aObject to be inserted into db
	 * @param aObject
	 */
	public void insert(JSONObject aObject){
		if (isValid(aObject))
			insert2db(aObject);
	}

}
