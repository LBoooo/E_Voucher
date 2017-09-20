package com.evoucher.accv.e_voucher.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ListView;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.view.other.AbsBaseAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by 李小白 on 2017/9/18.
 * Email WorkerLiBai@163.com
 */

@ContentView(R.layout.activity_pay_helper)
public class PayHelperActivity extends BaseActivity {
    @ViewInject(R.id.payHelperLv)
    ListView payHelperLv;
    
    @Override
    protected void initData() {
        setTitleText("支付助手");
        setTitleBackdrop(Color.WHITE);
        setTitleMoreImg(INVISIBLE);
        payHelperLv.setAdapter(new PayHelperAdapter(this));
        
    }
    
    @Event(value = {R.id.mineBillBtn})
    private void onClick(View view){
        switch (view.getId()){
            case R.id.mineBillBtn:
                startActivity(new Intent(this , MineBillActivity.class));
                break;
        }
    }
    
    private class PayHelperAdapter extends AbsBaseAdapter {
        
        public PayHelperAdapter(Context context) {
            super(context);
        }
        
        @Override
        protected int setItemView() {
            return R.layout.item_pay_helper;
        }
        
        @Override
        protected void intiData(int position, AbsBaseViewHolder holder) {
            PayHelperHolder ph = (PayHelperHolder) holder;
            
            
        }
        
        @Override
        protected int setCount() {
            return 10;
        }
        
        @Override
        protected AbsBaseViewHolder setHolder(View view, int position) {
            return new PayHelperHolder(view);
        }
        
        private class PayHelperHolder extends AbsBaseViewHolder {
            
            private PayHelperHolder(View view) {
                super(view);
            }
        }
    }
}
