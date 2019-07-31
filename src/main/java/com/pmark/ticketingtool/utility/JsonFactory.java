package com.pmark.ticketingtool.utility;

import com.pmark.ticketingtool.model.abstractmodel.JSONBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;


public class JsonFactory {
	
	
	
	public static String ok() {
		
		JSONObject jo = new JSONObject();
		
		jo.put("RESULT", "OK");
		
		
		return jo.toString();
	}
	
	public static String result(JSONObject o) {
		
		JSONObject res = new JSONObject();
		
		res.put("RESULT", o);
		
		return res.toString();
	}
	
	public static String result(JSONArray jo) {

		
		JSONObject res = new JSONObject();
		res.put("RESULT", jo);
		
		return res.toString();
	}
	
	public static String error(String ex) {
		JSONObject jo = new JSONObject();
		jo.put("ERROR", ex);
		
		return jo.toString();
	}

	public static JSONArray toJArray(List<? extends JSONBuilder> list) throws Exception {
		if (list.size() == 0 || list == null)
			return new JSONArray();
		JSONArray ja = new JSONArray();
        for (JSONBuilder y : list) {
            ja.put(y.toJson());
        }
        return ja;
	}

}
