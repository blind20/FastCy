package com.xs.fastcy.cypda.fragment.waitupload;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.flj.latte.delegates.bottom.BottomItemDelegate;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.R2;
import com.xs.fastcy.cypda.database.FastCyDbUtil;
import com.xs.fastcy.cypda.entity.VehCheckInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WaitUploadVehListFrm extends BottomItemDelegate {

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

    @Override
    public Object setLayout() {
        return R.layout.frm_veh_cyxms;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mContext = getContext();
        mTextBack.setVisibility(View.INVISIBLE);
        mTextNext.setText("一周内");
        mTextTitle.setText("未上传车辆");
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        List<VehCheckInfo> vehs = new FastCyDbUtil(mContext).queryAllVehCheckInfo();
        final LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(manager);
        final ArrayList<MultipleItemEntity> data = new WaitUploadVehDataConvert()
                .setVehCheckInfos(vehs)
                .convert();
        mAdapter = new WaitUploadVehListAdapter(data);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new WaitUploadVehClickListener(this));
    }

    @Override
    public void post(Runnable runnable) {}
}
