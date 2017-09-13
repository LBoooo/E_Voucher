package com.evoucher.accv.e_voucher.model;

import com.evoucher.accv.e_voucher.contract.BillContract;
import com.evoucher.accv.e_voucher.http.XHttp;

import org.xutils.http.RequestParams;

/**
 * Created by 李小白 on 2017/9/9.
 * Email WorkerLiBai@163.com
 */

public class BillModel implements BillContract.Model {
    
    @Override
    public void obtainBill(BillContract.Presenter presenter) {
        obtainBillOnHttp(presenter);
    }
    
    
    private void obtainBillOnHttp(final BillContract.Presenter presenter) {
        RequestParams params = new RequestParams("url");
//        params.addBodyParameter();
        XHttp.post(params, new XHttp.HttpCallBack() {
            @Override
            public void onResponse(String result) {
                presenter.onObtainBillSuccess();
            }
    
            @Override
            public void onError() {
                presenter.onObtainBillFail();
            }
    
            @Override
            public void onFinish() {
                presenter.onFinish();
            }
        });
    }
}
