package com.evoucher.accv.e_voucher.http;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by 李小白 on 2017/9/8.
 * Email WorkerLiBai@163.com
 */

public class XHttp {
    public interface HttpCallBack{
        void onSuccess(String result);
        void onError();
    }
    
    public void post( RequestParams rp ,final HttpCallBack httpCallBack){
        x.http().post(rp, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (httpCallBack != null)
                httpCallBack.onSuccess(result);
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
        
            }
    
            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }
}
