package com.evoucher.accv.e_voucher.presenter;

import com.evoucher.accv.e_voucher.contract.BillContract;
import com.evoucher.accv.e_voucher.model.BillModel;

/**
 * Created by 李小白 on 2017/9/9.
 * Email WorkerLiBai@163.com
 */

public class BillPresenter implements BillContract.Presenter {
    private BillModel model;
    private BillContract.View view;
    
    public BillPresenter(BillContract.View view) {
        this.model = new BillModel();
        this.view = view;
    }
    
    
    @Override
    public void obtainBill() {
        model.obtainBill(this);
    }
    
    @Override
    public void onObtainBillSuccess() {
        view.onObtainBillSuccess();
    }
    
    @Override
    public void onObtainBillFail() {
        view.onObtainBillFail();
    }
    
    @Override
    public void onFinish() {
        view.onObtainBillFinish();
    }
}
