package com.evoucher.accv.e_voucher.application;

import android.app.Application;

import com.evoucher.accv.e_voucher.BuildConfig;
import com.evoucher.accv.e_voucher.model.bean.User;
import com.evoucher.accv.e_voucher.utils.DatabaseHelper;
import com.evoucher.accv.e_voucher.utils.LogUtil;
import com.evoucher.accv.e_voucher.utils.PermissionHelper;
import com.evoucher.accv.e_voucher.utils.SmartRefreshUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhy.autolayout.config.AutoLayoutConifg;

import org.xutils.x;

/**
 * Created by 李小白 on 2017/9/8.
 * Email WorkerLiBai@163.com
 */

public class AppApplication extends Application {
    public static User user;
    
    
    @Override
    public void onCreate() {
        super.onCreate();
    
        AutoLayoutConifg.getInstance().useDeviceSize();
        // 初始化xUtils
        x.Ext.init(this);
        x.Ext.setDebug(false); // 是否输出debug日志, 开启debug会影响性能.
    
        ZXingLibrary.initDisplayOpinion(this); // 初始化二维码
        
        DatabaseHelper.getInstance().init(); // 初始化数据库
    
        new SmartRefreshUtils();  // 初始化刷新样式
     
        LogUtil.init();  // 初始化log
    }
}
