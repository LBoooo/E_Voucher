package com.evoucher.accv.e_voucher.model;

import android.os.Handler;

import com.evoucher.accv.e_voucher.contract.QualificationContract;
import com.evoucher.accv.e_voucher.http.XHttp;

import org.xutils.http.RequestParams;

import java.io.File;

/**
 * Created by 李小白 on 2017/9/26.
 * Email WorkerLiBai@163.com
 */

public class QualificationModel implements QualificationContract.Model {
    @Override
    public void onUpLoad(String merchantName, String merchantAddress, String path, final QualificationContract.Presenter presenter) {
        RequestParams rp = new RequestParams();
        rp.setMultipart(true);
        rp.addBodyParameter("", merchantName);
        rp.addBodyParameter("", merchantAddress);
        rp.addBodyParameter("file", new File(path));
        XHttp.post(rp, new XHttp.HttpCallBack() {
            @Override
            public void onResponse(String result) {
                presenter.onUpLoadSuccess();
            }

            @Override
            public void onError(String err) {
                presenter.onUploadFail(err);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
