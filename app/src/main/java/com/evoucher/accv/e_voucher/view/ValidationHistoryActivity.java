package com.evoucher.accv.e_voucher.view;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.evoucher.accv.e_voucher.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by 李小白 on 2017/9/11.
 * Email WorkerLiBai@163.com
 */

@ContentView(R.layout.activity_validation_history)
public class ValidationHistoryActivity extends BaseActivity {
    @ViewInject(R.id.tabLayout)
    TabLayout tabLayout;
    @ViewInject(R.id.viewPager)
    ViewPager viewPager;
    @ViewInject(R.id.dateSelectBtn)
    Button dateSelectBtn;
    Context context;
    
    @Override
    protected void initData() {
        setTitleText("验证历史");
        context = this;
        ValidationHistoryVpAdapter pagerAdapter = new ValidationHistoryVpAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setDividerPadding(30); //设置分割线间隔
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.drawable.layout_divider_vertical));
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab == null)
                return;
            tab.setCustomView(pagerAdapter.getTabView(i));
        }
    }
    
    @Event(value = {R.id.dateSelectBtn})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.dateSelectBtn:
                
                break;
        }
    }
    
    private class ValidationHistoryVpAdapter extends FragmentPagerAdapter {
        
        ValidationHistoryVpAdapter(FragmentManager fm) {
            super(fm);
        }
        
        @Override
        public Fragment getItem(int position) {
            return VHPageFragment.newInstance(position);
        }
        
        @Override
        public int getCount() {
            return 2;
        }
        
        @Override
        public CharSequence getPageTitle(int position) {
            return null;//super.getPageTitle(position);
        }
        
        public View getTabView(int position) {
            View view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
            TextView tabItemNumTv = (TextView) view.findViewById(R.id.tabItemNumTv);
            TextView tabItemNameTv = (TextView) view.findViewById(R.id.tabItemNameTv);
            switch (position) {
                case 0:
                    tabItemNumTv.setText("321");
                    tabItemNameTv.setText("验证量(张)");
                    break;
                case 1:
                    tabItemNumTv.setText("1");
                    tabItemNameTv.setText("门店数(个)");
                    break;
            }
            
            return view;
        }
    }
}
