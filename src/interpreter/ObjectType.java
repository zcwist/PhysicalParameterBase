package interpreter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import dao.MongoWrapper;

public class ObjectType extends HasAtTag{
	public ObjectType(){
		configListTag = "ot";
	}
	public static String getOid(String sid){
		BasicDBObject query = new BasicDBObject();
		BasicDBObject keys = new BasicDBObject();
		query.put("sid",sid);
		keys.put("oid",1);
		keys.put("_id", 0);
		DBCursor cursor = MongoWrapper.getInstance().getValueFromColl(query,keys,"SampleType");
		if (cursor.hasNext()){
			return (String) cursor.next().get("oid");
		}
		return null;
	}
}
