package interpreter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AttributeType extends GeneralTypeInterpreter{
	private static AttributeType instance;
	private AttributeType(){
		configListTag = "at";
	}
	public static AttributeType getInstance(){
		if (instance == null){
			instance = new AttributeType();
		}
		return instance;
	}
	/**
	 * 
	 * @param atName
	 * @return a JSONObject including the name, type and unit
	 */
	public JSONObject getAttribute(String atName){
		JSONArray atList;
		try {
			atList = getConfigList();
		
			for (int i = 0; i < atList.length(); i++ ){
				JSONObject obj = atList.getJSONObject(i);
				if (obj.getString("name").equals(atName))
					return obj;
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
