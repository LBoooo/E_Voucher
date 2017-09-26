package com.evoucher.accv.e_voucher.presenter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.evoucher.accv.e_voucher.contract.RegisterContract;
import com.evoucher.accv.e_voucher.model.RegisterModel;

/**
 * Created by 李小白 on 2017/9/26.
 * Email WorkerLiBai@163.com
 */

public class RegisterPresenter implements RegisterContract.Presenter {
    RegisterContract.View view;
    RegisterModel model;
    private boolean ise1NotNull = false;
    private boolean ise2NotNull = false;
    private boolean ise3NotNull = false;
    private boolean ise4NotNull = false;
    
    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
        this.model = new RegisterModel();
    }
    
    
    @Override
    public void onTextWatch(EditText e1, EditText e2, EditText e3, EditText e4) {
        
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
                view.onButtonSate(ise1NotNull && ise2NotNull && ise3NotNull && ise4NotNull);
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
                view.onButtonSate(ise1NotNull && ise2NotNull && ise3NotNull && ise4NotNull);
            }
        });
        
        e3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
                ise3NotNull = editable != null && !editable.toString().trim().equals("");
                view.onButtonSate(ise1NotNull && ise2NotNull && ise3NotNull && ise4NotNull);
            }
        });
        
        e4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }
            
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }
            
            @Override
            public void afterTextChanged(Editable editable) {
                ise4NotNull = editable != null && !editable.toString().trim().equals("");
                view.onButtonSate(ise1NotNull && ise2NotNull && ise3NotNull && ise4NotNull);
            }
        });
    }
    
    @Override
    public void onRegister(String phone, String code, String password, String againPsw, boolean isCheck) {
        if (phone != null && !phone.equals("")) {
            if (code != null && !code.equals("")) {
                if (password != null && !password.equals("")) {
                    if (againPsw != null && !againPsw.equals("")) {
                        if (isCheck) {
                            if (password.equals(againPsw)) {
                                model.onRegister(phone, code, password, this);
                            } else {
                                view.onRegisterFail("两次密码输入不一致");
                            }
                        } else {
                            view.onRegisterFail("请同意用户协议");
                        }
                    }else {
                        view.onRegisterFail("确认密码不可为空");
                    }
                } else {
                    view.onRegisterFail("密码不可为空");
                }
            } else {
                view.onRegisterFail("验证码不可为空");
            }
        } else {
            view.onRegisterFail("账号不可为空");
        }
    }
    
    @Override
    public boolean onGetCode(String phone) {
        if (phone != null && !phone.equals("")) {
            model.onGetCode(phone , this);
            return true;
        }else {
            view.onRegisterFail("请先填写手机号码");
            return false;
        }
    }
    
    @Override
    public void onRegisterFail(String err) {
        view.onRegisterFail(err);
    }
    
    @Override
    public void onRegisterSuccess() {
        view.onRegisterSuccess();
    }
    
    @Override
    public void onSendCodeFail(String err) {
        view.onRegisterFail(err);
    }
    
    @Override
    public void onSendCodeSuccess() {
        view.onGetCodeSuccess();
    }
}
