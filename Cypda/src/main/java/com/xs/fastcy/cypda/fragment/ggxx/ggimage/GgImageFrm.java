package com.xs.fastcy.cypda.fragment.ggxx.ggimage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.blankj.utilcode.util.ToastUtils;
import com.flj.latte.delegates.LatteDelegate;
import com.flj.latte.net.RestClient;
import com.flj.latte.net.callback.ISuccess;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.xs.fastcy.cypda.CyConstant;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.R2;
import com.xs.fastcy.cypda.net.TestData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class GgImageFrm extends LatteDelegate implements ISuccess {

    private GgImageGridAdapter mAdapter;
    private Context mContext;

    @BindView(R2.id.rv_takephoto)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.tv_title)
    AppCompatTextView mTitle;
    @BindView(R2.id.tv_back)
    AppCompatTextView mLeftTitle;
    @BindView(R2.id.tv_next)
    AppCompatTextView mRightTitle;

    @OnClick(R2.id.tv_back)
    void onClickBack(){
        getSupportDelegate().pop();
    }

    @Override
    public Object setLayout() {
        return R.layout.frm_takephoto;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mTitle.setText("公告照片列表");
        mLeftTitle.setText("返回");
        mRightTitle.setVisibility(View.INVISIBLE);
        mContext = getContext();
        Bundle bundle = getArguments();
        String ggbh = bundle.getString("bh");
        RestClient.builder().url("pdaService/getGongGaoImagesByBh")
                .loader(getContext())
                .params("bh",ggbh)
                .success(this)
                .build().post();
    }

    @Override
    public void post(Runnable runnable) {}

    @Override
    public void onSuccess(String response) {
        Object object = JSON.parse(response);
        if(object instanceof JSONArray) {
            GridLayoutManager manager = new GridLayoutManager(mContext, 3);
            mRecyclerView.setLayoutManager(manager);
            final ArrayList<MultipleItemEntity> data = new GgImageDataConverter()
                    .setJsonData(response)
                    .setContext(mContext).convert();
            mAdapter = new GgImageGridAdapter(data);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.addOnItemTouchListener(new GgImageClickListener(this));
        }


    }
}
