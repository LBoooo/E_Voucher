package com.evoucher.accv.e_voucher.view.other;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.evoucher.accv.e_voucher.R;

/**
 * Created by 李小白 on 2017/9/20.
 * Email WorkerLiBai@163.com
 */

public abstract class AbsBaseDialog extends DialogFragment {
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getDialog().getWindow() != null)
            getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View view = inflater.inflate(initLayout(), container, false);
        initView(view);
        return view;
    }
    
    protected abstract int initLayout();
    
    protected abstract void initView(View view);
    
    
    
}
