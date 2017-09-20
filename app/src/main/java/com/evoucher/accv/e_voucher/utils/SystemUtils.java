package com.evoucher.accv.e_voucher.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.view.activity.MainActivity;

/**
 * Created by 李小白 on 2017/9/12.
 * Email WorkerLiBai@163.com
 */

public class SystemUtils {
    //        ContextCompat.getColor() 获取颜色资源
    /**
     * 调用拨号界面
     *
     * @param phone 电话号码
     */
    public static void call(String phone, Context context) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    
    /**
     * 调用拨号功能
     *
     * @param phone 电话号码
     */
    public static void callNow(String phone, Context context) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        context.startActivity(intent);
    }
    
    private static int sTheme;
    
    public static void changeToTheme(Activity activity, int theme) {
        sTheme = theme;
        activity.startActivity(new Intent(activity, MainActivity.class));
    }
    public static void onActivityCreateSetTheme(Activity activity) {
        if ( sTheme > 0)
            activity.setTheme(sTheme);
        else
            activity.setTheme(R.style.AppTheme);
    }
    
    public static int getThemeColor(Context context){
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.theme_color , typedValue ,true);
        return typedValue.data;
    }

    
    
}
