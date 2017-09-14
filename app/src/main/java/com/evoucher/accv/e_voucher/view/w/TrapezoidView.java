package com.evoucher.accv.e_voucher.view.w;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.evoucher.accv.e_voucher.R;
import com.evoucher.accv.e_voucher.utils.CanvasUtils;
import com.zhy.autolayout.AutoLayoutInfo;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoLayoutHelper;

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
    private int[] textSize;
    private int[] size;
    private float[] x;
    
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
        textSize = new int[3];
        size = new int[3];
        x = new float[]{1, 1};
        
        DisplayMetrics metric = getResources().getDisplayMetrics();
        float width = metric.widthPixels;     // 屏幕宽度（像素）
        float height = metric.heightPixels;   // 屏幕高度（像素）
//        float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
//        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        
        x[0] = width / getResources().getInteger(R.integer.base_width);
        x[1] = height / getResources().getInteger(R.integer.base_height);
    }
    
    public void setSize(int leftPadding, int[] textSize, int[] size) {
        this.leftPadding = leftPadding;
        this.textSize = textSize;
        this.size = size;
        invalidate();
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
        path.lineTo(size[0], size[1] * x[1]);
        path.lineTo(progress * x[0], size[1] * x[1]);
        path.lineTo((progress + size[2]) * x[0], size[0]);
        path.close();
        paint.setColor(backColor);
        canvas.drawPath(path, paint);
        
        paint.setColor(Color.BLACK);
        paint.setTextSize(textSize[0] * (x[0] + x[1]) / 2);
        drawCenter(canvas, text, leftPadding * x[0]);
        float cTemp = CanvasUtils.calculationTextWidth(paint, CanvasUtils.accurate, text);
        
        paint.setTextSize(textSize[1] * (x[0] + x[1]) / 2);
        drawCenter(canvas, users, (leftPadding * 2 * x[0] + cTemp));
        float rTemp = CanvasUtils.calculationTextWidth(paint, CanvasUtils.accurate, users);
        
        paint.setTextSize(textSize[2] * (x[0] + x[1]) / 2);
        drawCenter(canvas, upUsers, (leftPadding * 3 * x[0] + cTemp + rTemp));
    }
    
    private void drawCenter(Canvas canvas, String s, float left) {
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        int baseline = (int) ((size[1] * x[1] + 0 - fontMetrics.bottom - fontMetrics.top) / 2);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(s, left, baseline, paint);
    }
}
