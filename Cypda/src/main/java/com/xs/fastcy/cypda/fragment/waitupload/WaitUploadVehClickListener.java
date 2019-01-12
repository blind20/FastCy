package com.xs.fastcy.cypda.fragment.waitupload;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.flj.latte.util.log.LatteLogger;
import com.xs.fastcy.cypda.database.FastCyDbUtil;
import com.xs.fastcy.cypda.entity.VehCheckInfo;
import com.xs.fastcy.cypda.fragment.baseinfo.VehBasicInfoFrm;

import java.util.List;

public class WaitUploadVehClickListener extends SimpleClickListener {

    private WaitUploadVehListFrm DELEGATE;
    public WaitUploadVehClickListener(WaitUploadVehListFrm fragment){
        this.DELEGATE = fragment;
    }
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) adapter.getData().get(position);
        String lsh = entity.getField(WaitUploadVehItemFields.ITEM_WAIT_UPLOAD_VEH_LSH);
        FastCyDbUtil dbUtil = new FastCyDbUtil(DELEGATE.getContext());
        List<VehCheckInfo> list = dbUtil.queryVehCheckInfoByLsh(lsh);
        if(list!=null || list.size()>0){
            VehCheckInfo vehCheckInfo = list.get(0);
            Bundle bundle =  new Bundle();
            bundle.putSerializable("vehCheckInfo",vehCheckInfo);
            VehBasicInfoFrm vehBasicInfoFrm = new VehBasicInfoFrm();
            vehBasicInfoFrm.setArguments(bundle);
            DELEGATE.getParentDelegate().getSupportDelegate().start(vehBasicInfoFrm);
        }
    }



    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {}
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {}
    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {}
}
