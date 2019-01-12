package com.xs.fastcy.cypda.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.flj.latte.delegates.bottom.BottomItemDelegate;
import com.flj.latte.net.RestClient;
import com.flj.latte.net.callback.ISuccess;
import com.flj.latte.util.callback.CallbackManager;
import com.flj.latte.util.callback.CallbackType;
import com.flj.latte.util.callback.IGlobalCallback;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.R2;
import com.xs.fastcy.cypda.fragment.baseinfo.VehBasicInfoFrm;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class NewCarScanFrm extends BottomItemDelegate implements ISuccess {

    private boolean mIsSchoolCar =false;

    @BindView(R2.id.et_lsh)
    AppCompatEditText mLsh=null;

    @BindView(R2.id.checkbox)
    AppCompatCheckBox checkBox=null;

    @OnCheckedChanged(R2.id.checkbox)
    void onChecked(boolean isChecked){
        mIsSchoolCar = isChecked;
    }

    @OnClick(R2.id.icon_scan)
    void onClickScanLsh(){
        startScanWithCheck(getParentDelegate());
    }

    @OnClick(R2.id.btn_query)
    void  onClickQuery(){
        RestClient.builder().url("pdaService/findPreCarRegisterByLsh")
                .loader(getContext())
                .params("lsh", mLsh.getText().toString())
                .success(this).build().post();
    }

    @Override
    public Object setLayout() {
        return R.layout.frm_newcarscan;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_SCAN, new IGlobalCallback<String>() {
                    @Override
                    public void executeCallback(@Nullable String args) {
                        mLsh.setText(args.trim());
                    }
                });
    }

    @Override
    public void onSuccess(String response) {
        JSONObject jo  = JSON.parseObject(response).getJSONObject("data");
        if(jo!=null&&!jo.isEmpty()){
            Bundle bundle = new Bundle();
            bundle.putString("data",jo.toString());
            bundle.putString("gcjk",jo.getString("gcjk"));
            bundle.putBoolean("isSchoolCar", mIsSchoolCar);
            bundle.putBoolean("isNewCar", true);
            VehBasicInfoFrm vehBasicInfoFrm = new VehBasicInfoFrm();
            vehBasicInfoFrm.setArguments(bundle);
            getParentDelegate().getSupportDelegate().start(vehBasicInfoFrm);
        }
    }

    @Override
    public void post(Runnable runnable) { }
}
