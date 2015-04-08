package interpreter.calculate;

import java.text.DecimalFormat;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Average implements CalculateStrategyInterface {

	public JSONObject calculate(JSONObject datasource) {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		@SuppressWarnings("rawtypes")
		Iterator iter = datasource.keys();
		while (iter.hasNext()){
			String key = iter.next().toString();
			JSONArray valueList;
			try {
				valueList = (JSONArray)datasource.get(key);
				double sum = 0;
				for (int i = 0; i < valueList.length(); i++){
					sum += Double.valueOf(valueList.get(i).toString());
				}
				DecimalFormat df = new DecimalFormat("#.##");
				result.put(key, df.format(sum / valueList.length()));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return result;
	}

}
