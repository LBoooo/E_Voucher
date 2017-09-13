package com.evoucher.accv.e_voucher.http;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 李小白 on 2017/9/8.
 * Email WorkerLiBai@163.com
 */

public class XHttp {
    public interface HttpCallBack {
        void onResponse(String result);
        
        void onError();
        
        void onFinish();
    }
    
    public static void post(RequestParams rp, final HttpCallBack httpCallBack) {
        x.http().post(rp, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (httpCallBack != null)
                    httpCallBack.onResponse(result);
            }
            
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if (httpCallBack != null)
                    httpCallBack.onError();
            }
            
            @Override
            public void onCancelled(CancelledException cex) {
                if (httpCallBack != null)
                    httpCallBack.onError();
            }
            
            @Override
            public void onFinished() {
                httpCallBack.onFinish();
            }
            
            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }
    
    public static void get(RequestParams rp, final HttpCallBack httpCallBack) {
        x.http().get(rp, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                httpCallBack.onResponse(result);
            }
            
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                httpCallBack.onError();
            }
            
            @Override
            public void onCancelled(CancelledException cex) {
                httpCallBack.onError();
            }
            
            @Override
            public void onFinished() {
                httpCallBack.onFinish();
            }
        });
    }
}
