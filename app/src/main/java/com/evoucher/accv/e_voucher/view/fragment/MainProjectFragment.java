package com.evoucher.accv.e_voucher.view.fragment;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
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
    
    @Override
    protected void initData() {
        mainProjectLv.setAdapter(new MainProjectAdapter(getContext()));
        GradientDrawable gd = (GradientDrawable) mainAddView.getBackground();
        gd.setColor(SystemUtils.getThemeColor(getContext()));

    }
    
    @Event(value = {R.id.mainAddView})
    private void onCLick(View view){
        Toast.makeText(getContext(), "add", Toast.LENGTH_SHORT).show();
    }
    
    private class MainProjectAdapter extends AbsBaseAdapter {
        
        public MainProjectAdapter(Context context) {
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
