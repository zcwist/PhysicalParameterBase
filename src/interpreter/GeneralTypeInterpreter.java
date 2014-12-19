package interpreter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GeneralTypeInterpreter {
	protected String className; 
	protected JSONObject config = null; //all config file in json
	protected String configListTag;// different type has different tag, e.g the tag of ObjcetType is ot

	protected int configNo;
	
	GeneralTypeInterpreter(){
		this.className = this.getClass().getSimpleName();
		XMLUtil xmlUtil = new XMLUtil(this.className);
		try {
			config = xmlUtil.getContentInJson().getJSONObject(this.className);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * @return all the config file in JSON
	 */
	public JSONObject getConfig(){
		if (config != null)
			return config;
		return null;
	}
	
	/**
	 * 
	 * @return the configuration list for each type except the public e.g."[{"alias":"什么介质？多新？","at":["介质类型","新旧程度"]},{"alias":"什么介质？多大面额？","at":["介质类型","面额"]}]"
	 */
	public JSONArray getConfigList(){
		try {
			return JSONUtil.Object2JsonArray(config.get(configListTag));
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			return config.getJSONArray(configListTag);
		}catch(JSONException e){
//			e.printStackTrace();
		}
		try {
			//when there is only one element, a jsonobject, rather than a jsonarray will be returned.
			JSONArray obj2arr = new JSONArray();
			obj2arr.put(config.getJSONObject(configListTag));
			return obj2arr;
		} catch (JSONException e){
//			e.printStackTrace();
		}
		
		return null;
	}
	
}
