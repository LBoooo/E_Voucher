package com.evoucher.accv.e_voucher.application;

import android.app.Application;

import com.evoucher.accv.e_voucher.BuildConfig;
import com.evoucher.accv.e_voucher.utils.DatabaseHelper;
import com.evoucher.accv.e_voucher.utils.PermissionHelper;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhy.autolayout.config.AutoLayoutConifg;

import org.xutils.x;

/**
 * Created by 李小白 on 2017/9/8.
 * Email WorkerLiBai@163.com
 */

public class AppApplication extends Application {
    
    @Override
    public void onCreate() {
        super.onCreate();
    
        AutoLayoutConifg.getInstance().useDeviceSize();
        // 初始化xUtils
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    
        ZXingLibrary.initDisplayOpinion(this); // 初始化二维码
        
        DatabaseHelper.getInstance().init(); // 初始化数据库
    
        
    }
}