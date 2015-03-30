package interpreter;

import interpreter.calculate.CalculateStrategyInterface;

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
	public void update(String oid, JSONArray pidList){
//		System.out.println("PidList:" + pidList);
//		e.g pidList = [{"at":{"atType":"杨氏模量","unit":"GPa","value":"0.65","type":"float"},"pid":"1"}]
		

		for (int i = 0; i < pidList.length(); i++){
			try {
				String pid = ((JSONObject)pidList.get(i)).get("pid").toString();
				JSONObject template = pidList.getJSONObject(i).getJSONObject("at");
//				System.out.println("template:" + template);
				JSONObject result = new JSONObject();
				result.put("pid", pid);
				result.put("oid", oid);
				JSONObject cal = calculate(oid, pid);
//				JSONArray resultValueList = new JSONArray();
				String atType = template.get("atType").toString();
				template.put("value", cal.get(atType));
//				resultValueList.put(template);
				
				result.put("value", template);
				JSONObject query = new JSONObject();
				query.put("oid", oid);
				query.put("pid", pid);
				MongoWrapper.getInstance().update(query, result, "RecordType");
				
				
//				System.out.println(result);
				
				

				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		System.out.println("I got an id list:" + pidList);
//		
//		System.out.println(oid);
	}
	
	/**
	 * get all the source data
	 * @param oid
	 * @param pid
	 * @return
	 * @throws JSONException
	 */
	
	private JSONObject getSource(String oid, String pid) throws JSONException{
		String parameterName = ParameterType.getInstance().getParametersName(Integer.valueOf(pid));
		JSONObject result = new JSONObject();
		result.put(parameterName, new JSONArray());
//		System.out.println(result);
		
		
		
		BasicDBObject query = new BasicDBObject();
		query.put("oid", oid);
		BasicDBObject keys = new BasicDBObject();
		keys.put("pidList", "1");
		keys.put("_id", 0);
//		System.out.println("Query:" + query.toString());
//		System.out.println("Fields" + keys.toString());
		DBCursor queryResult = MongoWrapper.getInstance().getValueFromColl(query, keys, "RecordSampleType");
		while (queryResult.hasNext()){
//			System.out.println(queryResult.next().toString());
//			e.g { "pidList" : [ { "at" : { "atType" : "杨氏模量" , "unit" : "GPa" , "value" : "0.65" , "type" : "float"} , "pid" : "1"}]}
			JSONArray parameterValueList = (JSONArray)(new JSONObject(queryResult.next().toString())).get("pidList");
			
//			System.out.println(parameterValueList);
			for (int i = 0; i < parameterValueList.length(); i++){
				JSONObject parameterValue = (JSONObject)parameterValueList.get(i);
				if (parameterValue.get("pid").toString().equals(pid)){
					JSONObject at = parameterValue.getJSONObject("at");
					result.accumulate(at.get("atType").toString(), at.get("value").toString());
	
				}
			}
		}
		System.out.println(result);
		return result;	
	}
	
	private JSONObject calculate(String oid, String pid){
		try {
			JSONObject datasource = getSource(oid,pid);
			String strategy = getStrategyName(pid);
			Class<?> demo = Class.forName("interpreter.calculate." + strategy);
			CalculateStrategyInterface cal = (CalculateStrategyInterface)demo.newInstance();
			return cal.calculate(datasource);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	private String getStrategyName(String pid){
		try {
			JSONArray ruleList = JSONUtil.Object2JsonArray(getConfig().get("rt"));
			for (int i = 0; i < ruleList.length(); i++){
				JSONObject rule = ruleList.getJSONObject(i).getJSONObject("rule");
//				System.out.println(rule);
				if (rule.get("pid").toString().equals(pid)){
					return rule.getString("content");
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}



}
