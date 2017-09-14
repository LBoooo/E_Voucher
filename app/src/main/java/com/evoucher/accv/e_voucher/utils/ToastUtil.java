package com.evoucher.accv.e_voucher.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 李小白 on 2017/9/13.
 * Email WorkerLiBai@163.com
 */

public class ToastUtil {
    private static Toast toast;
    
    public static void showToast(Context context,
                                 String content) {
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}
