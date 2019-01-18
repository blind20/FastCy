package com.xs.fastcy.cypda.fragment.waitupload;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.flj.latte.delegates.bottom.BottomItemDelegate;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.flj.latte.util.log.LatteLogger;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.R2;
import com.xs.fastcy.cypda.database.FastCyDbUtil;
import com.xs.fastcy.cypda.entity.VehCheckInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WaitUploadVehListFrm extends BottomItemDelegate {

    @BindView(R2.id.tv_hint)
    TextView mTextHint;
    @BindView(R2.id.tv_back)
    AppCompatTextView mTextBack;
    @BindView(R2.id.tv_next)
    AppCompatTextView mTextNext;
    @BindView(R2.id.tv_title)
    AppCompatTextView mTextTitle;
    @BindView(R2.id.rv_veh_cyxm)
    RecyclerView mRecyclerView = null;

    private Context mContext;
    private WaitUploadVehListAdapter mAdapter;
    private List<VehCheckInfo> mVehList;

    ArrayList<MultipleItemEntity> mData;
    WaitUploadVehDataConvert mDataConvert;

    @Override
    public Object setLayout() {
        return R.layout.frm_veh_cyxms;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mContext = getContext();
        mTextBack.setVisibility(View.INVISIBLE);
        mTextHint.setVisibility(View.VISIBLE);
        mTextNext.setText("刷新");
        mTextTitle.setText("未上传车辆");
        mTextNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshItems();
            }
        });
    }

    private void refreshItems() {
        if(mAdapter!=null){
//            mVehList.clear();
            mVehList = new FastCyDbUtil(mContext).queryAllVehCheckInfo();
//            List<VehCheckInfo> list22= new FastCyDbUtil(mContext).query14DayBeforeVehCheckInfo();
//            LatteLogger.json("mVehList22",JSON.toJSONString(list22));
            mData.clear();
            mData =  mDataConvert.setVehCheckInfos(mVehList).convert();
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mVehList = new FastCyDbUtil(mContext).queryAllVehCheckInfo();
        mDataConvert = new WaitUploadVehDataConvert();
        final LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(manager);
        mData = mDataConvert.setVehCheckInfos(mVehList).convert();
        mAdapter = new WaitUploadVehListAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new WaitUploadVehClickListener(this));
    }

    @Override
    public void onResume() {
        LatteLogger.e("onresume","onresume+++++++++");
        if(mAdapter!=null){
            mVehList = new FastCyDbUtil(mContext).queryAllVehCheckInfo();
            mAdapter.notifyDataSetChanged();
        }
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        refreshItems();
        super.onHiddenChanged(hidden);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        LatteLogger.e("setUserVisibleHint","setUserVisibleHint+++++++++");
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
        } else {
            //相当于Fragment的onPause
        }
    }

    @Override
    public void post(Runnable runnable) {}
}
