package com.evoucher.accv.e_voucher.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.evoucher.accv.e_voucher.BuildConfig;
import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.contract.BillContract;
import com.evoucher.accv.e_voucher.presenter.BillPresenter;
import com.evoucher.accv.e_voucher.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by 李小白 on 2017/9/9.
 * Email WorkerLiBai@163.com
 */

@ContentView(R.layout.activity_bill_details)
public class BillDetailsActivity extends BaseActivity implements BillContract.View {
    @ViewInject(R.id.billLv)
    ListView billLv;
    
    BillPresenter billPresenter;
    BillAdapter adapter;
    @ViewInject(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    
    @Override
    protected void initData() {
        setTitleText("账单明细");
        billPresenter = new BillPresenter(this);
        adapter = new BillAdapter(this);
        billLv.setAdapter(adapter);
//        ClassicsHeader header = (ClassicsHeader) refreshLayout.getRefreshHeader();
//        Drawable drawable = header.getProgressView().getDrawable();
//        if (drawable instanceof LayerDrawable) {
//            drawable = ((LayerDrawable) drawable).getDrawable(0);
//        }
//        header.setEnableLastTime(false);
//        header.setSpinnerStyle(SpinnerStyle.Scale);
        
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                billPresenter.obtainBill();
//            }
//        });
//
//        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//                ToastUtil.showToast(getContext() , "加载更多");
//
//                refreshlayout.finishLoadmore(false);
//            }
//        });
        
        setOnRefreshListener(refreshLayout , new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                billPresenter.obtainBill();
            }
        }, new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ToastUtil.showToast(getContext() , "加载更多");
    
                refreshlayout.finishLoadmore(false);
            }
        });
    }
    
    
    @Override
    public void onObtainBillSuccess() {
        refreshLayout.finishRefresh(true);
        adapter.setData();
    }
    
    @Override
    public void onObtainBillFail() {
        refreshLayout.finishRefresh(false);
        ToastUtil.showToast(this , "获取数据失败");
    }
    
    @Override
    public void onObtainBillFinish() {
       
        
    }
}
