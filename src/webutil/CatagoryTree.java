package webutil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import dao.MongoWrapper;

public class CatagoryTree {
	
	private static MongoWrapper mongo = MongoWrapper.getInstance();
	/**
	 * when a new object is created, put it into the tree 
	 * @param jsonObj this new object
	 * @param atList the attribute ordered list of the object
	 */
	public static void updateTree(JSONObject jsonObj,JSONArray atList, int atIndex)
	{
		//a tree is a json, which is not a good solution
		
		BasicDBObject query = new BasicDBObject();
		query.append("atIndex", String.valueOf(atIndex+1));
		BasicDBObject result = mongo.getOneObjectFromColl(query, "tree");
		if (result == null)
		{
			JSONObject newJson = new JSONObject();
			String key;
			try {
				key = atList.getString(atList.length()-1);
			
				key = key +":" + jsonObj.getString(key);
				newJson.append(key, jsonObj.get("ObjectName"));
				for (int i = atList.length()-2; i>=0; i--)
				{
	
					key = atList.getString(i) + ":" + jsonObj.getString(atList.getString(i));
					System.out.println(key);
					JSONObject tmp = new JSONObject(newJson.toString());
					newJson = new JSONObject().append(key,tmp);
					
				}
				newJson.accumulate("atIndex", String.valueOf(atIndex+1));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(newJson);
			mongo.insertDatatoCollection(newJson, "tree");
				
		} else {
			System.out.println(result);
		}
	}
	
	public static void updateTree(JSONObject jsonObj, JSONArray atList)
	{
		String parentNode = null;
		for (int i = 0; i < atList.length(); i++)
		{
			try {
				String nodeName = atList.getString(i) + ":" + jsonObj.getString(atList.getString(i));
				insert2DB(genItem(nodeName,parentNode));
				parentNode = nodeName;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		try {
			String nodeName = jsonObj.getString("ObjectName");
			String oid = jsonObj.getString("oid");
			insert2DB(genItem(nodeName,parentNode,oid));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static BasicDBObject genItem(String nodeName, String parentNode)
	{
		BasicDBObject dbObj = new BasicDBObject();
		dbObj.append("_id", nodeName);
		dbObj.append("parent", parentNode);
		return dbObj;
	}
	
	private static BasicDBObject genItem(String nodeName, String parentNode, String oid)
	{
		BasicDBObject dbObj = genItem(nodeName,parentNode);
		dbObj.append("oid", oid);
		return dbObj;
		
	}
	
	private static void insert2DB(BasicDBObject item)
	{
		mongo.update(item, item, "tree");
	}
	
	public static JSONObject getNodesByParentNode(String parentNodeName)
	{
		BasicDBObject query = new BasicDBObject().append("parent", parentNodeName);
		DBCursor cursor = mongo.getObjectFromColl(query, "tree");
		JSONObject result = new JSONObject();
		
		try {
			while (cursor.hasNext())
			{
				BasicDBObject item = (BasicDBObject) cursor.next();
				item.remove("parent");
				result.append("child", item);
				
			}
			result.accumulate("_id", parentNodeName);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return result;
	}
	

}
