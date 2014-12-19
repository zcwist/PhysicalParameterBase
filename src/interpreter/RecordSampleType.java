package interpreter;

import org.json.JSONObject;

public class RecordSampleType extends HasAtTag {
	public RecordSampleType(){
		configListTag = "rst";
	}
	
	public void insert(JSONObject aObject){
		super.insert(aObject);
		Updatable recordType = (Updatable) new RecordSampleType();
		recordType.update();
	}
}
