package com.xs.fastcy.cypda.fragment.cyxm;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.xs.fastcy.cypda.fragment.list.VehCyxmItemFields;

public class VehCyxmClickListener extends SimpleClickListener {

    private final VehCyxmsFrm DELEGATE;
    private final String[] mArray = new String[]{"√", "-", "×"};
    private Integer mSelect=null;
    private VehCyxmAdapter mAdapter;

    public VehCyxmClickListener(VehCyxmsFrm fragment,VehCyxmAdapter adapter) {
        this.DELEGATE = fragment;
        this.mAdapter = adapter;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) adapter.getData().get(position);
        getItemsDialog(mArray,entity,position);
    }

    private void getItemsDialog(final String[] array, final MultipleItemEntity entity, final int position){
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setItems(array,  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mSelect = which;
                entity.setField(VehCyxmItemFields.ITEM_CYXM_RESULT_VALUE,mSelect);
                entity.setField(VehCyxmItemFields.ITEM_CYXM_RESULT_NAME,mArray[mSelect]);
                mAdapter.setData(position,entity);
                mAdapter.notifyItemRangeChanged(0,mAdapter.getItemCount());
                dialog.cancel();
            }
        }).show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
    }
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
    }
    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
    }
}
