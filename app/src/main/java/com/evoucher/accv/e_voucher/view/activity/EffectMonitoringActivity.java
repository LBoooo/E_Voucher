package com.evoucher.accv.e_voucher.view.activity;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.view.other.AbsBaseAdapter;
import com.evoucher.accv.e_voucher.view.w.TrapezoidView;

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
    
    
    private class EffAdapter extends AbsBaseAdapter {
        
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
            
//            eh.effTv0.setBackdropColor(ContextCompat.getColor(getContext(), R.color.st_blue));
//            eh.effTv0.setSize(30, new int[]{30, 40, 30}, new int[]{0, 100, 50});
//            eh.effTv0.setProgress(500);
//            eh.effTv0.setText("曝光人数", "1234", "234");
//
//            eh.effTv1.setBackdropColor(ContextCompat.getColor(getContext(), R.color.wt_blue));
//            eh.effTv1.setSize(30, new int[]{30, 40, 30}, new int[]{0, 100, 50});
//            eh.effTv1.setProgress(450);
//            eh.effTv1.setText("领券人数", "234", "34");
//
//            eh.effTv2.setBackdropColor(ContextCompat.getColor(getContext(), R.color.lt_blue));
//            eh.effTv2.setSize(30, new int[]{30, 40, 30}, new int[]{0, 100, 50});
//            eh.effTv2.setProgress(400);
//            eh.effTv2.setText("核销人数", "34", "4");
        }
        
        @Override
        protected int setCount() {
            return 10;
        }
        
        @Override
        protected AbsBaseViewHolder setHolder(View view, int position) {
            return new EffHolder(view);
        }
        
        private class EffHolder extends AbsBaseViewHolder {
            @ViewInject(R.id.effTv0)
            TrapezoidView effTv0;
            @ViewInject(R.id.effTv1)
            TrapezoidView effTv1;
            @ViewInject(R.id.effTv2)
            TrapezoidView effTv2;
            
            EffHolder(View view) {
                super(view);
            }
        }
    }
}
