package utility;

import java.util.LinkedList;
import java.util.List;

public class JSONArray {

private List<JSON> jsonList = new LinkedList<>();
	
	public void addJSON(JSON json){
		
		jsonList.add(json);
	}
	
	public String toString(){
		
		String jsonArray = "[";
		
		String seperator = "";
		
		if(jsonList.size() > 1) seperator = ",";
		
		for(JSON json : jsonList){
			
			jsonArray += (json.toString());
			jsonArray += (seperator);
		}
		
		if(jsonArray.endsWith(",")) jsonArray = jsonArray.substring(0, jsonArray.length()-1);
		
		jsonArray += "]";
		
		return jsonArray;
	}

}
