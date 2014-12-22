package interpreter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParameterType extends GeneralTypeInterpreter {
	private static ParameterType instance;
	private ParameterType(){
		configListTag = "pt";
	}
	public static ParameterType getInstance(){
		if (instance == null){
			instance = new ParameterType();
		}
		return instance;
	}
	
	/**
	 * Get the parameter name in ParameterType.xml
	 * @param pid
	 * @return parameter name
	 */
	
	public JSONArray getParametersName(int pid){
		JSONArray atList;
		try {
			atList= getConfigList();
			
			for (int i = 0; i < atList.length(); i++){
				JSONObject obj = atList.getJSONObject(i);
				if (Integer.valueOf(obj.get("pid").toString()) == pid){
					return JSONUtil.Object2JsonArray(obj.get("at"));
				}
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
