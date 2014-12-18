package interpreter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HasAtTag extends HasPublicType {
	/**
	 * By selecting the configuration, a set of fields will be formed into record with all of values null
	 */
	protected void formFields(int i){
		super.formFields(i);
		try {
			JSONArray fieldList = (JSONArray)((JSONObject) getConfigList().get(i)).get("at");
			for (int j = 0; j < fieldList.length(); j++){
				recordRequest.put(fieldList.getString(j),"");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
