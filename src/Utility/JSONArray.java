package utility;

import java.util.LinkedList;
import java.util.List;

public class JSONArray {

private List<JSON> jsonList = new LinkedList<>();
private List<String> stringList = new LinkedList<>();
	
	public void addJSON(JSON json){
		
		jsonList.add(json);
	}
	
	public void addString(String string){
		
		stringList.add(string);
	}
	
	public String toString(){
		
		String jsonArray = "[";
		
		String seperator = ",";
		
		for(JSON json : jsonList){
			
			jsonArray += (json.toString());
			jsonArray += (seperator);
		}
		
		for(String string : stringList){
			
			jsonArray += ("\"");
			jsonArray += (string);
			jsonArray += ("\"");
			jsonArray += (seperator);
		}
		
		
		if(jsonArray.endsWith(",")) jsonArray = jsonArray.substring(0, jsonArray.length()-1);
		
		jsonArray += "]";
		
		return jsonArray;
	}

}
