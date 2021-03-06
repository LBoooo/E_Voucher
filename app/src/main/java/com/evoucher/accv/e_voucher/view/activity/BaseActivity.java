package com.evoucher.accv.e_voucher.view.activity;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.utils.LogUtil;
import com.evoucher.accv.e_voucher.utils.SystemUtils;
import com.evoucher.accv.e_voucher.view.other.AbsBaseDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by 李小白 on 2017/9/8.
 * Email WorkerLiBai@163.com
 */

public abstract class BaseActivity extends AutoLayoutActivity {
    @ViewInject(R.id.titleTextTv)
    private TextView titleTextTv;
    @ViewInject(R.id.titleMoreImg)
    private ImageView titleMoreImg;
    @ViewInject(R.id.titleBackImg)
    private ImageView titleBackImg;
    @ViewInject(R.id.verticalLineImg)
    private View verticalLineImg;
    @ViewInject(R.id.titleBackdropView)
    private View titleBackdropView;
    
    protected static final int INVISIBLE = -1;
    
    
    private LoadingDialog loading;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemUtils.onActivityCreateSetTheme(this);
        x.view().inject(this);
        loading = new LoadingDialog();
        initData();
        LogUtil.v(getClass().getSimpleName() + "-----------onCreate");
        
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
    
    @Event(value = {R.id.titleBackImg})
    private void titleBack(View view) {
        if (this instanceof SelectActivity) {
            // 主页
            showDoubleClickFinishDialog(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
        } else {
            // 其他直接返回
            finish();
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
    
    protected void setOnRefreshListener(SmartRefreshLayout refreshLayout, OnRefreshListener onRefreshListener, OnLoadmoreListener onLoadmoreListener) {
        if (refreshLayout != null) {
            refreshLayout.setOnRefreshListener(onRefreshListener);
            
            refreshLayout.setOnLoadmoreListener(onLoadmoreListener);
        }
    }
    
    public static class LoadingDialog extends AbsBaseDialog {
        
        @Override
        protected int initLayout() {
            return R.layout.dialog_custom_progress;
        }
        
        @Override
        protected void initView(View view) {
            
        }
       
        
    }
    
    protected void showLoading() {
//        if (loading != null)
            loading.show(getSupportFragmentManager(), "");
    }
    
    protected void hideLoading() {
//        if (loading != null && loading.getDialog() != null && loading.getDialog().isShowing())
            loading.dismiss();
    }
    
    /**
     * 这是兼容的 AlertDialog
     */
    protected void showDoubleClickFinishDialog(DialogInterface.OnClickListener listener) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        dialog.setTitle("退出！");
        dialog.setMessage("确定要退出吗？");
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", listener);
        dialog.show();
    }
    
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.e(getClass().getSimpleName() + "-----------onDestroy");
        
    }
    
    /**
     * 监听Back键按下事件
     * 注意:
     * 返回值表示:是否能完全处理该事件
     * 在此处返回false,所以会继续传播该事件.
     * 在具体项目中此处的返回值视情况而定.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (this instanceof SelectActivity)
            if ((keyCode == KeyEvent.KEYCODE_BACK)) {
                showDoubleClickFinishDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                return false;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        return super.onKeyDown(keyCode, event);
    }
}
