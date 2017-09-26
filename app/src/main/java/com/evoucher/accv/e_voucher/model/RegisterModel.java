package com.evoucher.accv.e_voucher.model;

import com.evoucher.accv.e_voucher.application.Config;
import com.evoucher.accv.e_voucher.contract.RegisterContract;
import com.evoucher.accv.e_voucher.http.XHttp;

import org.xutils.http.RequestParams;

/**
 * Created by 李小白 on 2017/9/26.
 * Email WorkerLiBai@163.com
 */

public class RegisterModel implements RegisterContract.Model {
    @Override
    public void onRegister(String phone, String code, String password, final RegisterContract.Presenter presenter) {
        RequestParams rp = new RequestParams(Config.REGISTER);
        rp.addBodyParameter("username", phone);
        rp.addBodyParameter("phone", phone);
        rp.addBodyParameter("code", code);
        rp.addBodyParameter("password", password);
        
        XHttp.post(rp, new XHttp.HttpCallBack() {
            @Override
            public void onResponse(String result) {
                presenter.onRegisterSuccess();
            }
            
            @Override
            public void onError(String err) {
                presenter.onRegisterFail(err);
            }
            
            @Override
            public void onFinish() {
                
            }
        });
    }
    
    @Override
    public void onGetCode(String phone, final RegisterContract.Presenter presenter) {
        RequestParams rp = new RequestParams(Config.SENDCODE);
        rp.addBodyParameter("phone", phone);
        XHttp.post(rp, new XHttp.HttpCallBack() {
            @Override
            public void onResponse(String result) {
               presenter.onSendCodeSuccess();
            }
    
            @Override
            public void onError(String err) {
                presenter.onSendCodeFail(err);
            }
    
            @Override
            public void onFinish() {
        
            }
        });
    }
}
