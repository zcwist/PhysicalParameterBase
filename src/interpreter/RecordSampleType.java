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
			System.out.println(recordRequest);
			
			//Parameter List
//			recordRequest.put("pid", pidList);
			JSONArray pidValueList= new JSONArray();
			for (int j = 0; j < pidList.length(); j++){ // to each pid
				JSONObject parameterList = new JSONObject();
				int pid = Integer.valueOf(pidList.getInt(j));
				parameterList.put("pid", pid);
				
				JSONArray atNameList = ParameterType.getInstance().getParametersName(pid);
				JSONArray atList = new JSONArray();
				for (int k = 0; k < atNameList.length(); k++){
					JSONObject parameter = new JSONObject();
					String name = atNameList.getString(k);
					
					JSONObject attribute = AttributeType.getInstance().getAttribute(name);
					parameter.put("unit", attribute.get("unit"));
					parameter.put("type", attribute.get("type"));
					parameter.put("atType", name);
					parameter.put("value", "");
					atList.put(parameter);
				}
				parameterList.put("at", atList);
				pidValueList.put(parameterList);
				
			}
			recordRequest.put("pid", pidValueList);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insert(JSONObject aObject){
		String sid;
		String oid;
//		JSONArray pidValueList;
		try {
			sid = aObject.get("sid").toString();
			oid = ObjectType.getOid(sid);
			aObject.put("oid", oid);
			
			super.insert(aObject);
			
//			pidValueList = (JSONArray) aObject.get("pid");
//			Updatable recordType = (Updatable) new RecordType();
//			recordType.update(oid,pidValueList);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public void insert(JSONObject aObject,boolean refresh){
		String sid;
		String oid;
		JSONArray pidValueList;
		try {
			sid = aObject.get("sid").toString();
			oid = ObjectType.getOid(sid);
			aObject.put("oid", oid);
			
			super.insert(aObject);
			System.out.println(aObject);
			
			if(refresh){
				pidValueList = (JSONArray) aObject.get("pid");
				Updatable recordType = (Updatable) new RecordType();
				recordType.update(oid,pidValueList);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
