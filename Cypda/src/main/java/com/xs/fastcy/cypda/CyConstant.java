package com.xs.fastcy.cypda;

//1.切换常州,照片种类调出来array.xml
//2.InUseCarFrm取消测试数据
//3.TakePhotoClickListener常州loader(mContext)注释
//4.图片质量
public class CyConstant {
    //测试
    public static final String SETTING_PDA_HOST = "192.168.43.166";
    public static final String SETTING_PDA_BASEURL  = "http://"+ SETTING_PDA_HOST.trim() +":8088/cms/";
    public static final String SETTING_PDA_PROVINCE = "赣";
    public static final String SETTING_PDA_CITY  = "F";

    //抚州
//    public static final String SETTING_PDA_HOST = "192.168.10.33";
//    public static final String SETTING_PDA_BASEURL  = "http://"+ SETTING_PDA_HOST.trim() +":34080/cms/";
//    public static final String SETTING_PDA_PROVINCE = "赣";
//    public static final String SETTING_PDA_CITY  = "F";


    //常州
//    public static final String SETTING_PDA_HOST = "127.0.0.1";
//    public static final String SETTING_PDA_BASEURL  = "http://"+ SETTING_PDA_HOST.trim() +":14001/cms/";
//    public static final String SETTING_PDA_PROVINCE = "苏";
//    public static final String SETTING_PDA_CITY  = "D";


    public static final int REQ_OK  = 1;
    //拦截器session过期调用登录接口
    public static final String PROCESSVALIDSESSIONINTERCEPTORURL = SETTING_PDA_BASEURL+"user/login";
    public static final String SHAREPREFERENCE_USERNAME = "userName";
    public static final String SHAREPREFERENCE_PASSWORD = "password";


    //请选择..业务类型/颜色/号牌种类
    public static final String PLEASE_SELECT_TYPE ="NULL";

    //查验员签名图片ZPZL代码值
    public static final String CYY_SIGNATURE ="49";
    public static final String ZPZL_45LEFT ="11";
    public static final String ZPZL_VIN ="30";

    public static final String YWLX_NEWCAR ="A";

    public static final String APK_UPDATE_URL=SETTING_PDA_BASEURL+"static/cache/apk/Cypda.apk";
    public static final String APK_CHECK_URL="static/cache/apk/update_apk.json";
}
