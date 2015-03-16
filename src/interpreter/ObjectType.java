package interpreter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import webutil.CatagoryTree;

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
	
	/**
	 * 
	 */
	public JSONArray getAtList(int i)
	{
		try {
			return ((JSONObject)getConfigList().get(i)).getJSONArray("at");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * insert a new Object to DB
	 * @param aObject Object
	 * @param atIndex the order of the attribute list of the object
	 */
	public void insert(JSONObject aObject, int atIndex)
	{
		super.insert(aObject);
		CatagoryTree.updateTree(aObject, this.getAtList(atIndex));
	}
}
