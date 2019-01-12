package com.xs.fastcy.cypda.fragment.ggxx;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ToastUtils;
import com.flj.latte.delegates.LatteDelegate;
import com.flj.latte.net.RestClient;
import com.flj.latte.net.callback.ISuccess;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.flj.latte.util.log.LatteLogger;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.R2;
import com.xs.fastcy.cypda.fragment.baseinfo.VehBasicInfoDataConverter;
import com.xs.fastcy.cypda.net.TestData;
import com.xs.fastcy.cypda.util.ToolUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class GgxxSelectFrm extends LatteDelegate implements ISuccess {

    @BindView(R2.id.rv_hge_ggxx)
    RecyclerView mRecyclerView = null;

    @BindView(R2.id.tv_title)
    AppCompatTextView mTitle;

    GgxxSelectAdapter mAdapter;

    @Override
    public Object setLayout() {
        return R.layout.frm_hgz_ggxx;
    }

    @OnClick(R2.id.tv_back)
    public void onClickBack(){
        getSupportDelegate().pop();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mTitle.setText("选择公告");
        Bundle bundle = getArguments();
        String clxh = bundle.getString("clxh");
        String fzrq = bundle.getString("fzrq");
        RestClient.builder().url("pdaService/getAllGongGaoListbyCLXH")
                .loader(getContext()).params("clxh",clxh)
                .success(this).build().post();
        ////--------------------测试代码开始--------------------------------
//        onSuccess(TestData.getGongGaoArray());
        ////--------------------测试代码结束--------------------------------
    }

    @Override
    public void onSuccess(String response) {
        if(!ToolUtil.isEmpty(response)){
            Object object = JSON.parse(response);
            if(object instanceof JSONArray) {
                final ArrayList<MultipleItemEntity> data = new GgxxSelectDataConverter()
                        .setJsonData(response).convert();
                mAdapter = new GgxxSelectAdapter(data);
                final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                mRecyclerView.setLayoutManager(manager);
                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.addOnItemTouchListener(new GgxxSelectClickListener(this));
            }
        }
    }

    @Override
    public void post(Runnable runnable) {}





}
