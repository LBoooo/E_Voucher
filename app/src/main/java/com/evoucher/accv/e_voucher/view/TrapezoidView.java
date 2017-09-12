package com.evoucher.accv.e_voucher.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.evoucher.accv.e_voucher.R;

/**
 * Created by 李小白 on 2017/9/11.
 * Email WorkerLiBai@163.com
 */

public class TrapezoidView extends View {
    private Paint paint;
    private Path path;
    
    
    public TrapezoidView(Context context) {
        this(context,null);
        
    }
    
    public TrapezoidView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    
    public TrapezoidView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    
    private void init() {
        paint = new Paint();
        paint.setColor(0xffd0f3e7);
        path = new Path();
        path.lineTo(0,100);
        path.lineTo(300,100);
//        path.lineTo(300,500);
        path.lineTo(350,0);
        path.close();
    }
    
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        canvas.drawPath(path , paint);
        
    }
    
}
