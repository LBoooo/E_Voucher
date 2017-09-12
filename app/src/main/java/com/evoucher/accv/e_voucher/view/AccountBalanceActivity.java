package com.evoucher.accv.e_voucher.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.evoucher.accv.e_voucher.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by 李小白 on 2017/9/9.
 * Email WorkerLiBai@163.com
 */

@ContentView(R.layout.activity_account_balance)
public class AccountBalanceActivity extends BaseActivity {
    
    TextView billDetailsTv;
    
    @Override
    protected void initData() {
        setTitleText("账号余额");
        
    }
    
    @Event(value = {R.id.billDetailsTv})
    private void onClick(View view){
        switch (view.getId()){
            case R.id.billDetailsTv:
                
                startActivity(new Intent(this , BillDetailsActivity.class));
                break;
        }
    }
    
    
   
}
