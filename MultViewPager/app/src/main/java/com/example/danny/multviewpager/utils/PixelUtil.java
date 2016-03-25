package com.example.danny.multviewpager.utils;
import android.content.Context;
/**
 * 像素工具
 */
public class PixelUtil {
    /**
     * dip转换成px
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context,int dipValue) {
        try {
            final float scale = context.getResources()
                    .getDisplayMetrics().density;
            return (int) (dipValue * scale + 0.5f);
        } catch (Exception e) {
        }
        return  dipValue;
    }

    /**
     * px 转换成dip
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context,float pxValue) {
        final float scale =context.getResources()
                .getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * PX转换成SP
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context,float pxValue) {
        final float fontScale =context.getResources()
                .getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * SP转换为PX
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context,float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
