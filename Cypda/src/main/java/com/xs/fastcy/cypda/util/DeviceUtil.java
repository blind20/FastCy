package com.xs.fastcy.cypda.util;

import android.content.Context;
import android.provider.Settings;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DeviceUtil {

    public static String getAndroidId(Context context){
       return Settings.System.getString(context.getContentResolver(),  Settings.Secure.ANDROID_ID);
    }

    public static String createInUseCarLsh(Context context){
        String androidId = getAndroidId(context);
        String curTime =getSysCurTime().substring(2);
        return androidId+curTime;
    }

    public static String getSysCurTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(new Date());
    }
}
