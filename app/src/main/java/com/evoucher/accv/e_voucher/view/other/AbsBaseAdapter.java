package com.evoucher.accv.e_voucher.view.other;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zhy.autolayout.utils.AutoUtils;

import org.xutils.x;

/**
 * Created by 李小白 on 2017/9/9.
 * Email WorkerLiBai@163.com
 */

public abstract class AbsBaseAdapter extends BaseAdapter {
    private Context context;
    
    public Context getAdapterContet(){
        return context;
    }
    
    public AbsBaseAdapter(Context context) {
        this.context = context;
    }
    
    @Override
    public int getCount() {
        return setCount();
    }
    
    @Override
    public Object getItem(int i) {
        return null;
    }
    
    @Override
    public long getItemId(int i) {
        return 0;
    }
    
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        AbsBaseViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(setItemView(), viewGroup, false);
            holder = setHolder(view, i);
            view.setTag(holder);
            //对于listView，注意添加这一行，即可在item上使用高度
            AutoUtils.autoSize(view);
    
            x.view().inject(holder,view);//注解绑定
        } else {
            holder = (AbsBaseViewHolder) view.getTag();
        }
        intiData(i , holder);
        return view;
    }
    
    protected abstract int setItemView();
    protected abstract void intiData(int position ,AbsBaseViewHolder holder);
    protected abstract int setCount();
    protected abstract AbsBaseViewHolder setHolder(View view, int position);
    
    public abstract class AbsBaseViewHolder {
//        View view;
        public AbsBaseViewHolder(View view) {
//            this.view = view;
//            initView(view);
        }
        
//        <T extends View> T findView(int res) {
//            return this.view.findViewById(res);
//        }
//
//        protected abstract void initView(View view);
    }
}
