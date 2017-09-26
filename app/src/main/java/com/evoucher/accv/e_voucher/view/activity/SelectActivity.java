package com.evoucher.accv.e_voucher.view.activity;

import android.content.Intent;
import android.view.View;
import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.utils.DatabaseHelper;
import com.evoucher.accv.e_voucher.utils.LogUtil;
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
        LogUtil.minute("用户id"+ DatabaseHelper.getInstance().getLastUserId());
        
    }
    
    @Event(value = {R.id.selectToBtn0 , R.id.selectToBtn1 , R.id.selectToBtn2 , R.id.selectToBtn3})
    private void toMainActivity(View view){
        switch (view.getId()){
            case R.id.selectToBtn0:
                SystemUtils.changeToTheme(this ,R.style.AppTheme_kq , true);
                break;
            case R.id.selectToBtn1:
                SystemUtils.changeToTheme(this ,R.style.AppTheme_yj , false);
                break;
            case R.id.selectToBtn2:
                SystemUtils.changeToTheme(this ,R.style.AppTheme_hd , false);
                break;
            case R.id.selectToBtn3:
                startActivity(new Intent(this , QualificationActivity.class));
                break;
        }
    }
}
