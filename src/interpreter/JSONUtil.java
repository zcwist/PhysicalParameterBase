package interpreter;

import org.json.JSONArray;

public class JSONUtil {
	//A JSONObject will be returned if there is only one sub-element, while a JSONArray will be returned when there are two or more sub-elements.
	public static JSONArray Object2JsonArray(Object obj){
		String objClassName = obj.getClass().getSimpleName();
		if (objClassName.equals("JSONArray")) return (JSONArray)obj;
		JSONArray arr = new JSONArray();
		arr.put(obj);
		return arr;
	}

}
