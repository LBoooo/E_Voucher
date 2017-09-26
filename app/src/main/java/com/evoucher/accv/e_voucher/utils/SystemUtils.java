package com.evoucher.accv.e_voucher.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.view.activity.MainActivity;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

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
    
    public static void changeToTheme(Activity activity, int theme, boolean type) {
        sTheme = theme;
        activity.startActivity(new Intent(activity, MainActivity.class).putExtra("type", type));
    }
    
    public static void onActivityCreateSetTheme(Activity activity) {
        if (sTheme > 0)
            activity.setTheme(sTheme);
        else
            activity.setTheme(R.style.AppTheme);
    }
    
    public static int getThemeColor(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.theme_color, typedValue, true);
        return typedValue.data;
    }
    
    
    /**
     * 这是使用adb shell命令来获取mac地址的方式
     */
    public static String getMac() {
        String macSerial = null;
        String str = "";
        try {
            Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (IOException ex) {
            // 赋予默认值
            ex.printStackTrace();
        }
        return macSerial;
    }
    
    
    public static String getWifiMac(Context ctx) {
        @SuppressLint("WifiManagerPotentialLeak")
        WifiManager wifi = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        @SuppressLint("HardwareIds")
        String str = info.getMacAddress();
        if (str == null) str = "";
        return str;
    }
}
