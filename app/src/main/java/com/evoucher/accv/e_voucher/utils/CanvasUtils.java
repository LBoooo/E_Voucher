package com.evoucher.accv.e_voucher.utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;

/**
 * Created by 李小白 on 2017/9/13.
 * Email WorkerLiBai@163.com
 */

public class CanvasUtils {
    public final static int accurate = 0x00;
    public final static int normal = 0x01;
    public final static int rough = 0x02;
    
    /***
     * 计算文字宽度
     * @param paint
     * @param type
     * @param str
     * @return
     */
    public static float calculationTextWidth(Paint paint, int type, String... str) {
        if (str != null && str.length > 0) {
            float width = 0;
            switch (type) {
                case accurate:
                    for (int i = 0; i < str.length; i++) {
                        if (str[i] != null && !str[i].equals("")) {
                            int len = str[i].length();
                            float widths[] = new float[len];
                            paint.getTextWidths(str[i], widths);
                            for (int j = 0; j < len; j++) {
                                width += Math.ceil(widths[j]);
                            }
                        }
                    }
                    return width;
                case normal: //2. 计算文字的矩形，可以得到宽高：
                    for (int i = 0; i < str.length; i++) {
                        String s = str[i];
                        Rect rect = new Rect();
                        paint.getTextBounds(s, 0, s.length(), rect);
                        int w = rect.width();
                        int h = rect.height();
                        width += w;
                    }
                    return width;
                case rough: //1. 粗略计算文字宽度：
                    for (int i = 0; i < str.length; i++) {
                        width += paint.measureText(str[i]);
                    }
                    return width;
            }
        }
        return 0;
    }
    
    public static PointF[] getIntersectionPoints(float cx, float cy, float radius, Double lineK) {
        PointF[] points = new PointF[2];
        
        float radian, xOffset = 0, yOffset = 0;
        if(lineK != null){
            
            radian= (float) Math.atan(lineK);
            xOffset = (float) (Math.cos(radian) * radius);
            yOffset = (float) (Math.sin(radian) * radius);
        }else {
            xOffset = radius;
            yOffset = 0;
        }
        points[0] = new PointF(cx + xOffset, cy + yOffset);
        points[1] = new PointF(cx - xOffset, cy - yOffset);
        
        return points;
    }
    
    /**
     *两点间距
     */
    public static double getDistance(PointF p1,PointF p2){
        return Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y));
    }
    
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
