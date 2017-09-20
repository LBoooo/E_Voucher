package com.evoucher.accv.e_voucher.view.w;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
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
    //    private int progress = 50;
    private String text = "";//, users = "", upUsers = "";
    private int backColor;
    private int leftPadding;
    private int textSize;
    private float[] size = new float[3];
    private float[] x;
    
    //    int width , height;
    public TrapezoidView(Context context) {
        this(context, null);
        
    }
    
    public TrapezoidView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public TrapezoidView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TrapezoidView, defStyleAttr, 0);
        textSize = a.getInt(R.styleable.TrapezoidView_text_size, 10);
        size[0] = 0;
        size[1] = 30;
        size[2] = 50;
//        progress = a.getInt(R.styleable.TrapezoidView_progress , 100);
        text = a.getString(R.styleable.TrapezoidView_text);
        backColor = a.getColor(R.styleable.TrapezoidView_back_drop_color, 0xff6a6e77);
        init();
        a.recycle();
    }
    
    private void init() {
        paint = new Paint();
        path = new Path();
        paint.setColor(0xffd0f3e7);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setAntiAlias(true);
        paint.setDither(true);
        
        x = new float[]{1, 1};
        leftPadding = 10;
        DisplayMetrics metric = getResources().getDisplayMetrics();
        float width = metric.widthPixels;     // 屏幕宽度（像素）
        float height = metric.heightPixels;   // 屏幕高度（像素）
//        float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
//        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        x[0] = width / getResources().getInteger(R.integer.base_width);
        x[1] = height / getResources().getInteger(R.integer.base_height);
    
        paint.setTextSize(textSize * (x[0] + x[1]) / 2);
    }
    
    
    public void setBackdropColor(int color) {
        this.backColor = color;
        invalidate();
    }
    
    
    public void setText(String text) {
        this.text = text;
        invalidate();
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);
        path.lineTo(size[0] * x[0], size[2] * x[1]); //*x[1]
        path.lineTo(CanvasUtils.calculationTextWidth(paint, CanvasUtils.rough, text)+ leftPadding * x[0]*2, size[2] * x[1]); //*x[1]
        path.lineTo(CanvasUtils.calculationTextWidth(paint, CanvasUtils.rough, text)+leftPadding * x[0]*2 + (size[1] * x[0]), size[0] * x[1]);  //*x[0]
        
        
        path.close();
        paint.setColor(backColor);
        canvas.drawPath(path, paint);
        paint.setColor(Color.WHITE);
        drawCenter(canvas, text, leftPadding * x[0]);
//        width = (int) CanvasUtils.calculationTextWidth(paint, CanvasUtils.accurate, text);
//        height = (int) (size[2] * x[0]);
//        paint.setTextSize(textSize[1] * (x[0] + x[1]) / 2);
//        drawCenter(canvas, users, (leftPadding * 2 * x[0] + cTemp));
//        float rTemp = CanvasUtils.calculationTextWidth(paint, CanvasUtils.accurate, users);
//
//        paint.setTextSize(textSize[2] * (x[0] + x[1]) / 2);
//        drawCenter(canvas, upUsers, (leftPadding * 3 * x[0] + cTemp + rTemp));
    }
    
    private void drawCenter(Canvas canvas, String s, float left) {
        paint.setTextAlign(Paint.Align.LEFT);
        float stringWidth = paint.measureText(s);
        float x = (getWidth() - stringWidth) / 2;
        //文字的y轴坐标
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float y = getHeight() / 2 + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2;
        canvas.drawText(s, left, y, paint);
//        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
//        int baseline = (int) ((size[1] * x[1] + 0 - fontMetrics.bottom - fontMetrics.top) / 2);
//        paint.setTextAlign(Paint.Align.LEFT);
//        canvas.drawText(s, left, baseline, paint);
    }
    
//    int width, height;
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width, height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = (int) (CanvasUtils.calculationTextWidth(paint, CanvasUtils.rough, text)+(size[1]+leftPadding*2)* x[0]); // + size[1] * x[1]  +(size[2]+leftPadding)* x[0]
//            Log.d("TrapezoidView", "width:" + width);
//            width = 300;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = (int) (size[2] * x[1]);
        }
        setMeasuredDimension(width, height);

    }
    
//    /**
//     * view的大小控制
//     */
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//
//        setMeasuredDimension(measureWidth(widthMeasureSpec),
//                measureHeight(heightMeasureSpec));
//
//    }
//
//    private int measureHeight(int measureSpec) {
//        int result = 0;
//        int mode = MeasureSpec.getMode(measureSpec);
//        int hsize = MeasureSpec.getSize(measureSpec);
//
//        if (mode == MeasureSpec.EXACTLY) {
//            result = hsize;
//        } else {
//            result = (int) (CanvasUtils.calculationTextWidth(paint, CanvasUtils.rough, text)+(size[2]+leftPadding)* x[0]); // + size[1] * x[1]  +(size[2]+leftPadding)* x[0]
//            if (mode == MeasureSpec.AT_MOST) {
//                result = Math.min(result, hsize);
//            }
//        }
//
//        Log.d("TrapezoidView", "result1:" + result);
//        return result;
//
//    }
//
//    private int measureWidth(int measureSpec) {
//        int result = 0;
//        int mode = MeasureSpec.getMode(measureSpec);
//        int wsize = MeasureSpec.getSize(measureSpec);
//
//        if (mode == MeasureSpec.EXACTLY) {
//            result = wsize;
//        } else {
//            result = (int) (size[2] * x[1]);
//            if (mode == MeasureSpec.AT_MOST) {
//                result = Math.min(result, wsize);
//            }
//        }
//        Log.d("TrapezoidView", "result2:" + result);
//        return result;
//
//    }
}
