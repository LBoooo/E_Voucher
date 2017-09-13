package com.evoucher.accv.e_voucher.view;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.contract.BillContract;
import com.evoucher.accv.e_voucher.presenter.BillPresenter;

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
    @Override
    protected void initData() {
        setTitleText("账单明细");
        billPresenter = new BillPresenter(this);
        billPresenter.obtainBill();
        adapter = new BillAdapter(this);
        billLv.setAdapter(adapter);
    }
    
    
    @Override
    public void onObtainBillSuccess() {
        adapter.setData();
    }
    
    @Override
    public void onObtainBillFail() {
        Toast.makeText(this, "获取数据失败", Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public void onObtainBillFinish() {
        
    }
}
