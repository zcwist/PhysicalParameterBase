package interpreter;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;

import dao.MongoWrapper;

public class SampleType extends HasAtTag {
	public SampleType(){
		configListTag = "st";
	}
	
	/**
	 * Get the Sample Type Name JSON
	 * @param sid sample id
	 * @return the data excluding _id, sid, oid.
	 */
	
	public static JSONObject getSampleTypeName(String sid){
		BasicDBObject query = new BasicDBObject().append("sid", sid);
		BasicDBObject ref = new BasicDBObject().append("_id", 0).append("sid",0).append("oid", 0);
		BasicDBObject queryResult = MongoWrapper.getInstance().getSelectedValueFromColl(query,ref, "SampleType");
		return JSONUtil.basicDBObject2JSONObject(queryResult);
	}

}
