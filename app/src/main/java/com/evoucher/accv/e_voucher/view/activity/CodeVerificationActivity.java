package com.evoucher.accv.e_voucher.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.utils.ToastUtil;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by 李小白 on 2017/9/8.
 * Email WorkerLiBai@163.com
 */

@ContentView(R.layout.activity_code_verification)
public class CodeVerificationActivity extends BaseActivity {
    @ViewInject(R.id.showHxTv)
    TextView showHxTv;
    StringBuffer sb;
    
    @Override
    protected void initData() {
        sb = new StringBuffer();
        setTitleText("输码验证");
        setTitleBackdrop(ContextCompat.getColor(getContext() , R.color.white));
    }
    
    
    @Event(value = {R.id.t0Tv, R.id.t1Tv, R.id.t2Tv, R.id.t3Tv, R.id.t4Tv, R.id.t5Tv, R.id.t6Tv, R.id.t7Tv,
            R.id.t8Tv, R.id.t9Tv, R.id.hxTv})
    private void onClick(View view) {
        switch (view.getId()) {
//            case R.id.t0Tv:
//
//                break;
//
//            case R.id.t1Tv:
//                break;
//
//            case R.id.t2Tv:
//                break;
//
//            case R.id.t3Tv:
//                break;
//
//            case R.id.t4Tv:
//                break;
//
//            case R.id.t5Tv:
//                break;
//
//            case R.id.t6Tv:
//                break;
//
//            case R.id.t7Tv:
//                break;
//
//            case R.id.t8Tv:
//                break;
//
//            case R.id.t9Tv:
//                break;
//
            case R.id.hxTv:
                if (sb!= null && !sb.toString().isEmpty()){
                    Intent resultIntent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
                    bundle.putString(CodeUtils.RESULT_STRING, sb.toString());
                    resultIntent.putExtras(bundle);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
                break;
            default:
                appendText(((TextView)view).getText().toString());
                break;
        }
    }
    
    
    private void appendText(String s){
        sb.append(s);
        showHxTv.setText(sb.toString());
    }
}
