package com.evoucher.accv.e_voucher.utils;

import com.evoucher.accv.e_voucher.application.Config;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by 李小白 on 2017/9/22.
 * Email WorkerLiBai@163.com
 */

public class LogUtil {
    private static AndroidLogAdapter logAdapter;
    public static void init() {
        logAdapter = new AndroidLogAdapter(PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(0)        // (Optional) Skips some method invokes in stack trace. Default 5
//                .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag(Config.TAG)   // (Optional) Custom tag for each log. Default PRETTY_LOGGER
                .build()) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return Config.DEBUG;
            }
        };
    }
    
    
    public static void v(String message, Object... args) {
        Logger.addLogAdapter(logAdapter);
        Logger.v(message, args);
        Logger.clearLogAdapters();
    }
    
    public static void d(String message, Object... args) {
        Logger.addLogAdapter(logAdapter);
        Logger.d(message, args);
        Logger.clearLogAdapters();
    }
    
    public static void e(String message, Object... args) {
        Logger.addLogAdapter(logAdapter);
        Logger.e(message, args);
        Logger.clearLogAdapters();
    }
    
    public static void json(String json) {
        Logger.addLogAdapter(logAdapter);
        Logger.json(json);
        Logger.clearLogAdapters();
    }
    
    public static void minute(String message, Object... args){
        Logger.addLogAdapter(new AndroidLogAdapter(PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(2)         // (Optional) How many method line to show. Default 2
                .methodOffset(5)        // (Optional) Skips some method invokes in stack trace. Default 5
//                .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag(Config.TAG)   // (Optional) Custom tag for each log. Default PRETTY_LOGGER
                .build()) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return Config.DEBUG;
            }
        });
        Logger.d(message, args);
        Logger.clearLogAdapters();
    }
    
}
