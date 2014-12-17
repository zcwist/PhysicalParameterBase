package interpreter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class XMLUtil {
	private String content;
	
	public XMLUtil(String fileName){
		BufferedReader reader;
		try {
			reader = new BufferedReader( new FileReader (getPath() + fileName + ".xml"));
		
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    while( ( line = reader.readLine() ) != null ) {
	        stringBuilder.append( line );
	        stringBuilder.append( ls );
	    }

	    this.content = stringBuilder.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private String getPath(){
		String path = "WebRoot/WEB-INF/config/";
		return path;
	}
	public JSONObject getContentInJson(){
		try {
			return XML.toJSONObject(content);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
