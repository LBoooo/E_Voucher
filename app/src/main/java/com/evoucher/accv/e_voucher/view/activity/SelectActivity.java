package com.evoucher.accv.e_voucher.view.activity;

import android.view.View;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.utils.SystemUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * Created by 李小白 on 2017/9/15.
 * Email WorkerLiBai@163.com
 */

@ContentView(R.layout.activity_select)
public class SelectActivity extends BaseActivity {
    @Override
    protected void initData() {
        
    }
    
    @Event(value = {R.id.selectToBtn0 , R.id.selectToBtn1 , R.id.selectToBtn2})
    private void toMainActivity(View view){
        switch (view.getId()){
            case R.id.selectToBtn0:
                SystemUtils.changeToTheme(this ,R.style.AppTheme_kq);
                break;
            case R.id.selectToBtn1:
                SystemUtils.changeToTheme(this ,R.style.AppTheme_yj);
                break;
            case R.id.selectToBtn2:
                SystemUtils.changeToTheme(this ,R.style.AppTheme_hd);
                break;
        }
    }
}
