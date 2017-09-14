package com.evoucher.accv.e_voucher.view;

import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
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
    
    @ViewInject(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private LoadingDialog loading;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        loading = new LoadingDialog();
        initData();
    }
    
    protected abstract void initData();
    
    protected Context getContext() {
        return this;
    }
    
    protected void setTitleBackdrop(int color) {
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
    
    public static class LoadingDialog extends DialogFragment {
        
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            
            getDialog().setCanceledOnTouchOutside(true);
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
            if (getDialog().getWindow() != null)
                getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            return inflater.inflate(R.layout.dialog_custom_progress, container, false);
        }
        
    }

    protected void showLoading() {
        loading.show(getFragmentManager(), "");
    }
    
    protected void hideLoading() {
        loading.dismiss();
    }
    
    
    protected void setOnRefreshListener(OnRefreshListener onRefreshListener , OnLoadmoreListener onLoadmoreListener){
        refreshLayout.setOnRefreshListener(onRefreshListener);
    
        refreshLayout.setOnLoadmoreListener(onLoadmoreListener);
    }
}
