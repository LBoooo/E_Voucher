package com.evoucher.accv.e_voucher.view.activity;

import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.contract.RegisterContract;
import com.evoucher.accv.e_voucher.presenter.RegisterPresenter;
import com.evoucher.accv.e_voucher.utils.ToastUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by 李小白 on 2017/9/26.
 * Email WorkerLiBai@163.com
 */
@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity implements RegisterContract.View {
    @ViewInject(R.id.phoneEt)
    EditText phoneEt;
    @ViewInject(R.id.codeEt)
    EditText codeEt;
    @ViewInject(R.id.passwordEt)
    EditText passwordEt;
    @ViewInject(R.id.loginBtn)
    Button loginBtn;
    @ViewInject(R.id.getCodeTv)
    TextView getCodeTv;
    @ViewInject(R.id.againPasswordEt)
    EditText againPasswordEt;
    boolean isCheck, isCanClick;
    
    CountDownTimer timer = new CountDownTimer(60000, 1000) {
        
        @Override
        public void onTick(long millisUntilFinished) {
            getCodeTv.setText(millisUntilFinished / 1000 + "秒后重新获取");
        }
        
        @Override
        public void onFinish() {
            getCodeTv.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            getCodeTv.setEnabled(true);
            getCodeTv.setText("发送验证码");
        }
    };
    
    RegisterPresenter presenter;
    
    @Override
    protected void initData() {
        presenter = new RegisterPresenter(this);
        presenter.onTextWatch(phoneEt, codeEt, passwordEt , againPasswordEt);
        isCheck = isCanClick = false;
        loginBtn.setEnabled(false);
    }
    
    @Event(value = {R.id.getCodeTv, R.id.loginCheckImg, R.id.loginBtn , R.id.cleanPswImg , R.id.againCleanPswImg})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.getCodeTv:
               if (presenter.onGetCode(phoneEt.getText().toString().trim())){
                   getCodeTv.setTextColor(ContextCompat.getColor(getContext(), R.color.small_grey));
                   getCodeTv.setEnabled(false);
                   timer.start();
               }else {
                   return;
               }
                break;
            case R.id.loginCheckImg:
                check((ImageView) view);
                break;
            case R.id.loginBtn:
                presenter.onRegister(
                        phoneEt.getText().toString().trim(),
                        codeEt.getText().toString().trim(),
                        passwordEt.getText().toString().trim(),
                        againPasswordEt.getText().toString().trim(),isCheck);
                showLoading();
                break;
            case R.id.cleanPswImg:
                passwordEt.setText("");
                break;
            case R.id.againCleanPswImg:
                againPasswordEt.setText("");
                break;
        }
    }
    
    private void check(ImageView img) {
        if (isCheck) {
            img.setImageResource(R.mipmap.un_check);
            isCheck = false;
        } else {
            img.setImageResource(R.mipmap.check);
            isCheck = true;
        }
        onButtonSate(isCanClick);
    }
    
    @Override
    public void onRegisterSuccess() {
        hideLoading();
        ToastUtil.showToast(getContext() , "注册成功");
        
    }
    
    @Override
    public void onRegisterFail(String err) {
        hideLoading();
        ToastUtil.showToast(getContext() , err);
        
    }
    
    @Override
    public void onButtonSate(boolean isCanClick) {
        this.isCanClick = isCanClick;
        if (isCanClick && isCheck) {
//            loginBtn.setBackgroundColor(0xFFFFFFFF);
            loginBtn.setEnabled(true);
        } else {

//            loginBtn.setBackgroundColor(0xFFBBBBBB);
            loginBtn.setEnabled(false);
        }
    }
    
    @Override
    public void onGetCodeSuccess() {
        ToastUtil.showToast(getContext() , "验证码发送成功");
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
