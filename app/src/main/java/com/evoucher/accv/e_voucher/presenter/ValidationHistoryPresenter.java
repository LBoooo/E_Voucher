package com.evoucher.accv.e_voucher.presenter;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.evoucher.accv.e_voucher.contract.ValidationHistoryContract;
import com.evoucher.accv.e_voucher.model.ValidationHistoryModel;

/**
 * Created by 李小白 on 2017/9/14.
 * Email WorkerLiBai@163.com
 */

public class ValidationHistoryPresenter implements ValidationHistoryContract.Presenter {
    ValidationHistoryModel model;
    ValidationHistoryContract.View view;
    TextView[] views;
    
    public ValidationHistoryPresenter(ValidationHistoryContract.View view) {
        this.model = new ValidationHistoryModel();
        this.view = view;
        views = this.view.switchViews();
    }
    
    
    @Override
    public void switchBtn(View view) {
        
        for (int i = 0; i < views.length; i++) {
            
            if (views[i].getId() == view.getId()){
                views[i].setBackgroundColor(0xffff4444);
                views[i].setTextColor(0xffffffff);
            }else {
                views[i].setBackgroundColor(0xffffffff);
                views[i].setTextColor(0xffff4444);
            }
        }
    }
}
