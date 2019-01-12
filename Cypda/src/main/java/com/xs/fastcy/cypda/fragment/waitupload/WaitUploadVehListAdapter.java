package com.xs.fastcy.cypda.fragment.waitupload;

import android.support.v7.widget.AppCompatTextView;

import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.flj.latte.ui.recycler.MultipleRecyclerAdapter;
import com.flj.latte.ui.recycler.MultipleViewHolder;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.fragment.VehItemType;

import java.util.List;

public class WaitUploadVehListAdapter extends MultipleRecyclerAdapter {

    WaitUploadVehListAdapter(List<MultipleItemEntity> data){
        super(data);
        addItemType(VehItemType.ITEM_WAIT_UPLOAD_VEHS,R.layout.item_wait_upload_veh);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()){
            case VehItemType.ITEM_WAIT_UPLOAD_VEHS:
                final AppCompatTextView tv_clsbdh = holder.getView(R.id.tv_clsbdh);
                final AppCompatTextView tv_hphm = holder.getView(R.id.tv_hphm);
                final AppCompatTextView tv_lsh = holder.getView(R.id.tv_lsh);
                final AppCompatTextView tv_time = holder.getView(R.id.tv_time);

                String clsbdh = entity.getField(WaitUploadVehItemFields.ITEM_WAIT_UPLOAD_VEH_CLSBDH);
                String hphm = entity.getField(WaitUploadVehItemFields.ITEM_WAIT_UPLOAD_VEH_HPHM);
                String lsh = entity.getField(WaitUploadVehItemFields.ITEM_WAIT_UPLOAD_VEH_LSH);
                String time = entity.getField(WaitUploadVehItemFields.ITEM_WAIT_UPLOAD_VEH_TIME);
                tv_clsbdh.setText(clsbdh);
                tv_hphm.setText(hphm);
                tv_lsh.setText(lsh);
                tv_time.setText(time);
                break;
            default:
                break;
        }
    }
}
