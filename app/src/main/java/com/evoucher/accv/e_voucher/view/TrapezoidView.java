package com.evoucher.accv.e_voucher.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.utils.CanvasUtils;

/**
 * Created by 李小白 on 2017/9/11.
 * Email WorkerLiBai@163.com
 */

public class TrapezoidView extends View {
    private Paint paint;
    private Path path;
    private int progress = 0;
    private String text = "", users = "", upUsers = "";
    private int backColor;
    private int leftPadding;
    
    public TrapezoidView(Context context) {
        this(context, null);
    }
    
    public TrapezoidView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public TrapezoidView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    
    private void init() {
        paint = new Paint();
        path = new Path();
        paint.setColor(0xffd0f3e7);
//        paint.setStrokeWidth(3);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setAntiAlias(true);
        paint.setDither(true);
        leftPadding = 30;
    }
    
    public void setBackdropColor(int color) {
        this.backColor = color;
        invalidate();
    }
    
    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }
    
    public void setText(String text, String users, String upUsers) {
        this.text = text;
        this.users = users;
        this.upUsers = upUsers;
        invalidate();
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.lineTo(0, 100);
        path.lineTo(progress, 100);
        path.lineTo(progress + 50, 0);
        path.close();
        paint.setColor(backColor);
        canvas.drawPath(path, paint);
        
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        drawCenter(canvas, text, leftPadding);
        float cTemp = CanvasUtils.calculationTextWidth(paint, CanvasUtils.accurate, text);
        
        paint.setTextSize(40);
        drawCenter(canvas, users, leftPadding * 2 + cTemp);
        float rTemp = CanvasUtils.calculationTextWidth(paint, CanvasUtils.accurate, users);
        
        paint.setTextSize(30);
        drawCenter(canvas, upUsers, leftPadding * 3 + cTemp + rTemp);
    }
    
    
    private void drawCenter(Canvas canvas, String s, float left) {
//        Rect bounds = new Rect();
//        paint.getTextBounds(s, 0, s.length(), bounds);
//        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
//        int baseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        canvas.drawText(s, left, 60, paint); //getMeasuredWidth() / 2 - bounds.width() / 2
    }
    
    
}
