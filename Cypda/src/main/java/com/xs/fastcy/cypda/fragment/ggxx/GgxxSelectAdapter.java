package com.xs.fastcy.cypda.fragment.ggxx;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.flj.latte.ui.recycler.MultipleFields;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.flj.latte.ui.recycler.MultipleRecyclerAdapter;
import com.flj.latte.ui.recycler.MultipleViewHolder;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.fragment.VehItemType;
import com.xs.fastcy.cypda.fragment.baseinfo.VehBasicItemFields;
import com.xs.fastcy.cypda.fragment.list.VehCyxmItemFields;
import com.xs.fastcy.cypda.fragment.list.VehCyxmItemType;

import java.util.List;

public class GgxxSelectAdapter extends MultipleRecyclerAdapter {

    GgxxSelectAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(VehItemType.ITEM_GGXX_SELECT, R.layout.item_ggxx_select);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()){
            case VehItemType.ITEM_GGXX_SELECT:
                final AppCompatTextView tv_ggbh = holder.getView(R.id.tv_ggbh);
                final AppCompatTextView tv_clxh = holder.getView(R.id.tv_clxh);
                final AppCompatTextView tv_ggrq = holder.getView(R.id.tv_ggrq);

                final String ggbh = entity.getField(GgxxSelectItemFields.ITEM_GGBH);
                final String clxh = entity.getField(GgxxSelectItemFields.ITEM_CLXH);
                final String ggrq = entity.getField(GgxxSelectItemFields.ITEM_GGRQ);
                tv_ggbh.setText(ggbh);
                tv_clxh.setText(clxh);
                tv_ggrq.setText(ggrq);
                break;
            default:
                break;
        }
    }
}
