package com.xs.fastcy.cypda.fragment.ggxx.ggimage;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.flj.latte.ui.recycler.MultipleFields;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.flj.latte.ui.recycler.MultipleRecyclerAdapter;
import com.flj.latte.ui.recycler.MultipleViewHolder;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.fragment.VehItemType;
import com.xs.fastcy.cypda.fragment.takephoto.TakePhotoItemType;

import java.util.List;

public class GgImageGridAdapter extends MultipleRecyclerAdapter {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();


    public GgImageGridAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(VehItemType.ITEM_GGXX_IMAGE, R.layout.item_ggimage);
    }


    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        final int id = entity.getField(MultipleFields.ID);
        final String thumb = entity.getField(MultipleFields.IMAGE_URL);
        final AppCompatImageView imgThumb = holder.getView(R.id.iv_ggimage);
        Glide.with(mContext).load(thumb).apply(OPTIONS).into(imgThumb);
    }
}
