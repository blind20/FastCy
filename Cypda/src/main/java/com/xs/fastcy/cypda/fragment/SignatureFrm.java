package com.xs.fastcy.cypda.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ToastUtils;
import com.flj.latte.delegates.LatteDelegate;
import com.flj.latte.net.RestClient;
import com.flj.latte.net.callback.IError;
import com.flj.latte.net.callback.IFailure;
import com.flj.latte.net.callback.ISuccess;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.xs.fastcy.cypda.CyConstant;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.R2;
import com.xs.fastcy.cypda.database.FastCyDbUtil;
import com.xs.fastcy.cypda.entity.User;
import com.xs.fastcy.cypda.entity.VehCheckInfo;
import com.xs.fastcy.cypda.ui.OnMultiClickListener;
import com.xs.fastcy.cypda.util.BeanRefUtil;
import com.xs.fastcy.cypda.util.ToolUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class SignatureFrm extends LatteDelegate {

    private VehCheckInfo mVehCheckInfo;
    private Context mContext;
    private FastCyDbUtil mFastCyDbUtil;

    @BindView(R2.id.tv_lsh)
    AppCompatTextView mLsh;
    @BindView(R2.id.tv_clsbdh)
    AppCompatTextView mClsbdh;
    @BindView(R2.id.signature_pad)
    SignaturePad mSignaturePad;
    @BindView(R2.id.clear_button)
    Button mClearButton;
    @BindView(R2.id.save_button)
    Button mSaveButton;
    @BindView(R2.id.sp_cyjl)
    AppCompatSpinner mCyjlSpinner;
    @BindView(R2.id.tv_next)
    AppCompatTextView mUploadTextView;

    @OnClick({R2.id.clear_button,R2.id.save_button,R2.id.tv_back,R2.id.btn_savetodb})
    void onClickEvent(View view){
        switch (view.getId()){
            case R.id.clear_button:
                mSignaturePad.clear();
                break;
            case R.id.save_button:
                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
                uploadSignImg(signatureBitmap,CyConstant.CYY_SIGNATURE,mVehCheckInfo);
                break;
            case R.id.tv_back:
                getSupportDelegate().pop();
                break;
            case R.id.btn_savetodb:
                VehCheckInfo veh = setCyjlToVehChek(mVehCheckInfo);
                mFastCyDbUtil.updateVehCheckInfo(veh);
                ToastUtils.showShort("保存成功");
                getSupportDelegate().popTo(CyBottomDelegate.class,false);
                break;
        }
    }



    private void uploadCyxm(final VehCheckInfo vehCheckInfo){
        WeakHashMap<String, Object> params = getWeakHashMapFromVehCheck(vehCheckInfo);
        RestClient.builder().url("pdaService/addVehCheckInfo")
                .loader(getContext())
                .params(params)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        if(!ToolUtil.isEmpty(response)){
                            JSONObject jo = JSON.parseObject(response);
                            int state = jo.getInteger("state");
                            String msg = jo.getString("message");
                            if(state==CyConstant.REQ_OK){
                                getSupportDelegate().popTo(CyBottomDelegate.class,false);
                                mFastCyDbUtil.deleteVehPhotoByLsh(vehCheckInfo.getLsh());
                                mFastCyDbUtil.deleteVehCheckInfo(vehCheckInfo);
                                ToastUtils.showShort("上传成功");
                            }else {
                                if(!TextUtils.isEmpty(msg)){
                                    ToastUtils.showLong("上传失败1:"+msg);
                                }
                            }
                        }
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        mFastCyDbUtil.insertVehCheckInfo(vehCheckInfo);
                        ToastUtils.showLong("网络故障上传失败,请检查移动警务是否开启");
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        mFastCyDbUtil.insertVehCheckInfo(vehCheckInfo);
                        ToastUtils.showLong("上传失败,服务器返回:"+msg+",code="+code);
                    }
                })
                .build().post();

    }

    @NonNull
    private WeakHashMap<String, Object> getWeakHashMapFromVehCheck(VehCheckInfo veh) {
        VehCheckInfo vehCheckInfo = setCyjlToVehChek(veh);
        WeakHashMap<String, Object> params =  BeanRefUtil.getFieldValueMap(vehCheckInfo);
        Set<Map.Entry<String, Object>> set=params.entrySet();
        Iterator<Map.Entry<String, Object>> it = set.iterator();
        while (it.hasNext()){
            Map.Entry<String, Object> entry=it.next();
            Object value = entry.getValue();
            if(value!=null){
                if(value instanceof String){
                    if(ToolUtil.isEmpty((String)value)){
                        it.remove();
                    }
                }
            }else {
                it.remove();
            }
        }
        return params;
    }

    private VehCheckInfo setCyjlToVehChek(VehCheckInfo vehCheckInfo) {
        String cyjl =mCyjlSpinner.getSelectedItem().toString().split(":")[0];
        vehCheckInfo.setCyjg(cyjl);
        User user = new FastCyDbUtil().queryLoginUser();
        vehCheckInfo.setCyr(user.getSfzmhm());
        return vehCheckInfo;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mContext = getContext();
        mFastCyDbUtil = new FastCyDbUtil();
        mVehCheckInfo = (VehCheckInfo) getArguments().getSerializable("data");
        mLsh.setText(mVehCheckInfo.getLsh());
        mClsbdh.setText(mVehCheckInfo.getClsbdh());
        ArrayAdapter<String> cyjlAdapter = new ArrayAdapter<>(getContext(),
                R.layout.item_spinnerselect,
                getResources().getStringArray(R.array.cyjl));
        cyjlAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCyjlSpinner.setAdapter(cyjlAdapter);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                ToastUtils.showShort("onStartSigning");
            }
            @Override
            public void onSigned() {
                mSaveButton.setEnabled(true);
                mClearButton.setEnabled(true);
            }
            @Override
            public void onClear() {
                mSaveButton.setEnabled(false);
                mClearButton.setEnabled(false);
            }
        });
        mUploadTextView.setOnClickListener(new OnMultiClickListener(){
            @Override
            public void onMultiClick(View view) {
                uploadCyxm(mVehCheckInfo);
            }
        });
    }



    @Override
    public Object setLayout() {
        return R.layout.frm_signature;
    }

    /**
     * 上传签名图片
     */
    private void uploadSignImg(Bitmap bmp, String zpCode,VehCheckInfo vehCheckInfo) {
        Map<String,Object> params = new HashMap<>();
        params.put("lsh",vehCheckInfo.getLsh());
        params.put("clsbdh",vehCheckInfo.getClsbdh());
        params.put("hpzl",vehCheckInfo.getHpzl());
        params.put("zpzl",zpCode);
        params.put("jccs",vehCheckInfo.getCycs());
        String base64 = ToolUtil.bitmaptoString(bmp);
        params.put("imageStr",base64);
        JSONObject jo = JSONObject.parseObject(JSON.toJSONString(params));
        RestClient.builder().url("pdaService/uploadImageFile")
                .loader(getContext())
                .raw(jo.toString())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        if(!ToolUtil.isEmpty(response)){
                            JSONObject jo = JSON.parseObject(response);
                            ToastUtils.showShort(jo.getString("message"));
                        }
                    }
                })
                .build().post();
    }

    @Override
    public void post(Runnable runnable) {}
}
