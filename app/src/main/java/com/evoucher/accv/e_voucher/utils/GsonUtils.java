package com.evoucher.accv.e_voucher.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 李小白 on 2017/9/13.
 * Email WorkerLiBai@163.com
 */

public class GsonUtils<T> {
    
    private GsonUtils(){
        
    }
    
    private static Gson gson = null;
    
    static {
        if (gson == null) {
            gson = new Gson();
        }
    }
    
    /**
     * 转成json
     */
    public static String entityToGson(Object object) {
        String json = null;
        if (gson != null) {
            json = gson.toJson(object);
        }
        return json;
    }
    
    /**
     * 解析
     */
    public static <T> T gsonToEntity(String json, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(json, cls);
        }
        return t;
    }
    
    /**
     * 转成list
     * 泛型在编译期类型被擦除导致报错
     */
    public static <T> List<T> GsonToListEntity(String json, Class<T> cls) {
        List<T> list = null;
        if (gson != null) {
            list = gson.fromJson(json, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }
    
    /**
     * 转成list
     * 解决泛型问题
     */
    public List<T> GsonToList(String json, Class<T> cls) {
        List<T> list = new ArrayList<>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            if (gson != null)
                list.add(gson.fromJson(elem, cls));
        }
        return list;
    }
    
    
    /**
     * 转成list中有map的
     */
    public static <T> List<Map<String, T>> GsonToListMaps(String json) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(json, new TypeToken<List<Map<String, T>>>() {
            }.getType());
        }
        return list;
    }
    
    /**
     * 转成map的
     */
    public static <T> Map<String, T> GsonToMaps(String json) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(json, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }
    
}
