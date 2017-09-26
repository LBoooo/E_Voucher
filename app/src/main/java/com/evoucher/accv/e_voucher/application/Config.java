package com.evoucher.accv.e_voucher.application;

/**
 * Created by 李小白 on 2017/9/11.
 * Email WorkerLiBai@163.com
 */

public class Config  {
    public final static boolean DEBUG = true;
    
    public final static String TAG = "E_APP";
    
    private final static String BASEURL = "http://192.168.1.126/accv-appser-web/";
    public final static String TIMEURL = "http://www.ntsc.ac.cn";
//    private final static String BASEURL = "https://mpt.accvmedia.com/accv-appser-web/";
    
    public final static String LOGIN = BASEURL+"login.json";
    public final static String CODE = BASEURL+ "app_server/do_coupon.json";
    public final static String REGISTER = BASEURL +"register.json";
    public final static String SENDCODE = BASEURL +"app_server/sendMsg.json";
    
}
