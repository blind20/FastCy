package com.xs.fastcy.cypda.fragment.cyxm;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.flj.latte.delegates.LatteDelegate;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.flj.latte.util.log.LatteLogger;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.R2;
import com.xs.fastcy.cypda.entity.VehCheckInfo;
import com.xs.fastcy.cypda.fragment.SignatureFrm;
import com.xs.fastcy.cypda.fragment.list.VehCyxmItemFields;
import com.xs.fastcy.cypda.util.BeanRefUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class VehCyxmsFrm extends LatteDelegate {

    private VehCheckInfo mVehCheckInfo=null;
    private VehCyxmAdapter mAdapter =null;
    @BindView(R2.id.rv_veh_cyxm)
    RecyclerView mRecyclerView = null;

    @OnClick(R2.id.tv_back)
    public void onClickBack(){
        getSupportDelegate().pop();
    }
    @OnClick(R2.id.tv_next)
    void onClickNext(){
        if(mVehCheckInfo!=null){
            setVehCyxmToBean(mVehCheckInfo,mAdapter);
            Bundle bundle = new Bundle();
            bundle.putSerializable("data",mVehCheckInfo);
            SignatureFrm signatureFrm = new SignatureFrm();
            signatureFrm.setArguments(bundle);
            getSupportDelegate().start(signatureFrm);
        }
    }


    @Override
    public Object setLayout() {
        return R.layout.frm_veh_cyxms;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {}

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final Context con = getContext();
        mVehCheckInfo = (VehCheckInfo) getArguments().getSerializable("data");
        final LinearLayoutManager manager = new LinearLayoutManager(con);
        mRecyclerView.setLayoutManager(manager);
        final ArrayList<MultipleItemEntity> data = new VehCyxmDataConverter()
                .setmXczl(mVehCheckInfo.getXczl())
                .setContext(con)
                .convert();
        mAdapter = new VehCyxmAdapter(data);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new VehCyxmClickListener(this,mAdapter));
    }

    private VehCheckInfo setVehCyxmToBean(VehCheckInfo veh,VehCyxmAdapter adapter) {
        final List<MultipleItemEntity> data = adapter.getData();
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i <data.size() ; i++) {
            MultipleItemEntity entity = data.get(i);
            String fieldName = entity.getField(VehCyxmItemFields.ITEM_CYXM_FIELD);
            int fieldValue = entity.getField(VehCyxmItemFields.ITEM_CYXM_RESULT_VALUE);
            map.put(fieldName,String.valueOf(fieldValue));
        }
        BeanRefUtil.setFieldValue(veh,map);
        return veh;
    }

    @Override
    public void post(Runnable runnable) {}
}
