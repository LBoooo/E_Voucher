package com.evoucher.accv.e_voucher.view;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.evoucher.accv.e_voucher.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by 李小白 on 2017/9/11.
 * Email WorkerLiBai@163.com
 */

@ContentView(R.layout.activity_effect_monitoring)
public class EffectMonitoringActivity extends BaseActivity {
    @ViewInject(R.id.listView)
    ListView listView;
    
    @Override
    protected void initData() {
        setTitleText("效果监测");
        listView.setAdapter(new EffAdapter(this));
    }
    
    
    private class EffAdapter extends AbsBaseAdapter{
    
        public EffAdapter(Context context) {
            super(context);
        }
    
        @Override
        protected int setItemView() {
            return R.layout.item_effect_monitoring;
        }
    
        @Override
        protected void intiData(int position, AbsBaseViewHolder holder) {
            EffHolder eh = (EffHolder) holder;
            
            
        }
    
        @Override
        protected int setCount() {
            return 10;
        }
    
        @Override
        protected AbsBaseViewHolder setHolder(View view) {
            return new EffHolder(view);
        }
        
        private class EffHolder extends AbsBaseViewHolder{
    
            EffHolder(View view) {
                super(view);
            }
        }
    }
}
