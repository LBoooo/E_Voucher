package com.evoucher.accv.e_voucher.model;

import com.evoucher.accv.e_voucher.application.AppApplication;
import com.evoucher.accv.e_voucher.application.Config;
import com.evoucher.accv.e_voucher.contract.LoginContract;
import com.evoucher.accv.e_voucher.http.XHttp;
import com.evoucher.accv.e_voucher.model.bean.User;
import com.evoucher.accv.e_voucher.model.entity.LoginEntity;
import com.evoucher.accv.e_voucher.utils.DatabaseHelper;
import com.evoucher.accv.e_voucher.utils.GsonUtils;
import org.xutils.http.RequestParams;

/**
 * Created by 李小白 on 2017/9/8.
 * Email WorkerLiBai@163.com
 */

public class LoginModel implements LoginContract.Model {
    @Override
    public void login(String account, String password, LoginContract.OnLoginListener listener) {
        loginHttp(account, password, listener);
    }
    
//    @Override
//    public void sign(String account, String password, LoginContract.onSignListener listener) {
//        signHttp(account, password, listener);
//    }
    
    
    private void loginHttp(final String account, final String password, final LoginContract.OnLoginListener listener) {
        RequestParams rp = new RequestParams(Config.LOGIN);
        rp.addBodyParameter("username", account);
        rp.addBodyParameter("password", password);
        rp.addBodyParameter("code", "");
        XHttp.post(rp, new XHttp.HttpCallBack() {
            @Override
            public void onResponse(String result) {
                LoginEntity le = GsonUtils.gsonToEntity(result, LoginEntity.class);
                User user = new User();
                user.userName = account;
                user.passWord = password;
                user.userId = le.getFlag().getRetDetail().getUserCode();
                DatabaseHelper.getInstance().saveUserToDatabase(user);
                AppApplication.user = user;
                listener.loginSuccess();
            }
            
            @Override
            public void onError(String err) {
                listener.loginFail(err);
            }
            
            @Override
            public void onFinish() {
                
            }
        });
    }
    
//    private void signHttp(String account, String password, final LoginContract.onSignListener listener) {
//        RequestParams rp = new RequestParams("url");
//        rp.addBodyParameter("", account);
//        rp.addBodyParameter("", password);
//        XHttp.post(rp, new XHttp.HttpCallBack() {
//            @Override
//            public void onResponse(String result) {
//                listener.signSuccess();
//            }
//
//            @Override
//            public void onError(String err) {
//                listener.signFail();
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        });
//    }
}
