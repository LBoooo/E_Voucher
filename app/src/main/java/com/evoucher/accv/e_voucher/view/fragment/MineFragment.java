package com.evoucher.accv.e_voucher.view.fragment;

import android.content.Intent;
import android.view.View;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.view.activity.AccountBalanceActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * Created by 李小白 on 2017/9/18.
 * Email WorkerLiBai@163.com
 */

@ContentView(R.layout.fragment_mine)
public class MineFragment extends BaseFragment {
    @Override
    protected void initData() {
        
    }
    
    @Event(value = {R.id.balanceRechargeBtn})
    private void onClick(View view){
        switch (view.getId()){
            case R.id.balanceRechargeBtn:
                startActivity(new Intent(getContext(), AccountBalanceActivity.class));
                break;
            
        }
    }
}
