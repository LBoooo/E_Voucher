package com.evoucher.accv.e_voucher.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.model.bean.MessageBean;
import com.evoucher.accv.e_voucher.utils.PermissionHelper;
import com.evoucher.accv.e_voucher.utils.SystemUtils;
import com.evoucher.accv.e_voucher.utils.ToastUtil;
import com.evoucher.accv.e_voucher.view.fragment.MainFragment;
import com.evoucher.accv.e_voucher.view.fragment.MessageFragment;
import com.evoucher.accv.e_voucher.view.fragment.MineFragment;
import com.evoucher.accv.e_voucher.view.w.AutoTabLayout;
import com.evoucher.accv.e_voucher.view.w.DragPointHelper;
import com.evoucher.accv.e_voucher.view.w.DragPointView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 李小白 on 2017/9/8.
 * Email WorkerLiBai@163.com
 */

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewInject(R.id.mainTabLayout)
    AutoTabLayout mainTabLayout;
    @ViewInject(R.id.mainViewPager)
    ViewPager mainViewPager;
    MainViewPagerAdapter adapter;
    List<Fragment> fragments = new ArrayList<>();
    @Override
    protected void initData() {
//        setTitleText("主页");
        
        PermissionHelper.initPermission(this);
        final MessageFragment msgFragment = new MessageFragment();
    
        msgFragment.setCallBack(new MessageFragment.OnInitMessageNumCallBack() {
            @Override
            public void onMessageListener(int num) {
                if (num > 0) {
                    dpView.setVisibility(View.VISIBLE);
                    dpView.setText(String.valueOf(num));
//                    dpView.setText("123");
                    DragPointHelper.setAnim(getContext(), dpView, new DragPointHelper.OnDragRemoveListener() {
                        @Override
                        public void onDragRemove(DragPointView view) {
                            ToastUtil.showToast(getContext(), "移除所有消息");
                            msgFragment.removeUnreadMessage();
                        }
                    });
                } else {
                    dpView.setVisibility(View.GONE);
                }
            }
        });
        fragments.add(MainFragment.getInstance(false));
        fragments.add(msgFragment);
        fragments.add(new MineFragment());
        
        adapter = new MainViewPagerAdapter(getSupportFragmentManager() , fragments);
        mainViewPager.setAdapter(adapter);
        mainTabLayout.setupWithViewPager(mainViewPager);
        for (int i = 0; i < mainTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mainTabLayout.getTabAt(i);
            if (tab == null)
                return;
            tab.setCustomView(adapter.getTabView(i));
//            tab.setText("首页");
//            tab.setIcon(R.mipmap.ic_launcher);
        }
        
        LinearLayout linearLayout = (LinearLayout) mainTabLayout.getChildAt(0);
        linearLayout.setDividerPadding(30); //设置分割线间隔
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.drawable.layout_divider_vertical));
        
        
        mainTabLayout.setSelectedTabIndicatorColor(SystemUtils.getThemeColor(this));
    
    
    
       
    }
    
    DragPointView dpView;
    private class MainViewPagerAdapter extends FragmentPagerAdapter {
        
        String[] title = new String[]{"首页", "消息", "我的"};
       
        
        MainViewPagerAdapter(FragmentManager fm , List<Fragment> fragments) {
            super(fm);
            MainActivity.this.fragments = fragments;
        }
        
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
            
//            if (fragmentMap != null && !fragmentMap.isEmpty() && fragmentMap.get(position) != null) {
//                return fragmentMap.get(position);
//            } else {
//                switch (position) {
//                    case 1:
//                        fragmentMap.put(position, msgf);
//                        return msgf;
//                    case 2:
//                        MineFragment minf = new MineFragment();
//                        fragmentMap.put(position, minf);
//                        return minf;
//                    default:
//                        MainFragment mf = MainFragment.getInstance(true);
//                        fragmentMap.put(position, mf);
//                        return mf;
//                }
//            }
        }
        
        @Override
        public int getCount() {
            return title.length;
        }
        
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];//super.getPageTitle(position);
        }
        
        
        public View getTabView(final int position) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.tab_main_item, null);
            TextView tabNameTv = (TextView) view.findViewById(R.id.tabNameTv);
            
            tabNameTv.setText(title[position]);
            switch (position){
//                case 0:
//                    dpView.setVisibility(View.GONE);
//                    break;
                case 1:
                    dpView = view.findViewById(R.id.tabDpv);
                    break;
//                case 2:
//                    dpView.setVisibility(View.GONE);
//                    break;
            }
//            dpView.setVisibility(View.GONE);
//            DragPointHelper.setAnim(getContext(), dpView, new DragPointHelper.OnDragRemoveListener() {
//                @Override
//                public void onDragRemove(DragPointView view) {
//                    ToastUtil.showToast(getContext(), "移除所有消息");
//                    ((MessageFragment) fragmentMap.get(position)).removeUnreadMessage();
//                }
//            });
    
           
            return view;
        }
    }
    
    
}
