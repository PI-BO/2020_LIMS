package utility;

import java.util.LinkedList;
import java.util.List;
import utility.JSONArray;
import utility.KeyValuePair;

public class JSON {
	
	private List<KeyValuePair>keyValuePairList = new LinkedList<>();
	
	public void addKeyValue(String key, String value){
		
		KeyValuePair keyValuePair = new KeyValuePair(key, value);
		keyValuePairList.add(keyValuePair);
	}
	
	public void addJSONArray(String key, JSONArray jsonArray){
		
		KeyValuePair keyValuePair = new KeyValuePair(key, jsonArray.toString());
		keyValuePairList.add(keyValuePair);
	}
	
	public void addJSON(String key, JSON json){
		
		KeyValuePair keyValuePair = new KeyValuePair(key, json.toString());
		keyValuePairList.add(keyValuePair);
	}
	
	public String toString(){
		
		String json = "{";
		
		String seperator = "";
		
		if(keyValuePairList.size() > 1) seperator = ",";
		
		for(KeyValuePair keyValuePair : keyValuePairList){
			
			String key = keyValuePair.getKey();
			String value = keyValuePair.getValue();
			
			if(key == null) key = "";
			if(value == null) value = "";
			
			String quote = "\"";
			
			json += (quote);
			json += (key);
			json += (quote);
			json += (":");
			if(isArray(value)) quote = "";
			if(isJSON(value)) quote = "";
			json += (quote);
			json += (value);
			json += (quote);
			json += (seperator);
		}
		
		if(json.endsWith(",")) json = json.substring(0, json.length()-1);
		
		json += "}";
		
		return json;
	}

	private boolean isArray(String value) {
		return value.startsWith("[");
	}
	
	private boolean isJSON(String value) {
		return value.startsWith("{");
	}

}
