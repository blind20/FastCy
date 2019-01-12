package com.xs.fastcy.cypda.fragment.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.flj.latte.delegates.LatteDelegate;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.R2;
import com.xs.fastcy.cypda.fragment.VehItemType;
import com.xs.fastcy.cypda.util.ToolUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

public class HgzOr01C21Frm extends LatteDelegate {

    boolean mIsNewCar = true;//新车true;在用车flase
    boolean mIsLocalCity = true;

    @BindView(R2.id.rv_hge_ggxx)
    RecyclerView mRecyclerView = null;
    Set<Map.Entry<String, Object>> set = null;
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
        Bundle bundle = getArguments();
        mIsNewCar = bundle.getBoolean("isNewCar");
        mIsLocalCity = bundle.getBoolean("isLocalCity");
        String jo = bundle.getString("data");
        if(!ToolUtil.isEmpty(jo)){
            setListView(JSON.parseObject(jo),mIsNewCar,mIsLocalCity);
        }
    }

    private void setListView(JSONObject jo,boolean isNewCar,boolean isLocalCity){
        String[] keys ;
        String[] values ;
        if(isNewCar){
            keys = getResources().getStringArray(R.array.hgz_key);
            values = getResources().getStringArray(R.array.hgz_value);
        }else if(!isNewCar && isLocalCity){
            keys = getResources().getStringArray(R.array.veh_all_baseinfo);
            values = getResources().getStringArray(R.array.veh_all_baseinfo_map);
        }else {
            keys = getResources().getStringArray(R.array.veh_simple_baseinfo);
            values = getResources().getStringArray(R.array.veh_simple_baseinfo_map);
        }

        final List<SimpleListBean> datas = new ArrayList<>();
        for (int i = 0; i <keys.length ; i++) {
            String value = jo.getString(keys[i]);
            SimpleListBean bean = new SimpleListBean.Builder()
                    .setItemType(VehItemType.ITEM_SIMPLE_LIST_2)
                    .setText(values[i])
                    .build();
            if (ToolUtil.isEmpty(value)){
                bean.setItemValue("");
            }else{
                bean.setItemValue(value);
            }
            datas.add(bean);
        }
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        final SimpleListAdapter adapter = new SimpleListAdapter(datas);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void post(Runnable runnable) {}
}
