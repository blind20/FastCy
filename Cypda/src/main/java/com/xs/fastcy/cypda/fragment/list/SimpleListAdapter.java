package com.xs.fastcy.cypda.fragment.list;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.fragment.VehItemType;

import java.util.List;

/**
 * Created by 傅令杰
 */

public class SimpleListAdapter extends BaseMultiItemQuickAdapter<SimpleListBean, BaseViewHolder> {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop();

    public SimpleListAdapter(List<SimpleListBean> data) {
        super(data);
        addItemType(VehItemType.ITEM_SIMPLE_LIST_2, R.layout.item_simple_list_2);
    }

    @Override
    protected void convert(BaseViewHolder helper, SimpleListBean item) {
        switch (helper.getItemViewType()) {
            case VehItemType.ITEM_SIMPLE_LIST_2:
                helper.setText(R.id.tv_item_name, item.getItemName());
                helper.setText(R.id.tv_item_value, item.getItemValue());
                break;
            default:
                break;
        }
    }
}
