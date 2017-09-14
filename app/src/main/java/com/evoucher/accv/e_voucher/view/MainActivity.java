package com.evoucher.accv.e_voucher.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.utils.ImageUtil;
import com.evoucher.accv.e_voucher.utils.PermissionHelper;
import com.evoucher.accv.e_voucher.utils.SystemUtils;
import com.evoucher.accv.e_voucher.utils.ToastUtil;
import com.evoucher.accv.e_voucher.view.w.TrapezoidView;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zhy.autolayout.utils.AutoUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by 李小白 on 2017/9/8.
 * Email WorkerLiBai@163.com
 */

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewInject(R.id.mainRv)
    private RecyclerView mainRv;
    private MainRecyclerViewAdapter adapter;
    private static final int REQUEST_CODE = 0x001;
    
    @Override
    protected void initData() {
        setTitleText("主页");
        PermissionHelper.initPermission(this);
        mainRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainRecyclerViewAdapter();
        mainRv.setAdapter(adapter);
    
        
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    ToastUtil.showToast(this, "解析结果:" + result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    ToastUtil.showToast(this, "解析二维码失败");
                }
            }
        }
    }
    
    private class MainRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final static int TYPE00 = 0x00;
        private final static int TYPE01 = 0x01;
        private final static int TYPE02 = 0x02;
        private final static int TYPE03 = 0x03;
        
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            switch (viewType) {
                case TYPE00:
                    View v0 = inflater.inflate(R.layout.item_main_0, parent, false);
                    MainViewHolder0 holder0 = new MainViewHolder0(v0);
                    x.view().inject(holder0, v0);
                    return holder0;
                case TYPE01:
                    View v1 = inflater.inflate(R.layout.item_main_1, parent, false);
                    MainViewHolder1 holder1 = new MainViewHolder1(v1);
                    x.view().inject(holder1, v1);
                    return holder1;
                case TYPE02:
                    View v2 = inflater.inflate(R.layout.item_main_2, parent, false);
                    MainViewHolder2 holder2 = new MainViewHolder2(v2);
                    x.view().inject(holder2, v2);
                    return holder2;
                case TYPE03:
                    View v3 = inflater.inflate(R.layout.item_main_3, parent, false);
                    MainViewHolder3 holder3 = new MainViewHolder3(v3);
                    x.view().inject(holder3, v3);
                    return holder3;
            }
            return null;
        }
        
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder != null) {
                switch (position) {
                    case TYPE00:
                        MainViewHolder0 holder0 = (MainViewHolder0) holder;
                        holder0.shopNameTv.setText("XXXXXXXXXXXXX店");
                        ImageUtil.loadImage("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3269061743,2028437678&fm=27&gp=0.jpg" , holder0.scanCodeImg);
                        ImageUtil.loadRoundImage("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1743354301,1611657411&fm=27&gp=0.jpg" , holder0.enterCodeImg);
                        break;
                    case TYPE01:
                        MainViewHolder1 holder1 = (MainViewHolder1) holder;
                        
                        
                        break;
                    case TYPE02:
                        MainViewHolder2 holder2 = (MainViewHolder2) holder;
                        
                        holder2.tv0.setBackdropColor(ContextCompat.getColor(getContext(), R.color.st_blue));
                        holder2.tv0.setSize(30 , new int[]{30,40,30} , new int[]{0,100,50});
                        holder2.tv0.setProgress(500);
                        holder2.tv0.setText("曝光人数", "1234", "234");
                      
                        holder2.tv1.setBackdropColor(ContextCompat.getColor(getContext(), R.color.wt_blue));
                        holder2.tv1.setSize(30 , new int[]{30,40,30} , new int[]{0,100,50});
                        holder2.tv1.setProgress(450);
                        holder2.tv1.setText("领券人数", "234", "34");
                       
                        holder2.tv2.setBackdropColor(ContextCompat.getColor(getContext(), R.color.lt_blue));
                        holder2.tv2.setSize(30 , new int[]{30,40,30} , new int[]{0,100,50});
                        holder2.tv2.setProgress(400);
                        holder2.tv2.setText("核销人数", "34", "4");
                        
                        break;
                    case TYPE03:
                        MainViewHolder3 holder3 = (MainViewHolder3) holder;
                        
                        
                        break;
                }
            }
        }
        
        @Override
        public int getItemCount() {
            return 4;
        }
        
        @Override
        public int getItemViewType(int position) {
            return position;
        }
        
        private class MainViewHolder0 extends RecyclerView.ViewHolder {
            @ViewInject(R.id.shopNameTv)
            TextView shopNameTv;
            @ViewInject(R.id.scanCodeImg)
            ImageView scanCodeImg;
            @ViewInject(R.id.enterCodeImg)
            ImageView enterCodeImg;
            
            public MainViewHolder0(View itemView) {
                super(itemView);
                AutoUtils.autoSize(itemView);
            }
            
            @Event(value = {R.id.scanCodeView, R.id.enterCodeView, R.id.accountBalanceView})
            private void onClick(View v) {
                switch (v.getId()) {
                    case R.id.scanCodeView: // 二维码页面
                        Intent intent = new Intent(MainActivity.this, ScanCodeActivity.class);
                        startActivityForResult(intent, REQUEST_CODE);
                        break;
                    case R.id.enterCodeView:
                        startActivity(new Intent(MainActivity.this, CodeVerificationActivity.class));
                        break;
                    case R.id.accountBalanceView:
                        startActivity(new Intent(MainActivity.this, AccountBalanceActivity.class));
                        break;
                }
            }
        }
        
        private class MainViewHolder1 extends RecyclerView.ViewHolder {
            
            public MainViewHolder1(View itemView) {
                super(itemView);
                AutoUtils.autoSize(itemView);
            }
            
            @Event(value = {R.id.validationHistoryView, R.id.effectMonitoringView})
            private void onClick(View v) {
                switch (v.getId()) {
                    case R.id.validationHistoryView:
                        startActivity(new Intent(MainActivity.this, ValidationHistoryActivity.class));
                        break;
                    
                    case R.id.effectMonitoringView:
                        startActivity(new Intent(MainActivity.this, EffectMonitoringActivity.class));
                        break;
                }
            }
        }
        
        private class MainViewHolder2 extends RecyclerView.ViewHolder {
            @ViewInject(R.id.tv0)
            TrapezoidView tv0;
            @ViewInject(R.id.tv1)
            TrapezoidView tv1;
            @ViewInject(R.id.tv2)
            TrapezoidView tv2;
            
            
            public MainViewHolder2(View itemView) {
                super(itemView);
                AutoUtils.autoSize(itemView);
            }
            
            @Event(value = {R.id.moreEffectTv})
            private void onClick(View view) {
                switch (view.getId()) {
                    case R.id.moreEffectTv:
                        startActivity(new Intent(MainActivity.this, EffectMonitoringActivity.class));
                        break;
                }
            }
        }
        
        private class MainViewHolder3 extends RecyclerView.ViewHolder {
            String phone = "XXXXXXXXXXX";
            
            public MainViewHolder3(View itemView) {
                super(itemView);
                AutoUtils.autoSize(itemView);
            }
            
            @Event(value = {R.id.mainCallSalesmanView})
            private void onCLick(View view) {
                switch (view.getId()) {
                    case R.id.mainCallSalesmanView:
                        new CallDialog().setListener(new CallDialog.onDialogClickListener() {
                            @Override
                            public void onCallListener() {
                                SystemUtils.call(phone, getContext());
                            }
                        }).show(getFragmentManager(), "");
                        break;
                }
            }
        }
    }
    
    
    /**
     * 监听Back键按下事件,方法2:
     * 注意:
     * 返回值表示:是否能完全处理该事件
     * 在此处返回false,所以会继续传播该事件.
     * 在具体项目中此处的返回值视情况而定.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            showDialog(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
    
    /**
     * 这是兼容的 AlertDialog
     */
    protected void showDialog(DialogInterface.OnClickListener listener) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        dialog.setTitle("退出！");
        dialog.setMessage("确定要退出吗？");
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", listener);
        dialog.show();
    }
}
