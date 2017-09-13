package com.evoucher.accv.e_voucher.view;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by 李小白 on 2017/9/12.
 * Email WorkerLiBai@163.com
 */

public class BaseDialog extends DialogFragment {
    public final static String LAYOUT = "layout";
    
    private int layout;
    private onDialogContentClickListener listener;
    
    /**
     * 从bundle中解析参数，args有可能来自fragment被系统回收，然后界面又重新被启动系统保存的参数；也有可能是其他使用者带过来的
     * ，具体实现交给子类
     *
     * @param args
     */
    protected void parseArgs(Bundle args) {
        layout = args.getInt(LAYOUT);
    }
    
    protected void setOnDialogContentClickListener(onDialogContentClickListener listener){
        this.listener = listener;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseArgs(getArguments());
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getDialog().getWindow() != null)
            getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(layout, container, false);
    }
    
    public interface onDialogContentClickListener{
        void onClickListener(View view);
    }
}
