package com.evoucher.accv.e_voucher.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.view.other.AbsBaseAdapter;
import com.evoucher.accv.e_voucher.view.other.MineBillItemDecoration;
import com.zhy.autolayout.utils.AutoUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 李小白 on 2017/9/19.
 * Email WorkerLiBai@163.com
 */
@ContentView(R.layout.activity_mine_bill)
public class MineBillActivity extends BaseActivity {
    @ViewInject(R.id.mineBillLv)
    ListView mineBillLv;
    @ViewInject(R.id.headMineBillView)
    View headView;
    @ViewInject(R.id.headMineBillMonthTv)
    TextView headTv;
    
    List<String> list = new ArrayList<>();
    
    @Override
    protected void initData() {
        for (int i = 0; i < 20; i++) {
            
            if (i == 0 || i == 5 || i == 10 || i == 8 || i == 13 || i == 16) {
                list.add("a");
            } else {
                list.add("--i--");
            }
            
        }
        
        setTitleBackdrop(ContextCompat.getColor(getContext() , R.color.white));
        setTitleMoreImg(INVISIBLE);
        mineBillLv.setAdapter(new MineBillAdapter(getContext()));
        mineBillLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                
            }
            
            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                Log.d("MineBillActivity", "i:" + i);
                if (i >= 0) {

                    headView.setVisibility(View.VISIBLE);
                    
                    if (list.get(i).equals("a")) {
                        headTv.setText("月份1234" + i);
                    }
                    
                    
                } else {
                    headView.setVisibility(View.INVISIBLE);
                    
                }
            }
        });
        
        mineBillLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!list.get(i).equals("a")) {
                    startActivity(new Intent(getContext() , BillDetailsActivity.class));
                }
            }
        });
    }
    
    @Event(value = {R.id.calendarBtn})
    private void chooseDate(View view){
        startActivity(new Intent(getContext() , DatePickActivity.class));
    }
    
    private class MineBillAdapter extends AbsBaseAdapter {
        boolean isMonth = false;
        
        public MineBillAdapter(Context context) {
            super(context);
        }
        
        @Override
        protected int setItemView() {
            if (isMonth)
                return R.layout.item_mine_bill_month;
            else
                return R.layout.item_mine_bill;
        }
        
        @Override
        protected void intiData(int position, AbsBaseViewHolder holder) {
            
        }
        
        @Override
        protected int setCount() {
            return 20;
        }
        
        @Override
        public int getItemViewType(int position) {
            if (list.get(position).equals("a")) {
                isMonth = true;
                return 0;
            } else {
                isMonth = false;
                return 1;
            }
        }
        
        @Override
        public int getViewTypeCount() {
            return 2;
        }
        
        @Override
        protected AbsBaseViewHolder setHolder(View view, int position) {
            return new MineBillHolder(view);
        }
        
        private class MineBillHolder extends AbsBaseViewHolder {
            
            public MineBillHolder(View view) {
                super(view);
            }
        }
    }
}
