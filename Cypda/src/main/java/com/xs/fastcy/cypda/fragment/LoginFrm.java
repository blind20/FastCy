package com.xs.fastcy.cypda.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.blankj.utilcode.util.ToastUtils;
import com.flj.latte.app.Latte;
import com.flj.latte.delegates.LatteDelegate;
import com.flj.latte.net.RestClient;
import com.flj.latte.net.callback.IError;
import com.flj.latte.net.callback.IFailure;
import com.flj.latte.net.callback.ISuccess;
import com.flj.latte.util.file.SharedPreferenceUtils;
import com.xs.fastcy.cypda.CyConstant;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.R2;
import com.xs.fastcy.cypda.database.FastCyDbUtil;
import com.xs.fastcy.cypda.entity.User;
import com.xs.fastcy.cypda.fragment.ggxx.ggimage.GgImageFrm;
import com.xs.fastcy.cypda.util.ToolUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFrm extends LatteDelegate implements ISuccess,IFailure,IError{

    @BindView(R2.id.edit_sign_in_user)
    TextInputEditText mUsername=null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword=null;
    @BindView(R2.id.tv_ver_name)
    AppCompatTextView mVersionName=null;

    @Override
    public Object setLayout() {
        return R.layout.frm_login;
    }

    @OnClick(R2.id.btn_login)
    void onClickLoginBtn(){
        if(checkForm()){
            RestClient.builder().url("user/login")
                    .loader(getContext())
                    .params("password", mPassword.getText().toString())
                    .params("userName", mUsername.getText().toString())
                    .success(this).build().post();

        }
    }


    private boolean checkForm() {
        final String username = mUsername.getText().toString();
        final String password = mPassword.getText().toString();
        boolean isPass = true;
        if (username.isEmpty() ) {
            mUsername.setError("用户名不能为空");
            isPass = false;
        } else {
            mUsername.setError(null);
        }
        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }
        return isPass;
    }
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mVersionName.setText(ToolUtil.getVersionName(getContext()));
        String userName = (String) SharedPreferenceUtils.get(getContext(),CyConstant.SHAREPREFERENCE_USERNAME,"");
        String password = (String) SharedPreferenceUtils.get(getContext(),CyConstant.SHAREPREFERENCE_PASSWORD,"");
        if(!ToolUtil.isEmpty(userName)&&!ToolUtil.isEmpty(password)){
            //抚州要求不显示用户名密码
//            mUsername.setText(userName);
//            mPassword.setText(password);
        }
    }

    @Override
    public void onSuccess(String response) {
        int state = JSON.parseObject(response).getInteger("state");
        if(state==CyConstant.REQ_OK){
            checkApkIsNew(response);
        }else{
            ToastUtils.showLong(JSON.parseObject(response).getString("message"));
        }
    }

    private void loginToMainFrm(String response) {
        JSONObject jo = JSON.parseObject(response).getJSONObject("data");
        User user = new User();
        user.setUsername(mUsername.getText().toString());
        user.setPassword(mPassword.getText().toString());
        user.setYhxm(jo.getString("yhxm"));
        user.setGlbm(jo.getString("bmdm"));
        user.setBmmc(jo.getString("bmmc"));
        user.setSfzmhm(jo.getString("sfzh"));
        user.setIsLoginTag(true);
        new FastCyDbUtil().insertUser(user);
        SharedPreferenceUtils.put(Latte.getApplicationContext(),"userName",user.getUsername());
        SharedPreferenceUtils.put(Latte.getApplicationContext(),"password",user.getPassword());
        getSupportDelegate().start(new CyBottomDelegate());
    }


    private boolean checkApkIsNew(final String loginResponse){
        final int localVersion = ToolUtil.getVersionCode(getContext());
        RestClient.builder().url(CyConstant.APK_CHECK_URL)
                .loader(getContext()).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                if(!ToolUtil.isEmpty(response)){
                    JSONObject jo = JSON.parseObject(response);
                    int remoteVersion =jo.getInteger("version");
                    if(localVersion>=remoteVersion){
                        loginToMainFrm(loginResponse);
                    }else {
                        AllenVersionChecker.getInstance()
                                .downloadOnly(crateUIData(CyConstant.APK_UPDATE_URL))
                                .excuteMission(getContext());
                    }
                }
            }
        }).error(new IError() {
            @Override
            public void onError(int code, String msg) {
                loginToMainFrm(loginResponse);
            }
        }).build().get();
        return false;
    }

    private UIData crateUIData(String downloadUrl) {
        UIData uiData = UIData.create();
        uiData.setTitle("查验终端");
        uiData.setDownloadUrl(downloadUrl);
        uiData.setContent("机动车查验终端版本更新");
        return uiData;
    }


    @Override
    public void onError(int code, String msg) {
        ToastUtils.showLong("登陆失败:"+msg+";code="+code);
    }
    @Override
    public void onFailure() {
        ToastUtils.showLong("登陆失败");
    }
    @Override
    public void post(Runnable runnable) { }
}
