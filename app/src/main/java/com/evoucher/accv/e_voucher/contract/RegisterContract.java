package com.evoucher.accv.e_voucher.contract;

import android.widget.EditText;

/**
 * Created by 李小白 on 2017/9/26.
 * Email WorkerLiBai@163.com
 */

public interface RegisterContract {
    interface Model {
        void onRegister(String phone , String code,String password , RegisterContract.Presenter presenter);
        
        void onGetCode(String phone , RegisterContract.Presenter presenter);
    }
    
    interface View {
        void onRegisterSuccess();
        
        void onRegisterFail(String err);
        
        void onButtonSate(boolean isCanClick);
        
        void onGetCodeSuccess();
    }
    
    interface Presenter {
        void onTextWatch(EditText e1, EditText e2, EditText e3 , EditText e4);
        
        void onRegister(String phone, String code, String password ,String againPsw, boolean isCheck);
    
        boolean onGetCode(String phone);
        
        void onRegisterFail(String err);
        
        void onRegisterSuccess();
        
        void onSendCodeFail(String err);
        
        void onSendCodeSuccess();
    }
}
