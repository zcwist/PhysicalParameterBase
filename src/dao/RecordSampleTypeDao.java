package dao;

import interpreter.JSONUtil;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;

public class RecordSampleTypeDao extends GeneralDao{

	public RecordSampleTypeDao()
	{
		collName = "RecordSampleType";
	}
	
	public JSONObject getRecordSampleByRsid(String rsid)
	{
		BasicDBObject query = new BasicDBObject().append("rsid",rsid);
		return JSONUtil.basicDBObject2JSONObject(mongo.getOneObjectFromColl(query, collName));
	}
}
