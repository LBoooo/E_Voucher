package com.evoucher.accv.e_voucher.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.view.other.VHPageListAdapter;
import com.evoucher.accv.e_voucher.view.w.AnimatedExpandableListView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by 李小白 on 2017/9/11.
 * Email WorkerLiBai@163.com
 */

@ContentView(R.layout.fragment_vhpager)
public class VHPageFragment extends BaseFragment {
    
    private int mPage;
    public static final String ARG_PAGE = "ARG_PAGE";
    
    @ViewInject(R.id.vhListView)
    AnimatedExpandableListView vhListView;
    
    public static VHPageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        VHPageFragment pageFragment = new VHPageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }
    
    @Override
    protected void initData() {
        mPage = getArguments().getInt(ARG_PAGE);
    
        // 取消贼丑的箭头
        vhListView.setGroupIndicator(null);
    
        vhListView.setAdapter(new VHPageListAdapter(getContext()));
    
        //默认第一组打开
        vhListView.expandGroupWithAnimation(0);
    
        //点击分组打开或关闭时添加动画
        vhListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
          
                if (vhListView.isGroupExpanded(groupPosition)){
    
                    vhListView.collapseGroupWithAnimation(groupPosition);
                }else{
                    vhListView.expandGroupWithAnimation(groupPosition);
                }
                return true;
            }
        });
    }
    
    
    
}
