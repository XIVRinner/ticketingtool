package com.pmark.ticketingtool.model.abstractmodel;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.json.JSONObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

@Slf4j
public abstract class JSONBuilder {


    public JSONObject toJson() throws Exception {
        Field[] fields = FieldUtils.getAllFields(this.getClass());


        JSONObject jo = new JSONObject();
        for (Field i : fields) {
            i.setAccessible(true);
            List<Annotation> annotation = Arrays.asList(i.getDeclaredAnnotations());
            int skipper = annotation.indexOf(JSONBuilderSkipper.class);
            int renamer = annotation.indexOf(JSONBuilderRenamer.class);
            boolean hasSkipper = false;

            if(skipper >= 0){
                Annotation an = annotation.get(skipper);
                boolean include = (boolean) an.getClass().getDeclaredMethod("include").invoke(an);
                hasSkipper = !include;
            }

            if(!hasSkipper) {
                if(renamer >= 0){
                    Annotation an = annotation.get(skipper);
                    String s = (String) an.getClass().getDeclaredMethod("key").invoke(an);
                    jo.put(s, FieldUtils.readField(i, this));
                }
                else{
                    jo.put(i.getName(), FieldUtils.readField(i, this));
                }
            }
            i.setAccessible(false);
        }
        return jo;
    }



}
