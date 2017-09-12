package com.evoucher.accv.e_voucher.view;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.evoucher.accv.e_voucher.R;
import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by 李小白 on 2017/9/8.
 * Email WorkerLiBai@163.com
 */

public abstract class BaseActivity extends AutoLayoutActivity {
    @ViewInject(R.id.titleTextTv)
    TextView titleTextTv;
    @ViewInject(R.id.titleMoreImg)
    ImageView titleMoreImg;
    @ViewInject(R.id.titleBackImg)
    ImageView titleBackImg;
    @ViewInject(R.id.verticalLineImg)
    View verticalLineImg;
    @ViewInject(R.id.titleBackdropView)
    View titleBackdropView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
    }
    
    protected abstract void initData();
    
    protected Context getContext() {
        return this;
    }
    
    protected void setTitleBackdrop(int color){
        titleBackdropView.setBackgroundColor(color);
    }
    
    protected void setTitleText(String title, int... color) {
        if (title == null)
            titleTextTv.setVisibility(View.GONE);
        else {
            if (color != null && color.length > 0)
                titleTextTv.setTextColor(color[0]);
            else
                titleTextTv.setTextColor(Color.BLACK);
            titleTextTv.setVisibility(View.VISIBLE);
            titleTextTv.setText(title);
        }
    }
    
    protected void setTitleBackImg(int res) {
        if (res <= 0) {
            titleBackImg.setVisibility(View.GONE);
        } else {
            titleBackImg.setVisibility(View.VISIBLE);
            titleBackImg.setImageResource(res);
        }
    }
    
    protected void isShowTitleCut(boolean isShow) {
        if (isShow)
            verticalLineImg.setVisibility(View.VISIBLE);
        else
            verticalLineImg.setVisibility(View.GONE);
    }
    
    protected void setTitleMoreImg(int res) {
        if (res <= 0)
            titleMoreImg.setVisibility(View.GONE);
        else {
            titleMoreImg.setVisibility(View.VISIBLE);
            titleMoreImg.setImageResource(res);
        }
    }
}
