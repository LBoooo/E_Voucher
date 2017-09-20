package com.evoucher.accv.e_voucher.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.utils.SystemUtils;
import com.evoucher.accv.e_voucher.utils.ToastUtil;
import com.evoucher.accv.e_voucher.view.activity.AccountBalanceActivity;
import com.evoucher.accv.e_voucher.view.other.AbsBaseAdapter;
import com.evoucher.accv.e_voucher.view.other.CallDialog;
import com.evoucher.accv.e_voucher.view.activity.CodeVerificationActivity;
import com.evoucher.accv.e_voucher.view.activity.EffectMonitoringActivity;
import com.evoucher.accv.e_voucher.view.activity.ScanCodeActivity;
import com.evoucher.accv.e_voucher.view.activity.ValidationHistoryActivity;
import com.evoucher.accv.e_voucher.view.w.TrapezoidView;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zhy.autolayout.utils.AutoUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by 李小白 on 2017/9/15.
 * Email WorkerLiBai@163.com
 */

@ContentView(R.layout.fragment_main)
public class MainFragment extends BaseFragment {
    private static final int REQUEST_CODE = 0x001;
    
    
    boolean isShowPage = false;
//    @SuppressLint("StaticFieldLeak")
    private static MainFragment mainFragment = null;

    public static MainFragment getInstance(boolean isShowPage) {
        if (mainFragment == null)
            mainFragment = new MainFragment();
        mainFragment.chooseView(isShowPage);
        return mainFragment;
    }
    
    @Override
    protected void initData() {
//        getFragmentManager().beginTransaction().replace(R.id.replaceView, new MainPageFragment()).commit();
        if (isShowPage)
            getFragmentManager().beginTransaction().replace(R.id.replaceView, new MainPageFragment()).commit();
        else
            getFragmentManager().beginTransaction().replace(R.id.replaceView, new MainProjectFragment()).commit();
    }
    
    protected void chooseView(boolean isShowPage) {
       this.isShowPage = isShowPage;
       

    }
    
    @Event(value = {R.id.scanCodeView, R.id.enterCodeView, R.id.verificationView})
//, R.id.mainAddView})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.scanCodeView: // 二维码页面
                Intent intent = new Intent(getContext(), ScanCodeActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.enterCodeView:
                startActivity(new Intent(getContext(), CodeVerificationActivity.class));
                break;
            case R.id.verificationView:
                startActivity(new Intent(getContext(), ValidationHistoryActivity.class));
                break;
//            case R.id.mainAddView:
//                Toast.makeText(getContext(), "add", Toast.LENGTH_SHORT).show();
//                break;
        }
    }
    
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    ToastUtil.showToast(getContext(), "解析结果:" + result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    ToastUtil.showToast(getContext(), "解析二维码失败");
                }
            }
        }
    }
    
    
}