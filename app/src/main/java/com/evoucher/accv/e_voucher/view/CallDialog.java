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
import android.widget.Button;

import com.evoucher.accv.e_voucher.R;

/**
 * Created by 李小白 on 2017/9/12.
 * Email WorkerLiBai@163.com
 */

public class CallDialog extends DialogFragment {
    private onDialogClickListener listener;
    
    public interface onDialogClickListener {
        void onCallListener();
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_call_salesman, container, false);
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getDialog().getWindow() != null)
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button call = v.findViewById(R.id.callBtn);
        Button cancel = v.findViewById(R.id.cancelBtn);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onCallListener();
                dismiss();
            }
        });
        
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return v;
    }
    
    public CallDialog setListener(onDialogClickListener listener) {
        this.listener = listener;
        return this;
    }
}
