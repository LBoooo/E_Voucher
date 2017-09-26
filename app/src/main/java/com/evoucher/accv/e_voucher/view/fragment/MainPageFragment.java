package com.evoucher.accv.e_voucher.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.contract.CodeVerificationContract;
import com.evoucher.accv.e_voucher.presenter.CodeVerificationPresenter;
import com.evoucher.accv.e_voucher.utils.ToastUtil;
import com.evoucher.accv.e_voucher.view.activity.CodeVerificationActivity;
import com.evoucher.accv.e_voucher.view.activity.ScanCodeActivity;
import com.evoucher.accv.e_voucher.view.activity.ValidationHistoryActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * Created by 李小白 on 2017/9/20.
 * Email WorkerLiBai@163.com
 */
@ContentView(R.layout.fragment_main_page)
public class MainPageFragment extends BaseFragment implements CodeVerificationContract.View {
    private static final int REQUEST_CODE = 0x001;
    CodeVerificationPresenter presenter ;
    
    @Override
    protected void initData() {
        presenter = new CodeVerificationPresenter(this);
    }
    
    @Event(value = {R.id.scanCodeView, R.id.enterCodeView, R.id.verificationView})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.scanCodeView: // 二维码页面
                Intent intent = new Intent(getContext(), ScanCodeActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.enterCodeView:
                startActivityForResult(new Intent(getContext(), CodeVerificationActivity.class),REQUEST_CODE);
                
                break;
            case R.id.verificationView:
                startActivity(new Intent(getContext(), ValidationHistoryActivity.class));
                break;
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
                    presenter.verificationCode(result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    ToastUtil.showToast(getContext(), "解析二维码失败");
                }
            }
        }
    }
    
    @Override
    public void verificationCodeSuccess(String result) {
        ToastUtil.showToast(getContext(), "解析成功:" + result);
    }
    
    @Override
    public void verificationCodeFail(String err) {
        ToastUtil.showToast(getContext(), "解析失败:" + err);
    }
}
