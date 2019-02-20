package com.xs.fastcy.cypda.util;

import android.content.Context;

import com.flj.latte.util.log.LatteLogger;
import com.xs.fastcy.cypda.R;

public class CsysConvert {

    public static String convertSingleCsysToCode(Context context,String singleColor){
        String[] array = ToolUtil.getArrayByResouce(context,R.array.csys);
        for (String color:array) {
            if(singleColor.contains(color)){
                return ToolUtil.getValueByKeyInArrayRes(context,color,R.array.csys,R.array.csyscode);
            }
        }
        return singleColor;
    }

    public static String convertMultiCsysToCode(Context context,String color){
        String[] array = color.split(",");
        String codes = "";
        for (int i = 0; i<array.length ; i++) {
            LatteLogger.i("convertMultiCsysToCode","convertMultiCsysToCode="+array[i]);
            String singleCode = convertSingleCsysToCode(context,array[i]);
            codes = codes + "," + singleCode ;
        }
        LatteLogger.i("codes","codes="+codes);
        return codes.substring(1,codes.length());
    }
}
