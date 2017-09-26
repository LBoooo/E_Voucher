package com.evoucher.accv.e_voucher.presenter;

import com.evoucher.accv.e_voucher.contract.QualificationContract;
import com.evoucher.accv.e_voucher.model.QualificationModel;

/**
 * Created by 李小白 on 2017/9/26.
 * Email WorkerLiBai@163.com
 */

public class QualificationPresenter implements QualificationContract.Presenter {
    private QualificationModel model;
    private QualificationContract.View view;
    
    public QualificationPresenter(QualificationContract.View view) {
        this.view = view;
        this.model = new QualificationModel();
    }
    
    @Override
    public boolean onUpLoad(String merchantName, String merchantAddress, String path) {
        if (merchantName != null && !merchantName.equals("")) {
            if (merchantAddress != null && !merchantAddress.equals("")) {
                if (path != null && !path.equals("")){
                    model.onUpLoad(merchantName, merchantAddress , path, this);
                    return true;
                }else {
                    view.onUploadFail("请选择图片");
                    return false;
                }
            } else {
                view.onUploadFail("请填写地址");
                return false;
            }
        } else {
            view.onUploadFail("请填写商户名");
            return false;
        }
    }
    
    @Override
    public void onUpLoadSuccess() {
        view.onUpLoadSuccess();
    }
    
    @Override
    public void onUploadFail(String err) {
        view.onUploadFail(err);
    }
}
