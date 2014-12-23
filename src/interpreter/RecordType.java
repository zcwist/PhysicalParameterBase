package interpreter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import dao.MongoWrapper;

public class RecordType extends HasPublicType implements Updatable{
	public RecordType(){
		configListTag = "rt";
	}
	
	//Verification of the json object 
	protected boolean isValid(JSONObject obj){
		return true;
	}
	
	/**
	 * when a record sample is inserted into db, it make the statistics in record type collection change.
	 */
	public void update(String sid, JSONArray pidList){
		String oid = ObjectType.getOid(sid);
		for (int i = 0; i < pidList.length(); i++){
			try {
				String pid = pidList.get(i).toString();
				
				DBCursor cursor = getDocWithOidAndPid(oid,pid);
				if (docExist(cursor)){
					
				} else
				{
					
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("I got a id list:" + pidList);
		
		System.out.println(oid);
	}
	
	/**
	 * Get a doc whose oid value equals oid, pid values equals pid.
	 * @param oid ObjectType ID
	 * @param pid Parameter ID
	 * @return the doc
	 */
	
	private DBCursor getDocWithOidAndPid(String oid, String pid){
		BasicDBObject query = new BasicDBObject();
		query.put("oid", oid);
		query.put("pid", pid);
		return MongoWrapper.getInstance().getAObjectFromColl(query, "RecordType");
	}
	
	private boolean docExist(DBCursor cursor){
		if (cursor.hasNext()) return true;
		return false;
	}

}
