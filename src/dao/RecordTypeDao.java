package dao;

import interpreter.JSONUtil;

import org.json.JSONArray;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

public class RecordTypeDao extends GeneralDao{
	
	public RecordTypeDao()
	{
		collName = "RecordType";
	}

	/**
	 * Get the record of the object selected by oid
	 * @param oid the oid of the object
	 * @return all record belong to the object
	 */
	public JSONArray getItemsByOid(String oid){
		BasicDBObject query = new BasicDBObject().append("oid", oid);
//		System.out.println(query.toString());
		return CursorToJsonArray(mongo.getObjectFromColl(query, collName));
	}
	
	protected JSONArray CursorToJsonArray(DBCursor result){
		JSONArray resultJson = new JSONArray();
		while(result.hasNext()){
			BasicDBObject obj = (BasicDBObject) result.next();
			obj.remove("_id");
			System.out.println(obj);
			resultJson.put(JSONUtil.basicDBObject2JSONObject(obj));
		}
		return resultJson;
		
	}
}
