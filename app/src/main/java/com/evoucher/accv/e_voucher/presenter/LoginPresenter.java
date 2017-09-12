package com.evoucher.accv.e_voucher.presenter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.evoucher.accv.e_voucher.contract.LoginContract;
import com.evoucher.accv.e_voucher.model.LoginModel;

/**
 * Created by 李小白 on 2017/9/8.
 * Email WorkerLiBai@163.com
 */

public class LoginPresenter implements LoginContract.Presenter, LoginContract.OnLoginListener, LoginContract.onSignListener {
    private LoginModel loginModel;
    private LoginContract.View view;
    private boolean ise1NotNull = false;
    private boolean ise2NotNull = false;
    public LoginPresenter(LoginContract.View view) {
        this.loginModel = new LoginModel();
        this.view = view;
    }
    
    @Override
    public void onTextWatch(EditText e1, EditText e2) {
        
        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
                ise1NotNull = editable != null && !editable.toString().trim().equals("");
                view.onButtonSate(ise1NotNull && ise2NotNull);
            }
        });
        
        e2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
                ise2NotNull = editable != null && !editable.toString().trim().equals("");
                view.onButtonSate(ise1NotNull && ise2NotNull);
            }
        });
    }
    
    @Override
    public void login(String account, String password) {
        if (account != null && !account.equals("")) {
            if (password != null && !password.equals("")) {
                loginModel.login(account, password, this);
            } else {
                view.loginFail("密码不可为空");
            }
        } else {
            view.loginFail("账号不可为空");
        }
    }
    
    @Override
    public void sign(String account, String password) {
        if (account != null && !account.equals("")) {
            if (password != null && !password.equals("")) {
                loginModel.sign(account, password, this);
            } else {
                view.signFail("密码不可为空");
            }
        } else {
            view.signFail("账号不可为空");
        }
    }
    
    @Override
    public void loginSuccess() {
        view.loginSuccess();
    }
    
    @Override
    public void loginFail() {
        view.loginFail("登录失败");
    }
    
    
    @Override
    public void signSuccess() {
        view.signSuccess();
    }
    
    @Override
    public void signFail() {
        view.signFail("注册失败");
    }
}
