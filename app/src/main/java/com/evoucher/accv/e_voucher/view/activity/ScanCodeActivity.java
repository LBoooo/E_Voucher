package com.evoucher.accv.e_voucher.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.utils.ImageUtil;
import com.evoucher.accv.e_voucher.utils.ToastUtil;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * Created by 李小白 on 2017/9/9.
 * Email WorkerLiBai@163.com
 */

@ContentView(R.layout.activity_scan_code)
public  class ScanCodeActivity extends BaseActivity{
    
    private CaptureFragment captureFragment;
    /**
     * 选择系统图片Request Code
     */
    public static final int REQUEST_IMAGE = 0x002;
    
    @Override
    protected void initData() {
        setTitleBackdrop(0xff373a41);
        setTitleText("二维码/条码" , Color.WHITE);
        captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.custom_camera);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.cameraView, captureFragment).commit();
    }
    
    @Event(value = {R.id.picCodeView})
    private void onClick(View v){
        switch (v.getId()){
            case R.id.picCodeView:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_IMAGE);
                break;
        }
    }
    
    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
            bundle.putString(CodeUtils.RESULT_STRING, result);
            resultIntent.putExtras(bundle);
            ScanCodeActivity.this.setResult(RESULT_OK, resultIntent);
            ScanCodeActivity.this.finish();
        }
        
        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            ScanCodeActivity.this.setResult(RESULT_OK, resultIntent);
            ScanCodeActivity.this.finish();
        }
    };
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(this, uri), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            ToastUtil.showToast(ScanCodeActivity.this, "解析结果:" + result);
                        }
                    
                        @Override
                        public void onAnalyzeFailed() {
                            ToastUtil.showToast(ScanCodeActivity.this, "解析二维码失败");
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
