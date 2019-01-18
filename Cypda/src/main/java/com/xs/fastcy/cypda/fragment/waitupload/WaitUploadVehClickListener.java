package com.xs.fastcy.cypda.fragment.waitupload;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.ToastUtils;
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
    private Context mContext;
    private FastCyDbUtil mDbUtil;
    public WaitUploadVehClickListener(WaitUploadVehListFrm fragment){
        this.DELEGATE = fragment;
        this.mContext = DELEGATE.getContext();
        this.mDbUtil = new FastCyDbUtil(mContext);
    }
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) adapter.getData().get(position);
        String lsh = entity.getField(WaitUploadVehItemFields.ITEM_WAIT_UPLOAD_VEH_LSH);
        FastCyDbUtil dbUtil = new FastCyDbUtil(DELEGATE.getContext());
        List<VehCheckInfo> list = dbUtil.queryVehCheckInfoByLsh(lsh);
        try {
            if(list!=null || list.size()>0){
                VehCheckInfo vehCheckInfo = list.get(0);
                Bundle bundle =  new Bundle();
                bundle.putSerializable("vehCheckInfo",vehCheckInfo);
                VehBasicInfoFrm vehBasicInfoFrm = new VehBasicInfoFrm();
                vehBasicInfoFrm.setArguments(bundle);
                DELEGATE.getParentDelegate().getSupportDelegate().start(vehBasicInfoFrm);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showShort("请刷新");
        }
    }



    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        List<MultipleItemEntity> list = adapter.getData();
        LatteLogger.e("onItemLongClick","position="+position);
        LatteLogger.json("onItemLongClick",JSON.toJSONString(list));
        dialogOnItemLongClick(adapter,list,position);
    }

    private void dialogOnItemLongClick(final BaseQuickAdapter adapter,final List<MultipleItemEntity> list, final int position) {
        String[] array = new String[]{"删除"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setItems(array, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                MultipleItemEntity entity = list.get(position);
                String lsh = entity.getField(WaitUploadVehItemFields.ITEM_WAIT_UPLOAD_VEH_LSH);
                mDbUtil.deleteVehCheckInfoByLsh(lsh);
                mDbUtil.deleteVehPhotoByLsh(lsh);
                list.remove(position);
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position,adapter.getItemCount());
            }
        }).show();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {}
    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {}
}
