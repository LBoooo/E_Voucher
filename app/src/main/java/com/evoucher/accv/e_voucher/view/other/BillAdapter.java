package com.evoucher.accv.e_voucher.view.other;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.evoucher.accv.e_voucher.R;

import org.xutils.view.annotation.ViewInject;

/**
 * Created by 李小白 on 2017/9/9.
 * Email WorkerLiBai@163.com
 */

public class BillAdapter extends AbsBaseAdapter {
    Context context;
    
    
    public BillAdapter(Context context) {
        super(context);
        this.context = context;
    }
    
    public void setData(){
        
        notifyDataSetChanged();
    }
    
    @Override
    protected int setItemView() {
        return R.layout.item_bill;
    }
    
    @Override
    protected void intiData(int position, AbsBaseViewHolder holder) {
        BillViewHolder bh = (BillViewHolder) holder;
        bh.billNameTv.setText("充值");
        bh.billTimeTv.setText("2017/09/09 - 17:55:03");
        bh.billPriceTv.setText("+1000");
    }
    
    @Override
    protected int setCount() {
        return 20;
    }
    
    @Override
    protected AbsBaseViewHolder setHolder(View view, int position) {
        return new BillViewHolder(view);
    }
    
    private class BillViewHolder extends AbsBaseViewHolder{
    
        @ViewInject(R.id.billNameTv)
        TextView billNameTv;
        @ViewInject(R.id.billTimeTv)
        TextView billTimeTv;
        @ViewInject(R.id.billPriceTv)
        TextView billPriceTv;
        BillViewHolder(View view) {
            super(view);
        }
        
    }
}
