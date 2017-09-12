package com.evoucher.accv.e_voucher.model;

import android.util.Log;

import com.evoucher.accv.e_voucher.application.Config;
import com.evoucher.accv.e_voucher.contract.LoginContract;
import com.evoucher.accv.e_voucher.http.XHttp;

import org.xutils.http.RequestParams;

/**
 * Created by 李小白 on 2017/9/8.
 * Email WorkerLiBai@163.com
 */

public class LoginModel implements LoginContract.Model {
    @Override
    public void login(String account, String password,LoginContract.OnLoginListener listener) {
        loginHttp(account , password , listener);
    }
    
    @Override
    public void sign(String account, String password, LoginContract.onSignListener listener) {
        signHttp(account , password , listener);
    }
    
    
    private  void loginHttp(String account , String password , final LoginContract.OnLoginListener listener){
        RequestParams rp = new RequestParams(Config.LOGINURL);
        rp.addBodyParameter("username",account);
        rp.addBodyParameter("password",password);
        rp.addBodyParameter("code","");
        new XHttp().post(rp, new XHttp.HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.d("LoginModel", result);
                listener.loginSuccess();
            }
    
            @Override
            public void onError() {
                listener.loginFail();
            }
        });
    }
    
    private void signHttp(String account , String password ,final LoginContract.onSignListener listener){
        RequestParams rp = new RequestParams("url");
        rp.addBodyParameter("",account);
        rp.addBodyParameter("",password);
        new XHttp().post(rp, new XHttp.HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                listener.signSuccess();
            }
        
            @Override
            public void onError() {
                listener.signFail();
            }
        });
    }
}
