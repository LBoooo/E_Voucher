package com.evoucher.accv.e_voucher.view.activity;

import android.Manifest;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.contract.QualificationContract;
import com.evoucher.accv.e_voucher.presenter.QualificationPresenter;
import com.evoucher.accv.e_voucher.utils.ImageUtil;
import com.evoucher.accv.e_voucher.utils.LogUtil;
import com.evoucher.accv.e_voucher.utils.ToastUtil;
import com.evoucher.accv.e_voucher.view.w.TedBottomPicker;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;


/**
 * Created by 李小白 on 2017/9/26.
 * Email WorkerLiBai@163.com
 */

@ContentView(R.layout.activity_qualification)
public class QualificationActivity extends BaseActivity implements QualificationContract.View {
    @ViewInject(R.id.selectImg)
    ImageView selectImg;
    @ViewInject(R.id.merchantNameEt)
    EditText merchantNameEt;
    @ViewInject(R.id.merchantAddress)
    EditText merchantAddress;
    @ViewInject(R.id.selectImageTv)
    TextView selectImageTv;
    TedBottomPicker bottomSheetDialogFragment;
    QualificationPresenter presenter;
    String imagePath ;
    
    @Override
    protected void initData() {
        presenter = new QualificationPresenter(this);
        setTitleBackdrop(ContextCompat.getColor(getContext(), R.color.white));
        setTitleText("资格审核");
        bottomSheetDialogFragment = new TedBottomPicker.Builder(QualificationActivity.this)
                .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
                    @Override
                    public void onImageSelected(final Uri uri) {
                        LogUtil.v("URI:" + uri + "\n" + "PATH:" + uri.getPath());
                        ImageUtil.displayImageUri(uri, selectImg, getContext());
                        imagePath = uri.getPath();
                        selectImageTv.setText("1/1");
                    }
                })
                .setPeekHeight(getResources().getDisplayMetrics().heightPixels / 2)
                .create();
    
      
    }
    
    @Event(value = {R.id.selectImg, R.id.upLoadBtn})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.selectImg:
                bottomSheetDialogFragment.show(getSupportFragmentManager());
                break;
            case R.id.upLoadBtn:
               
                if (presenter.onUpLoad(merchantNameEt.getText().toString().trim(),
                        merchantAddress.getText().toString().trim() ,
                        imagePath))
                    showLoading();
                
                break;
        }
    }
    
    @Event(type = View.OnLongClickListener.class , value = R.id.selectImg)
    private boolean onDeleteImg(View view){
        selectImg.setImageBitmap(null);
        imagePath = "";
        selectImageTv.setText("0/1");
        return true;
    }
    
    
    @Override
    public void onUploadFail(String err) {
        ToastUtil.showToast(getContext() , err);
        hideLoading();
    }
    
    @Override
    public void onUpLoadSuccess() {
        ToastUtil.showToast(getContext() , "上传成功");
        hideLoading();
    }
}
