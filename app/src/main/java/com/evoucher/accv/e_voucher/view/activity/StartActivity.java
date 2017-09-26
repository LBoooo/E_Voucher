package com.evoucher.accv.e_voucher.view.activity;

import android.content.Intent;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.application.AppApplication;
import com.evoucher.accv.e_voucher.utils.DatabaseHelper;

import org.xutils.view.annotation.ContentView;

/**
 * Created by 李小白 on 2017/9/21.
 * Email WorkerLiBai@163.com
 */

@ContentView(R.layout.activity_start)
public class StartActivity extends BaseActivity {
    
    @Override
    protected void initData() {
        start((AppApplication.user = DatabaseHelper.getInstance().getLastUser())!= null);
    }
    
    private void start(boolean isLogin){
        if (isLogin){
            startActivity(new Intent(getContext() , SelectActivity.class));
        }else {
            startActivity(new Intent(getContext() , LoginActivity.class));
        }
        finish();
    }
}
