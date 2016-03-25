package com.example.danny.multviewpager.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Method;
import java.util.UUID;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;


/**
 * @describe 设备信息工具类
 */
public class DeviceUtils {


    /**
     * 得到手机IMEI
     * @param context
     * @return
     */
    public static String getImei(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            if(tm != null) {
                String deviceId = tm.getDeviceId();
                if( ! TextUtils.isEmpty(deviceId))
                    return deviceId;
            }
        } catch(Exception e) {
        }
        // return getMac(context);
        return "";
    }


    /**
     * 获取androidid
     * @param context
     * @return
     */
    public static String getAndroidId(Context context) {
        String androidId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
        if(androidId != null)
            return androidId;
        return UUID.randomUUID().toString();
    }

    /**
     * 获取CPU序列号
     * @return CPU序列号(16位) 读取失败为"0000000000000000"
     */
    public static String getCPUSerial() {
        String str = "", strCPU = "", cpuAddress = "0000000000000000";
        try {
            // 读取CPU信息
            Process pp = Runtime.getRuntime().exec("cat /proc/cpuinfo");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            // 查找CPU序列号
            for(int i = 1; i < 100; i ++ ) {
                str = input.readLine();
                if(str != null) {
                    // 查找到序列号所在行
                    if(str.indexOf("Serial") > - 1) {
                        // 提取序列号
                        strCPU = str.substring(str.indexOf(":") + 1, str.length());
                        // 去空格
                        cpuAddress = strCPU.trim();
                        break;
                    }
                } else {
                    // 文件结尾
                    break;
                }
            }
        } catch(IOException ex) {
            // 赋予默认值
            ex.printStackTrace();
        }
        return cpuAddress;
    }

    /**
     * 获取设备型号
     * @return
     */
    public static String getModel() {
        return Build.MODEL;
    }

    /**
     * 获取设备品牌
     * @return
     */
    public static String getBrand() {
        return Build.BRAND;
    }

    /**
     * 获取OS版本号
     * @return
     */
    public static String getOSversion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 打开拨号界面
     * @param number
     * @param context
     */
    public static void dial(String number, Context context) {
        Class<TelephonyManager> c = TelephonyManager.class;
        Method getITelephonyMethod = null;
        try {
            getITelephonyMethod = c.getDeclaredMethod("getITelephony", (Class[])null);
            getITelephonyMethod.setAccessible(true);
            TelephonyManager tManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            Object iTelephony;
            iTelephony = (Object)getITelephonyMethod.invoke(tManager, (Object[])null);
            Method dial = iTelephony.getClass().getDeclaredMethod("dial", String.class);
            dial.invoke(iTelephony, number);
        } catch(Exception e) {
        }
    }


    /**
     * getSystemAvaialbeMemorySize:获得系统可用内存信息. <br/>
     * @author adison
     * @param context
     * @return
     */
    public static long getSystemAvaialbeMemory(Context context) {
        // 获得MemoryInfo对象
        MemoryInfo memoryInfo = new MemoryInfo();
        // 获得系统可用内存，保存在MemoryInfo对象上
        ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(memoryInfo);

        return memoryInfo.availMem;
    }

    /**
     * SDK版本大于Android 2.2
     * @return
     */
    public static boolean hasFroyo() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    /**
     * SDK版本大于Android 2.3
     * @return
     */
    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    /**
     * SDK版本大于Android3.0
     * @return
     */
    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    /**
     * SDK版本大于Android3.1
     * @return
     */
    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    /**
     * SDK版本大于Android4.0
     * @return
     */
    public static boolean hasIceCreamSandwich() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    /**
     * SDK版本大于Android4.0.3
     * @return
     */
    public static boolean hasIceCreamSandwichMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1;
    }
    
    /**
     * getScreenWidth:得到屏幕宽度(像素点数). <br/>
     * 
     * @author wangheng
     * @return
     */
    public static int getScreenWidth(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    /**
     * getScreenHeight:得到屏幕高度(像素点数). <br/>
     * 
     * @author wangheng
     * @return
     */
    public static int getScreenHeight(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

    /**
     * getDensity:得到像素密度 <br/>
     * 
     * @author wangheng
     * @return
     */
    public static float getDensity(Context context) {
        return getDisplayMetrics(context).density;
    }

    /**
     * getDensityDpi:得到每英寸的像素点数<br/>
     * 
     * @author wangheng
     * @return
     */
    public static int getDensityDpi(Context context) {
        return getDisplayMetrics(context).densityDpi;
    }

    /**
     * 
     * getDisplayMetrics:返回DisplayMetrics对象，以方便得到屏幕相关信息. <br/>
     *
     * @author wangheng
     * @param context
     * @return
     */
    private static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        try{
            WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            if (display != null) {
                display.getMetrics(dm);
            } else {
                dm.setToDefaults();
            }
        }catch(Exception e){
        }
        return dm;
    }

    /*
    *  返回注册的网络运营商的名字  例如：中国移动
    * */
    public static String getNetworkOperatorName(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (tm != null) {
                String netOperator = tm.getNetworkOperatorName();
                if (!TextUtils.isEmpty(netOperator))
                    return netOperator;
            }
        } catch (Exception e) {
        }
        return "";
    }

    /*
    *
    * 获取SIM卡运营商的名字 例如： CMCC
    * */
    public static String getSimOperatorName(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (tm != null) {
                String simOperator = tm.getSimOperatorName();
                if (!TextUtils.isEmpty(simOperator))
                    return simOperator;
            }
        } catch (Exception e) {
        }
        return "";
    }
}
