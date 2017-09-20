package com.evoucher.accv.e_voucher.view.w;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;

import com.evoucher.accv.e_voucher.R;

/**
 * Created by 李小白 on 2017/9/18.
 * Email WorkerLiBai@163.com
 */

public class DragPointHelper {
    
    public interface OnDragRemoveListener{
        void onDragRemove(DragPointView view);
    }
    
    public static void setAnim(Context context, DragPointView view , final OnDragRemoveListener listener) {
        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(context.getResources().getDrawable(R.mipmap.explode1), 100);
        animationDrawable.addFrame(context.getResources().getDrawable(R.mipmap.explode2), 100);
        animationDrawable.addFrame(context.getResources().getDrawable(R.mipmap.explode3), 100);
        animationDrawable.addFrame(context.getResources().getDrawable(R.mipmap.explode4), 100);
        animationDrawable.addFrame(context.getResources().getDrawable(R.mipmap.explode5), 100);
        animationDrawable.setOneShot(true);
        animationDrawable.setExitFadeDuration(300);
        animationDrawable.setEnterFadeDuration(100);
        view.setRemoveAnim(animationDrawable);
        view.setOnPointDragListener(new OnPointDragListener() {
            @Override
            public void onRemoveStart(AbsDragPointView view) {
        
            }
    
            @Override
            public void onRemoveEnd(AbsDragPointView view) {
                listener.onDragRemove((DragPointView) view);
            }
    
            @Override
            public void onRecovery(AbsDragPointView view) {
        
            }
        });
    }
}
