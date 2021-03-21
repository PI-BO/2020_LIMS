package model.database.dummyDB;

import java.util.LinkedList;
import java.util.List;
import utility.KeyValuePair;

public class DummyResultSetEntry {

	private List<KeyValuePair> entry = new LinkedList<>();
	
	public void addKeyValuePair(String key, String value){
		entry.add(new KeyValuePair(key, value));
	}
	
	public int size(){
		return entry.size();
	}
	
	public KeyValuePair get(int columnIndex){
		return entry.get(columnIndex);
	}
	
	public void printKeyValuePair(){
		System.out.println("----------------KEY_VALUE_PAIR----------------");
		for(KeyValuePair kv : entry){
			System.out.println("key: " + kv.getKey() + "\tvalue: " + kv.getValue());
		}
		System.out.println("----------------------------------------------");
	}
}
