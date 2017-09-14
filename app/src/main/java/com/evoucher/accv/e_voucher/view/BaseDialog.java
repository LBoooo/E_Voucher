package com.evoucher.accv.e_voucher.view;

import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import org.xutils.view.annotation.Event;

/**
 * Created by 李小白 on 2017/9/12.
 * Email WorkerLiBai@163.com
 */

public class BaseDialog extends DialogFragment {
    public final static String LAYOUT = "layout";
    
    private int layout;
    private onDialogContentClickListener listener;
    
    protected void setOnDialogContentClickListener(onDialogContentClickListener listener){
        this.listener = listener;
    }
    
    protected BaseDialog setLayout(int layout){
        this.layout = layout;
        return this;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    
    protected void showNow(){
        show(getFragmentManager() , "");
    }
}
