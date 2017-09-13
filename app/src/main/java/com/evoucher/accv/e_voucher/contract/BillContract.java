package com.evoucher.accv.e_voucher.contract;

/**
 * Created by 李小白 on 2017/9/9.
 * Email WorkerLiBai@163.com
 */

public interface BillContract {
    interface Model {
        void obtainBill(BillContract.Presenter presenter);
    }
    
    interface View {
        
        void onObtainBillSuccess();
        
        void onObtainBillFail();
        
        void onObtainBillFinish();
    }
    
    interface Presenter {
        void obtainBill();
    
        void onObtainBillSuccess();
    
        void onObtainBillFail();
        
        void onFinish();
    }
}
