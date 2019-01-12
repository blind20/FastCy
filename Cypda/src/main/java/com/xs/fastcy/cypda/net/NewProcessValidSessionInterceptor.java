package com.xs.fastcy.cypda.net;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.flj.latte.app.Latte;
import com.flj.latte.util.file.SharedPreferenceUtils;
import com.flj.latte.util.log.LatteLogger;
import com.xs.fastcy.cypda.CyConstant;
import com.xs.fastcy.cypda.util.ToolUtil;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;

public class NewProcessValidSessionInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Response originalResponse = chain.proceed(originalRequest);
        ResponseBody responseBody = originalResponse.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE);
        String respString = source.buffer().clone().readString(Charset.defaultCharset());
        String state;
        if (originalResponse.code() == 200) {
            Object object = JSON.parse(respString);
            if(object instanceof JSONObject){
                JSONObject jsonObject = (JSONObject) object;
                state = jsonObject.getString("state");
                if (state != null && state.equals("600")) {
                    String userName = (String) SharedPreferenceUtils.get(Latte.getApplicationContext(),
                            CyConstant.SHAREPREFERENCE_USERNAME, "");
                    String password = (String) SharedPreferenceUtils.get(Latte.getApplicationContext(),
                            CyConstant.SHAREPREFERENCE_PASSWORD, "");
                    if (!ToolUtil.isEmpty(userName) && !ToolUtil.isEmpty(password)) {
                        Request loginRequest = getLoginRequest(userName, password);
                        Response loginResponse = chain.proceed(loginRequest);
                        if (loginResponse.isSuccessful()) {
                            loginResponse.body().close();
                            return chain.proceed(originalRequest);
                        }

                    }
                }
            }

        }
        return originalResponse;
    }

    private Request getLoginRequest(String userName, String password) {
        return new Request.Builder().url(CyConstant.PROCESSVALIDSESSIONINTERCEPTORURL)
                .post(new FormBody.Builder()
                        .add("userName", userName)
                        .add("password", password).build())
                .build();
    }
}
