package com.xs.fastcy.cypda.fragment.baseinfo;

import android.support.v7.widget.AppCompatTextView;

import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.flj.latte.ui.recycler.MultipleRecyclerAdapter;
import com.flj.latte.ui.recycler.MultipleViewHolder;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.fragment.VehItemType;

import java.util.List;

public class VehBasicInfoAdapter extends MultipleRecyclerAdapter {

    public VehBasicInfoAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(VehItemType.ITEM_VEH_BASIC, R.layout.item_veh_base_info);
    }


    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()){
            case VehItemType.ITEM_VEH_BASIC:
                final AppCompatTextView tv_arrow_text = holder.getView(R.id.tv_arrow_text);
                final AppCompatTextView tv_arrow_value = holder.getView(R.id.tv_arrow_value);
                final String itemName = entity.getField(VehBasicItemFields.ITEM_LEFT_TEXT);
                final String itemValue = entity.getField(VehBasicItemFields.ITEM_RIGHT_VALUE);

                tv_arrow_text.setText(itemName);
                tv_arrow_value.setText(itemValue);
                break;
        }
    }
}
