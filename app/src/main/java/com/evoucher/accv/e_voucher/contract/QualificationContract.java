package com.evoucher.accv.e_voucher.contract;

/**
 * Created by 李小白 on 2017/9/26.
 * Email WorkerLiBai@163.com
 */

public interface QualificationContract {
    interface Model {
        void onUpLoad(String merchantName , String merchantAddress ,String path , QualificationContract.Presenter presenter);
    }
    
    interface View {
        void onUploadFail(String err);
        void onUpLoadSuccess();
    }
    
    interface Presenter {
        boolean onUpLoad(String merchantName , String merchantAddress ,String path);
        void onUpLoadSuccess();
        void onUploadFail(String err);
    }
}
