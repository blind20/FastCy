package com.xs.fastcy.cypda.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.ArrayAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ToastUtils;
import com.flj.latte.delegates.bottom.BottomItemDelegate;
import com.flj.latte.net.RestClient;
import com.flj.latte.net.callback.IError;
import com.flj.latte.net.callback.IFailure;
import com.flj.latte.net.callback.ISuccess;
import com.flj.latte.util.file.SharedPreferenceUtils;
import com.flj.latte.util.log.LatteLogger;
import com.xs.fastcy.cypda.CyConstant;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.R2;
import com.xs.fastcy.cypda.fragment.baseinfo.VehBasicInfoFrm;
import com.xs.fastcy.cypda.net.TestData;
import com.xs.fastcy.cypda.util.DeviceUtil;
import com.xs.fastcy.cypda.util.ToolUtil;
import com.xs.fastcy.cypda.util.UpperCaseTransform;

import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class InUseCarFrm extends BottomItemDelegate implements ISuccess {

    private Context mContext;
    private boolean mIsSchoolCar =false;
    private boolean mIsLocalCity = true;
    private String mHphmFull;

    @BindView(R2.id.et_hphm)
    AppCompatEditText mEditText;

    @BindView(R2.id.sp_province)
    AppCompatSpinner mSfSpinner;

    @BindView(R2.id.sp_hpzl)
    AppCompatSpinner mHpzlSpinner;

    @OnCheckedChanged(R2.id.checkbox)
    void onChecked(boolean isChecked){
        mIsSchoolCar = isChecked;
    }

    @OnClick(R2.id.btn_query)
    void onClickQuery(){
        String hpzl =mHpzlSpinner.getSelectedItem().toString();
        String province = mSfSpinner.getSelectedItem().toString();
        String hphm = mEditText.getText().toString().toUpperCase().trim();
        if(!checkform(province,hphm,hpzl)){
            return;
        }
        reqInUseCar(hpzl, province, hphm);
    }

    private void reqInUseCar(String hpzl, String province, String hphm) {
        String city = hphm.substring(0,1);
        String hpzlCode = ToolUtil.getValueByKeyInArrayRes(mContext,hpzl,R.array.hpzl,R.array.hpzlcode);
        String url ;
        WeakHashMap<String,Object> params = new WeakHashMap<>();
        if(isOtherProvinceHphm(province,city)){
            mIsLocalCity = false;
            url = "pdaService/getAllCarInfoByCarNumber";
            params.put("sf",province);
            params.put("hpzl",hpzlCode);
            params.put("hphm",hphm);
            mHphmFull = province+hphm;
        }else {
            mIsLocalCity = true;
            url = "pdaService/getCarInfoByCarNumber";
            params.put("hpzl",hpzlCode);
            params.put("hphm",hphm);
            mHphmFull = CyConstant.SETTING_PDA_PROVINCE+hphm;
        }
//        RestClient.builder().url(url).loader(getContext())
//                .params(params)
//                .success(this)
//                .error(new IError() {
//                    @Override
//                    public void onError(int code, String msg) {
//                        ToastUtils.showShort("网络故障onError："+msg);
//                    }
//                })
//                .failure(new IFailure() {
//                    @Override
//                    public void onFailure() {
//                        ToastUtils.showShort("网络故障onFailure");
//                    }
//                })
//                .build().post();

        //-----------*********-测试代码开始**********--------------------------------
        onSuccess(TestData.getVehBaseInfo());
        //-----------*********-测试代码结束**********--------------------------------
    }

    @Override
    public Object setLayout() {
        return R.layout.frm_inusecar_query;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mContext = getContext();
        initView();
    }

    private boolean isOtherProvinceHphm(String province,String city){
        String sf = (String) SharedPreferenceUtils.get(mContext,"province",CyConstant.SETTING_PDA_PROVINCE);
        String xs = (String) SharedPreferenceUtils.get(mContext,"city",CyConstant.SETTING_PDA_CITY);
        if (sf.equals(province)&& xs.equals(city)) {
            return false;
        }
        return true;
    }

    private void initView(){
        setParamToEidtText();
        initSpinner();
    }

    private void setParamToEidtText(){
        mEditText.setTransformationMethod(new UpperCaseTransform());
        String editTextContent = (String) SharedPreferenceUtils.get(mContext,"city",CyConstant.SETTING_PDA_CITY);
        mEditText.setText(editTextContent);
        mEditText.setSelection(editTextContent.length());
        mEditText.requestFocus();
    }

    private void initSpinner(){
        String sf = (String) SharedPreferenceUtils.get(mContext,"province",CyConstant.SETTING_PDA_PROVINCE);
        Integer index = ToolUtil.getIndexByValueWithResouce(mContext,R.array.province,sf);

        ArrayAdapter<String> hpzlAdapter = new ArrayAdapter<>(mContext,R.layout.item_spinnerselect,
                getResources().getStringArray(R.array.hpzl));
        hpzlAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mHpzlSpinner.setAdapter(hpzlAdapter);

        ArrayAdapter<String> sfAdapter = new ArrayAdapter<>(mContext,android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.province));
        sfAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSfSpinner.setAdapter(sfAdapter);
        if (index!=null){
            mSfSpinner.setSelection(index);
        }
        mHpzlSpinner.setSelection(2);//号牌号码设置小型汽车
    }


    @Override
    public void onSuccess(String response) {
        if(!ToolUtil.isEmpty(response)){
            JSONObject jo  = JSON.parseObject(response).getJSONObject("data");
            Bundle bundle =  toVehBasicFrmBundle(jo);
            VehBasicInfoFrm vehBasicInfoFrm = new VehBasicInfoFrm();
            vehBasicInfoFrm.setArguments(bundle);
            getParentDelegate().getSupportDelegate().start(vehBasicInfoFrm);
        }else {
            ToastUtils.showLong("网络请求失败");
        }
    }

    private Bundle toVehBasicFrmBundle(JSONObject jo) {
        Bundle bundle = new Bundle();
        if(jo!=null){
            bundle.putString("data",jo.toString());
            bundle.putString("gcjk",jo.getString("gcjk"));
        }else {
            bundle.putString("gcjk","A");
        }
        bundle.putString("hphmfull",mHphmFull);
        bundle.putBoolean("isSchoolCar", mIsSchoolCar);
        bundle.putBoolean("isNewCar", false);
        bundle.putBoolean("isLocalCity", mIsLocalCity);
        return bundle;
    }

    /**
     * 检查是否空，号牌是否小于4位
     *
     */
    private boolean checkform(String province,String hphm,String hpzl){
        if (ToolUtil.isEmpty(province)||ToolUtil.isEmpty(hphm)||ToolUtil.isEmpty(hpzl)||hphm.length()<4){
            ToastUtils.showLong("请输入正确的号牌号码");
            return false;
        }
        return true;
    }



    @Override
    public void post(Runnable runnable) {}

}
