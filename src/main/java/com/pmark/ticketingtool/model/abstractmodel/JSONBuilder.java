package com.pmark.ticketingtool.model.abstractmodel;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.json.JSONObject;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;


public abstract class JSONBuilder {


    public JSONObject toJson() throws Exception{
        List<Field> fields = Arrays.asList(FieldUtils.getAllFields(this.getClass()));


        JSONObject jo = new JSONObject();
        for (Field i : fields) {
            i.setAccessible(true);
            jo.put(i.getName(), FieldUtils.readField(i, this));
        }
        return jo;

    }


}
