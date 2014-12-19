package interpreter;

import org.json.JSONObject;

public class RecordType extends HasPublicType implements Updatable{
	public RecordType(){
		configListTag = "rt";
	}
	
	//Verification of the json object 
	protected boolean isValid(JSONObject obj){
		return true;
	}
	
	public void update(){
		
	}

}
