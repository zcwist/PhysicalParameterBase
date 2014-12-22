package interpreter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecordSampleType extends HasAtTag {
	public RecordSampleType(){
		configListTag = "rst";
	}
	public void formFields(int i){
		super.formFields(i);
		try {
			JSONArray pidList = JSONUtil.Object2JsonArray(((JSONObject)getConfigList().get(i)).get("pid"));
			recordRequest.put("pid", pidList);
			for (int j = 0; j < pidList.length(); j++){
				int pid = Integer.valueOf(pidList.getInt(j));
				JSONArray atList = ParameterType.getInstance().getParametersName(pid);
				for (int k = 0; k < atList.length(); k++){
					recordRequest.put(atList.getString(k), "");
				}
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insert(JSONObject aObject){
		super.insert(aObject);
		JSONArray pidList;
		String sid;
		try {
			pidList = (JSONArray) aObject.get("pid");
			sid = aObject.getString("sid");
			Updatable recordType = (Updatable) new RecordType();
			recordType.update(sid,pidList);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
