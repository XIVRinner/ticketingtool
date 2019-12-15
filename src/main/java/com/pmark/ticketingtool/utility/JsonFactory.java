package com.pmark.ticketingtool.utility;

import com.pmark.ticketingtool.model.abstractmodel.JSONBuilder;
import lombok.NonNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import static java.util.Objects.isNull;


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

	public static String warn(String warn){
		JSONObject jo = new JSONObject();
		jo.put("WARN", warn);

		return jo.toString();
	}

	public static String toJArray(@NonNull List<? extends JSONBuilder> list) throws Exception {
		if (list.size() == 0)
			return result(new JSONObject());
		JSONArray ja = new JSONArray();
        for (JSONBuilder y : list) {
            ja.put(y.toJson());
        }
        return result(ja);
	}

}
