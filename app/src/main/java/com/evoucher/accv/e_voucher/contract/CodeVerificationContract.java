package com.evoucher.accv.e_voucher.contract;

/**
 * Created by 李小白 on 2017/9/8.
 * Email WorkerLiBai@163.com
 */

public interface CodeVerificationContract {
    interface Model {
        void verificationCode(String code , Presenter presenter);
    }
    
    interface View {
        void verificationCodeSuccess(String result);
    
        void verificationCodeFail(String err);
    }
    
    interface Presenter {
        void verificationCode(String code);
        
        void verificationCodeSuccess(String result);
        
        void verificationCodeFail(String err);
    }
}
