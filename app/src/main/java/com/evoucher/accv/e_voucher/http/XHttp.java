package com.evoucher.accv.e_voucher.http;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
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
    
    /**
     * 上传文件
     *
     * @param path
     * @param url
     */
    public static void postFile(String path, String url) {
        path = "/mnt/sdcard/Download/icon.jpg";
        RequestParams params = new RequestParams(url);
        params.setMultipart(true);
        params.addBodyParameter("file", new File(path));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
            }
            
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            
            @Override
            public void onCancelled(CancelledException cex) {
            }
            
            @Override
            public void onFinished() {
            }
        });
    }
    
    /***
     * 下载文件
     * @param url
     * @param path
     */
    public static void downloadFile(String url, String path) {
        url = "http://127.0.0.1/server/abc.apk";
        RequestParams params = new RequestParams(url);
        //自定义保存路径，Environment.getExternalStorageDirectory()：SD卡的根目录
        params.setSaveFilePath(path);  //Environment.getExternalStorageDirectory()+"/myapp/"
        //自动为文件命名
        params.setAutoRename(true);
        x.http().post(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File result) {
//                //apk下载完成后，调用系统的安装方法
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setDataAndType(Uri.fromFile(result), "application/vnd.android.package-archive");
//                getActivity().startActivity(intent);
            }
            
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            
            @Override
            public void onCancelled(CancelledException cex) {
            }
            
            @Override
            public void onFinished() {
            }
            
            //网络请求之前回调
            @Override
            public void onWaiting() {
            }
            
            //网络请求开始的时候回调
            @Override
            public void onStarted() {
            }
            
            //下载的时候不断回调的方法
            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                //当前进度和文件总大小
                Log.i("JAVA", "current：" + current + "，total：" + total);
            }
        });
    }
}
