package com.evoucher.accv.e_voucher.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.evoucher.accv.e_voucher.R;
import com.zhy.autolayout.utils.AutoUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by 李小白 on 2017/9/11.
 * Email WorkerLiBai@163.com
 */

public class VHPageListAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {
    private Context context;
    
    public VHPageListAdapter(Context context) {
        this.context = context;
    }
    
    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        if (convertView == null) {
            holder = new ChildHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_child_vhpage, parent , false);
            
            
            convertView.setTag(holder);
            //对于listView，注意添加这一行，即可在item上使用高度
            AutoUtils.autoSize(convertView);
    
            x.view().inject(holder,convertView);//注解绑定
        } else {
            holder = (ChildHolder) convertView.getTag();
        }
       
        return convertView;
    }
    
    @Override
    public int getRealChildrenCount(int groupPosition) {
        return 10;
    }
    
    @Override
    public int getGroupCount() {
        return 20;
    }
    
    @Override
    public Object getGroup(int i) {
        return "123";
    }
    
    @Override
    public Object getChild(int i, int i1) {
        return "456";
    }
    
    @Override
    public long getGroupId(int i) {
        return 1;
    }
    
    @Override
    public long getChildId(int i, int i1) {
        return 2;
    }
    
    @Override
    public boolean hasStableIds() {
        return true;
    }
    
    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupHolder holder;
        if (view == null) {
            holder = new GroupHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_group_vhpage, viewGroup , false);
    
            view.setTag(holder);
            //对于listView，注意添加这一行，即可在item上使用高度
            AutoUtils.autoSize(view);
            x.view().inject(holder,view);//注解绑定
        } else {
            holder = (GroupHolder) view.getTag();
        }
        if(b){
            holder.rightImg.setImageResource(R.mipmap.ic_launcher_round);
        }else{
            holder.rightImg.setImageResource(R.mipmap.ic_launcher);
        }
        holder.itemGroupTitleTv.setText("XXX店");
        return view;
    }
    
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
    
    private static class ChildHolder {
        
    }
    
    private static class GroupHolder {
        @ViewInject(R.id.rightImg)
        ImageView rightImg;
        @ViewInject(R.id.leftImg)
        ImageView leftImg;
        @ViewInject(R.id.itemGroupTitleTv)
        TextView itemGroupTitleTv;
    }
}
