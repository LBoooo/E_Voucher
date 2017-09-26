package com.evoucher.accv.e_voucher.presenter;

import com.evoucher.accv.e_voucher.contract.CodeVerificationContract;
import com.evoucher.accv.e_voucher.model.CodeVerificationModel;

/**
 * Created by 李小白 on 2017/9/8.
 * Email WorkerLiBai@163.com
 */

public class CodeVerificationPresenter implements CodeVerificationContract.Presenter {
    CodeVerificationModel model;
    CodeVerificationContract.View view;
    
    
    public CodeVerificationPresenter(CodeVerificationContract.View view) {
        this.model = new CodeVerificationModel();
        this.view = view;
    }
    
    
    @Override
    public void verificationCode(String code) {
        model.verificationCode(code , this);
    }
    
    @Override
    public void verificationCodeSuccess(String result) {
        view.verificationCodeSuccess(result);
    }
    
    @Override
    public void verificationCodeFail(String err) {
        view.verificationCodeFail(err);
    }
}
