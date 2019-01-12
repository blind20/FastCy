package com.xs.fastcy.cypda.fragment.ggxx;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ToastUtils;
import com.flj.latte.delegates.LatteDelegate;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.R2;
import com.xs.fastcy.cypda.fragment.VehItemType;
import com.xs.fastcy.cypda.fragment.ggxx.ggimage.GgImageFrm;
import com.xs.fastcy.cypda.fragment.list.SimpleListAdapter;
import com.xs.fastcy.cypda.fragment.list.SimpleListBean;
import com.xs.fastcy.cypda.util.ToolUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GgxxDetailFrm extends LatteDelegate {

    String mJson;

    @BindView(R2.id.rv_hge_ggxx)
    RecyclerView mRecyclerView = null;

    @BindView(R2.id.tv_title)
    AppCompatTextView mTitle;
    @BindView(R2.id.tv_next)
    AppCompatTextView mRightText;

    @OnClick({R2.id.tv_back,R2.id.tv_next})
    public void onClickEvent(View view){
        switch (view.getId()){
            case R.id.tv_back:
                getSupportDelegate().pop();
                break;
            case R.id.tv_next:
                if(!ToolUtil.isEmpty(mJson)){
                    String bh = JSON.parseObject(mJson).getString("BH");
                    if(!ToolUtil.isEmpty(bh)){
                        Bundle bundle = new Bundle();
                        bundle.putString("bh",bh);
                        GgImageFrm ggImageFrm = new GgImageFrm();
                        ggImageFrm.setArguments(bundle);
                        getSupportDelegate().start(ggImageFrm);
                    }else {
                        ToastUtils.showLong("bh null");
                    }
                }
                break;
        }

    }

    @Override
    public Object setLayout() {
        return R.layout.frm_hgz_ggxx;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mTitle.setText("公告详细信息");
        mRightText.setText("公告照片");
        Bundle bundle = getArguments();
        mJson = bundle.getString("data");
        if(!ToolUtil.isEmpty(mJson)){
            setListView(JSON.parseObject(mJson));
        }
    }

    private void setListView(JSONObject jo){
        String[] item_left_keys = getResources().getStringArray(R.array.ggxx);
        String[] item_left_values = getResources().getStringArray(R.array.ggxx_map);
        final List<SimpleListBean> datas = new ArrayList<>();
        for (int i = 0; i <item_left_keys.length ; i++) {
            String value = jo.getString(item_left_keys[i]);
            SimpleListBean bean = new SimpleListBean.Builder()
                    .setItemType(VehItemType.ITEM_SIMPLE_LIST_2)
                    .setText(item_left_values[i])
                    .build();
            if (ToolUtil.isEmpty(value)){
                bean.setItemValue("");
            }else{
                if(item_left_values[i].contains("日期")){
                    try{
                        bean.setItemValue(ToolUtil.getDateByString(value));
                    }catch (NumberFormatException e){
                        bean.setItemValue(value);
                    }

                }else {
                    bean.setItemValue(value);
                }
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
