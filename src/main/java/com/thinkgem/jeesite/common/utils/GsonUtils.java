package com.thinkgem.jeesite.common.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zongjh on 16-3-10.
 */
public class GsonUtils {

    /**
     * 将object转成json串
     * @param obj
     * @return
     */
    public static String getJsonFromObject(Object obj) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(obj);
    }

    public static String getJsonFromObjectByExpose(Object obj) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(obj);
    }


    public static String getJsonFromObjectBySinceUntil(int version, Object obj) {
        Gson gson = new GsonBuilder().setVersion(version).create();
        return gson.toJson(obj);
    }

    public static String getJsonFromObjectModifityProtected(Object obj) {
        Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.PROTECTED).create();
        return gson.toJson(obj);
    }

    public static String getJsonFromObjectByList(final List<String> needs, Object obj) {
        Gson gson = new GsonBuilder()
                .addSerializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                        if (needs != null && needs.contains(fieldAttributes.getName())) {
                            return false;
                        }
                        return true;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> aClass) {
                        return false;
                    }
                }).create();
        return gson.toJson(obj);
    }

    public static <T> T getObjectFromJson(String json, Type typeOfT) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.fromJson(json, typeOfT);
    }

    public static <T> T getObjectFromJsonBySelf(String json, final List<String> needs, Type typeOfT) {
        Gson gson = new GsonBuilder()
                .addDeserializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                        if (needs != null && needs.contains(fieldAttributes.getName())) {
                            return false;
                        }
                        return true;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> aClass) {
                        return false;
                    }
                }).create();
        return gson.fromJson(json, typeOfT);
    }

    public static <T> List<T> getListFromJson(String jsonStr, Class<T> classT) {
        List<T> list = null;
        try {
            if (jsonStr != null && !jsonStr.equals("")) {

                JSONObject jSONObject = JSONObject.fromObject(jsonStr);
                int success = jSONObject.getInt("code");
                if (success == 0) {
                    String data = jSONObject.getString("data");
                    if (data != null && !data.equals("")) {
                        JSONArray jsonarray = JSONArray.fromObject(data);
                        list = new ArrayList<T>();
                        list = JSONArray.toList(jsonarray, classT);
                    }

                }

            }
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    
}
