package com.evoucher.accv.e_voucher.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.contract.LoginContract;
import com.evoucher.accv.e_voucher.presenter.LoginPresenter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;


@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements LoginContract.View{
    LoginPresenter loginPresenter;
    @ViewInject(R.id.loginBtn)
    Button loginBtn;
    @ViewInject(R.id.accountEt)
    EditText accountEt;
    @ViewInject(R.id.passwordEt)
    EditText passwordEt;
    @ViewInject(R.id.cleanPswImg)
    ImageView cleanPswImg;
    
    
    @Override
    protected void initData() {
        setTitleText(null);
        isShowTitleCut(false);
        loginPresenter = new LoginPresenter(this);
        loginBtn.setEnabled(false);
        loginPresenter.onTextWatch(accountEt , passwordEt);
    }
    
    @Event(value = {R.id.loginBtn , R.id.cleanPswImg})
    private void onClick(View view){
        switch (view.getId()){
            case R.id.loginBtn:
                loginPresenter.login(accountEt.getText().toString() , passwordEt.getText().toString());
                break;
            case R.id.cleanPswImg:
                passwordEt.setText("");
                break;
        }
    }
    
    
    @Override
    public void loginSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    
    @Override
    public void loginFail(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
        loginSuccess();
    }
    
    @Override
    public void signSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void signFail(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onButtonSate(boolean isCanClick) {
        if (isCanClick){
//            loginBtn.setBackgroundColor(0xFFFFFFFF);
            loginBtn.setEnabled(true);
        }else {
        
//            loginBtn.setBackgroundColor(0xFFBBBBBB);
            loginBtn.setEnabled(false);
        }
    }
    
}
