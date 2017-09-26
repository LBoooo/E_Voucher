package com.evoucher.accv.e_voucher.model;

import android.util.Log;

import com.evoucher.accv.e_voucher.application.AppApplication;
import com.evoucher.accv.e_voucher.application.Config;
import com.evoucher.accv.e_voucher.contract.CodeVerificationContract;
import com.evoucher.accv.e_voucher.http.XHttp;

import org.xutils.http.RequestParams;

/**
 * Created by 李小白 on 2017/9/8.
 * Email WorkerLiBai@163.com
 */

public class CodeVerificationModel implements CodeVerificationContract.Model {
    
    
    @Override
    public void verificationCode(String code , final CodeVerificationContract.Presenter presenter) {
        RequestParams rp = new RequestParams(Config.CODE);
        rp.addBodyParameter("userCoe" , AppApplication.user.userId);
        rp.addBodyParameter("couponNo" , code);
        rp.addBodyParameter("openId","");
        XHttp.post(rp, new XHttp.HttpCallBack() {
            @Override
            public void onResponse(String result) {
                Log.d("CodeVerificationModel", "result:"+result);
                presenter.verificationCodeSuccess(result);
            }
    
            @Override
            public void onError(String err) {
                presenter.verificationCodeFail(err);
            }
    
            @Override
            public void onFinish() {
        
            }
        });
    }
    
   
}
