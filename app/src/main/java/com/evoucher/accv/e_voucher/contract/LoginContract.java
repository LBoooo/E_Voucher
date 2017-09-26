package com.evoucher.accv.e_voucher.contract;

import android.widget.EditText;

/**
 * Created by 李小白 on 2017/9/8.
 * Email WorkerLiBai@163.com
 */

public interface LoginContract {
    interface Model {
        void login(String account , String password , OnLoginListener listener );
//        void sign(String account , String password , onSignListener listener);
    }
    
    interface View {
        void loginSuccess();
        void loginFail(String err);
        
//        void signSuccess();
//        void signFail(String err);
        
        void onButtonSate(boolean isCanClick);
    }
    
    interface Presenter {
        void onTextWatch(EditText e1 , EditText e2);
        void login(String account , String password, boolean isCheck);
//        void sign(String account , String password);
    }
    
    interface OnLoginListener{
        void loginSuccess();
        void loginFail(String err);
    }
    
//    interface onSignListener{
//        void signSuccess();
//        void signFail();
//    }
}
