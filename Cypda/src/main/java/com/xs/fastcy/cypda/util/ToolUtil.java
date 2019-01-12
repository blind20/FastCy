package com.xs.fastcy.cypda.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Base64;

import com.flj.latte.util.log.LatteLogger;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToolUtil {

    public static boolean isEmpty(String str) {
        if(TextUtils.isEmpty(str) || "null".equalsIgnoreCase(str)){
            return true;
        }
        return false;
    }

    /**
     * 判断字符串中是否包含字母
     */
    public static boolean isContainsStr(String str) {
        String regex=".*[a-zA-Z]+.*";
        Matcher m=Pattern.compile(regex).matcher(str);
        return m.matches();
    }

    /**
     * 判断字符串是否整数
     *  整数返回true,否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static String getSysCurTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    public static String getCurDate() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static String getDateByString(String longtime) {
        long time = Long.parseLong(longtime);
        Date date = new Date(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }


    public static String[] getArrayByResouce(Context context, int resId){
        return  context.getResources().getStringArray(resId);
    }

    public static Integer getIndexByValueWithResouce(Context context,int resId,String value ){
        String[] array = getArrayByResouce(context,resId);
        for (int i =0;i<array.length;i++){
            if(value.equals(array[i])){
                return i;
            }
        }
        return null;
    }

    public static String getValueByKeyInArrayRes(Context context, String existKey, int existKeyArrayResId, int valueArrayResId){
        Integer index = getIndexByValueWithResouce(context,existKeyArrayResId,existKey);
        String[] valueArray = getArrayByResouce(context,valueArrayResId);
        if(index !=null){
            return valueArray[index];
        }
        return null;
    }

    /**
     * 类似这种数组
     * B33:轻型罐式半挂车
     * K33:小型汽车
     */
    public static String getValueByCodeInSpecialArray(Context context, String existKey,int arrayResId){
        String[] valueArray = getArrayByResouce(context,arrayResId);
        for (String value:valueArray) {
            String key = value.split(":")[0];
            if (key.equals(existKey)){
                return value;
            }
        }
        return null;
    }


    public static String bitmaptoString(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bytes = bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    /**
     * 获取当前本地apk的版本
     */
    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取版本号名称
     */
    public static String getVersionName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }

}
