package com.evoucher.accv.e_voucher.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.model.bean.MessageBean;
import com.evoucher.accv.e_voucher.view.activity.PayHelperActivity;
import com.evoucher.accv.e_voucher.view.other.AbsBaseAdapter;
import com.evoucher.accv.e_voucher.view.w.DragPointHelper;
import com.evoucher.accv.e_voucher.view.w.DragPointView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李小白 on 2017/9/18.
 * Email WorkerLiBai@163.com
 */
@ContentView(R.layout.fragment_message)
public class MessageFragment extends BaseFragment {
    @ViewInject(R.id.messageLv)
    private ListView messageLv;
    MessageAdapter adapter;
    OnInitMessageNumCallBack callBack;
    List<MessageBean> messageList;
    
    
    @Override
    protected void initData() {
        adapter = new MessageAdapter(getContext());
        messageLv.setAdapter(adapter);
        messageLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getContext(), PayHelperActivity.class));
            }
        });
        
        messageList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            messageList.add(new MessageBean(false, i));
        }
        adapter.addData(messageList);
        adapter.notifyMessageRead();
    }
    
    public interface OnInitMessageNumCallBack {
        void onMessageListener(int num);
    }
    
    public void setCallBack(OnInitMessageNumCallBack callBack) {
        this.callBack = callBack;
    }
    
    public void removeUnreadMessage() {
        adapter.removeUnreadMessage();
    }
    
    private class MessageAdapter extends AbsBaseAdapter implements DragPointHelper.OnDragRemoveListener {
        MessageHolder mh;
        boolean isAllRead = false;
        List<MessageBean> msgs;
        
        MessageAdapter(Context context) {
            super(context);
            msgs = new ArrayList<>();
        }
        
        void addData(List<MessageBean> msgs) {
            this.msgs = msgs;
            notifyDataSetChanged();
        }
        
        void removeUnreadMessage() {
            isAllRead = true;
            notifyDataSetChanged();
        }
        
        @Override
        protected int setItemView() {
            return R.layout.item_message;
        }
        
        @Override
        protected void intiData(int position, AbsBaseViewHolder holder) {
            mh = (MessageHolder) holder;
            if (msgs != null && !msgs.isEmpty()) {
                MessageBean mb = msgs.get(position);
                if (isAllRead) {
                    mb.setRead(true);
                    mb.setMessageNum(0);
//                mh.pointView.startRemove();
                    mh.pointView.setVisibility(View.GONE);
                } else {
                    if (mb.isRead() || mb.getMessageNum() <= 0) {
                        mh.pointView.setVisibility(View.INVISIBLE);
                    } else {
                        mh.pointView.setVisibility(View.VISIBLE);
                        mh.pointView.setTag(mb);
                        mh.pointView.setText(String.valueOf(mb.getMessageNum()));
                        DragPointHelper.setAnim(getContext(), mh.pointView, this);
                    }
                }
            }
        }
        
        @Override
        protected int setCount() {
            return msgs.size();
        }
        
        @Override
        protected AbsBaseViewHolder setHolder(View view, int position) {
            return new MessageHolder(view);
        }
        
        @Override
        public void onDragRemove(DragPointView view) {
            MessageBean mb = (MessageBean) view.getTag();
            mb.setRead(true);
            mb.setMessageNum(0);
            notifyMessageRead();
            notifyDataSetChanged();
        }
        
        public void notifyMessageRead(){
            int num = 0;
    
            for (int i = 0; i < msgs.size(); i++) {
                num += msgs.get(i).getMessageNum();
            }
    
            if (callBack != null)
                callBack.onMessageListener(num);
        }
        
        class MessageHolder extends AbsBaseViewHolder {
            
            @ViewInject(R.id.itemMessageDpv)
            DragPointView pointView;
            
            MessageHolder(View view) {
                super(view);
            }
        }
    }
}
