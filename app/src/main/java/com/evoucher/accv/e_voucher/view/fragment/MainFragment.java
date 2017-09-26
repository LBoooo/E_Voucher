package com.evoucher.accv.e_voucher.view.fragment;

import android.speech.tts.TextToSpeech;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.utils.ToastUtil;

import org.xutils.view.annotation.ContentView;

import java.util.Locale;

/**
 * Created by 李小白 on 2017/9/15.
 * Email WorkerLiBai@163.com
 */

@ContentView(R.layout.fragment_main)
public class MainFragment extends BaseFragment {
   
    
    boolean isShowPage = false;
    private static MainFragment mainFragment = null;
    
    public static MainFragment getInstance(boolean isShowPage) {
        if (mainFragment == null)
            mainFragment = new MainFragment();
        mainFragment.chooseView(isShowPage);
        return mainFragment;
    }
    
    @Override
    protected void initData() {
        if (isShowPage)
            getFragmentManager().beginTransaction().replace(R.id.replaceView, new MainPageFragment()).commit();
        else
            getFragmentManager().beginTransaction().replace(R.id.replaceView, new MainProjectFragment()).commit();
    }
    
    protected void chooseView(boolean isShowPage) {
       this.isShowPage = isShowPage;
    }
   
}