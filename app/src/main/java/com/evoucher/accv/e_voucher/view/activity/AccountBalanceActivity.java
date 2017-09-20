package com.evoucher.accv.e_voucher.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.evoucher.accv.e_voucher.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by 李小白 on 2017/9/9.
 * Email WorkerLiBai@163.com
 */

@ContentView(R.layout.activity_account_balance)
public class AccountBalanceActivity extends BaseActivity {
    @ViewInject(R.id.rechargeEt)
    EditText rechargeEt;
    @ViewInject(R.id.rechargeBtn)
    Button rechargeBtn;
    
    BottomDialog bottomDialog;
    @Override
    protected void initData() {
        setTitleBackdrop(ContextCompat.getColor(getContext() , R.color.white));
        setTitleText("账号余额");
        rechargeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        
            }
    
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        
            }
    
            @Override
            public void afterTextChanged(Editable editable) {
        
                if (editable != null && !editable.toString().trim() .isEmpty() && !editable.toString().trim().equals("")){
                    rechargeBtn.setBackgroundColor(ContextCompat.getColor(getContext() , R.color.theme_color_0));
                    rechargeBtn.setEnabled(true);
                }else {
                    rechargeBtn.setEnabled(false);
                    rechargeBtn.setBackgroundColor(ContextCompat.getColor(getContext() , R.color.light_grey));
                }
            }
        });
    
        bottomDialog = new BottomDialog();
    }
    
    @Event(value = {R.id.billDetailsTv , R.id.rechargeBtn})
    private void onClick(View view){
        switch (view.getId()){
            case R.id.billDetailsTv:
                
                startActivity(new Intent(this , MineBillActivity.class));
                break;
            case R.id.rechargeBtn:
    
                bottomDialog.show(getSupportFragmentManager() , "");
                break;
        }
    }
    
    
  
    public static class BottomDialog extends android.support.v4.app.DialogFragment {
        
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
            Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);

//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
            View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_bottom , null);
            Button confirm = v.findViewById(R.id.confirmPaymentBtn);
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "123", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.setContentView(v);
            dialog.setCanceledOnTouchOutside(true); // 外部点击取消
            //dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//去掉这句话，背景会变暗
            // 设置宽度为屏宽, 靠近屏幕底部。
            Window window = dialog.getWindow();
            assert window != null;
            WindowManager.LayoutParams lp = window.getAttributes();
//            dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
            lp.gravity = Gravity.BOTTOM; // 紧贴底部
            lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
            window.setAttributes(lp);
            window.setWindowAnimations(R.style.BottomDialog_Animation);
            return dialog;
        }
    }
   
}
