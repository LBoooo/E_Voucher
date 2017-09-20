package com.evoucher.accv.e_voucher.view.activity;

import android.support.v4.content.ContextCompat;
import android.widget.ListView;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.contract.BillContract;
import com.evoucher.accv.e_voucher.presenter.BillPresenter;
import com.evoucher.accv.e_voucher.utils.ToastUtil;
import com.evoucher.accv.e_voucher.view.other.BillAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by 李小白 on 2017/9/9.
 * Email WorkerLiBai@163.com
 */

@ContentView(R.layout.activity_bill_details)
public class BillDetailsActivity extends BaseActivity implements BillContract.View {
    
    BillPresenter billPresenter;
    
    @Override
    protected void initData() {
        setTitleText("账单详情");
        setTitleBackdrop(ContextCompat.getColor(getContext() , R.color.white));
        setTitleMoreImg(INVISIBLE);
        billPresenter = new BillPresenter(this);


    }
    
    
    @Override
    public void onObtainBillSuccess() {


    }
    
    @Override
    public void onObtainBillFail() {

    }
    
    @Override
    public void onObtainBillFinish() {
       
        
    }
}
