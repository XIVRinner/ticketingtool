package com.pmark.ticketingtool.model.abstractmodel;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.json.JSONObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;


public abstract class JSONBuilder {


    public JSONObject toJson() throws Exception {
        List<Field> fields = Arrays.asList(FieldUtils.getAllFields(this.getClass()));


        JSONObject jo = new JSONObject();
        for (Field i : fields) {
            i.setAccessible(true);
            List<Annotation> annotation = Arrays.asList(i.getDeclaredAnnotations());
            int skipper = annotation.indexOf(JSONBuilderSkipper.class);
            boolean hasSkipper = false;
            if(skipper >= 0){
                Annotation an = annotation.get(skipper);
                boolean include = (boolean) an.getClass().getDeclaredMethod("include").invoke(an);
                hasSkipper = !include;
            }

            if(!hasSkipper) {
                jo.put(i.getName(), FieldUtils.readField(i, this));

            }
            i.setAccessible(false);
        }
        return jo;

    }


}
