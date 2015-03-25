package dao;

import interpreter.JSONUtil;
import interpreter.ObjectType;
import interpreter.SampleType;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

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
	 /**
	  * According to oid and pid from servlet, return a json containing the object name, sample name and all the attribute of the pid
	  * @param oid Object ID
	  * @param pid Parameter ID
	  * @return a json that will be rendered into a Record Sample Table
	  */
	
	public JSONObject getRecordSampleByOidPid(String oid, String pid){
		JSONObject result = new JSONObject(); // the whole result
		try {
			result.accumulate("ObjectName", ObjectType.getObjectNameByOid(oid));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BasicDBObject query = new BasicDBObject().append("oid",oid);
		DBCursor cursor = mongo.getObjectFromColl(query, collName);
		while (cursor.hasNext()){
			BasicDBObject recordItem = (BasicDBObject)cursor.next();
			JSONObject sampleName = SampleType.getSampleTypeName(recordItem.getString("sid"));
			BasicDBList pidList = (BasicDBList) recordItem.get("pid");
			
			for (Object pidItem : pidList){
				BasicDBObject pidObject = (BasicDBObject)pidItem;
				if (pidObject.get("pid").equals(pid)){
					//System.out.println(pidItem.toString());
					BasicDBList atList = (BasicDBList) pidObject.get("at");
					for (Object atItem: atList){
						BasicDBObject atObject = (BasicDBObject)atItem;
						JSONObject aRecord = new JSONObject();
						try {
							aRecord.accumulate("data", JSONUtil.basicDBObject2JSONObject(atObject));
							aRecord.accumulate("title", sampleName);
							result.append("content", aRecord);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
				}
			}

			
		}
		
		
		return result;
	}
}
