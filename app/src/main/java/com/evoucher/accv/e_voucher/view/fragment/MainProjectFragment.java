package com.evoucher.accv.e_voucher.view.fragment;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.utils.SystemUtils;
import com.evoucher.accv.e_voucher.view.other.AbsBaseAdapter;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by 李小白 on 2017/9/20.
 * Email WorkerLiBai@163.com
 */

@ContentView(R.layout.fragment_main_project)
public class MainProjectFragment extends BaseFragment {
    @ViewInject(R.id.mainProjectLv)
    ListView mainProjectLv;
    @ViewInject(R.id.mainAddView)
    View mainAddView;
//    TextToSpeech tts;
    
    @Override
    protected void initData() {
        mainProjectLv.setAdapter(new MainProjectAdapter(getContext()));
        GradientDrawable gd = (GradientDrawable) mainAddView.getBackground();
        gd.setColor(SystemUtils.getThemeColor(getContext()));
//        tts = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int i) {
//                if (i == TextToSpeech.SUCCESS) {
//                    int result = tts.setLanguage(Locale.ENGLISH);
//                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
//                        ToastUtil.showToast(getContext() , "LANG_MISSING_DATA  or LANG_NOT_SUPPORTED !");
//                    }
//                }
//            }
//        });
//
//        tts.setPitch(1.5f); // 在系统设置里也可以修改音调
        
    }
    
    @Event(value = {R.id.mainAddView})
    private void onCLick(View view) {
        Toast.makeText(getContext(), "add", Toast.LENGTH_SHORT).show();
//        tts.speak("hello , test the speak project !", TextToSpeech.QUEUE_FLUSH, null);
    }
    
    private class MainProjectAdapter extends AbsBaseAdapter {
        
        MainProjectAdapter(Context context) {
            super(context);
        }
        
        @Override
        protected int setItemView() {
            return R.layout.item_main_project;
        }
        
        @Override
        protected void intiData(int position, AbsBaseViewHolder holder) {
            
        }
        
        @Override
        protected int setCount() {
            return 10;
        }
        
        @Override
        protected AbsBaseViewHolder setHolder(View view, int position) {
            return new MainProjectHolder(view);
        }
        
        private class MainProjectHolder extends AbsBaseViewHolder {
            
            public MainProjectHolder(View view) {
                super(view);
            }
        }
    }
}
