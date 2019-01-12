package com.xs.fastcy.cypda.fragment.cyxm;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.flj.latte.ui.recycler.MultipleFields;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.flj.latte.ui.recycler.MultipleRecyclerAdapter;
import com.flj.latte.ui.recycler.MultipleViewHolder;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.fragment.list.VehCyxmItemFields;
import com.xs.fastcy.cypda.fragment.list.VehCyxmItemType;

import java.util.List;

public class VehCyxmAdapter extends MultipleRecyclerAdapter {

    VehCyxmAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(VehCyxmItemType.ITEM_VEH_CYXM, R.layout.item_veh_cyxm);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()){
            case VehCyxmItemType.ITEM_VEH_CYXM:
                final AppCompatTextView tv_cyxm_seq = holder.getView(R.id.tv_cyxm_seq);
                final AppCompatTextView tv_cyxm_text = holder.getView(R.id.tv_cyxm_text);
                final AppCompatImageView iv_cyxm_checkflag = holder.getView(R.id.iv_cyxm_checkflag);
                final String seq = String.valueOf(entity.getField(MultipleFields.ID));

                final String cyxm = entity.getField(VehCyxmItemFields.ITEM_CYXM_NAME);
                final int checkRes = entity.getField(VehCyxmItemFields.ITEM_CYXM_RESULT_VALUE);
                tv_cyxm_seq.setText(seq);
                tv_cyxm_text.setText(cyxm);
                if(checkRes==0){
                    iv_cyxm_checkflag.setImageResource(R.mipmap.checkflg_pass);
                }else if(checkRes==2){
                    iv_cyxm_checkflag.setImageResource(R.mipmap.checkflg_fail);
                }else {
                    iv_cyxm_checkflag.setImageResource(R.mipmap.checkflg_notcheck);
                }
                break;
            default:
                break;
        }
    }
}
