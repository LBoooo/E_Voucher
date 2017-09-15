package com.evoucher.accv.e_voucher.contract;

import android.view.View;
import android.widget.TextView;

/**
 * Created by 李小白 on 2017/9/14.
 * Email WorkerLiBai@163.com
 */

public interface ValidationHistoryContract {
    interface Model {
    }
    
    interface View {
        TextView[] switchViews();
        
    }
    
    interface Presenter {
        
        void switchBtn(android.view.View view);
    }
}
