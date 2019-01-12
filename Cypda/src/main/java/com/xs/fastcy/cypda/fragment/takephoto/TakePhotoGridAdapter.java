package com.xs.fastcy.cypda.fragment.takephoto;

import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.flj.latte.ui.recycler.MultipleFields;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.flj.latte.ui.recycler.MultipleRecyclerAdapter;
import com.flj.latte.ui.recycler.MultipleViewHolder;
import com.flj.latte.util.log.LatteLogger;
import com.xs.fastcy.cypda.R;

import java.io.File;
import java.util.List;

public class TakePhotoGridAdapter extends MultipleRecyclerAdapter {

    public TakePhotoGridAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(TakePhotoItemType.ITEM_TAKE_PHOTO, R.layout.item_take_photo);
    }


    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()){
            case TakePhotoItemType.ITEM_TAKE_PHOTO:
                final AppCompatImageView imageView = holder.getView(R.id.iv_add_photo);
                final AppCompatTextView tv_zp_name = holder.getView(R.id.tv_zp_name);
                final String zpzlName =  entity.getField(TakePhotoItemFields.PHOTO_ZPZL_NAME);
                final Uri imgUri = entity.getField(TakePhotoItemFields.PHOTO_FILE_URI);
                if(imgUri!=null){
                    LatteLogger.i("convert","uri="+imgUri+";ID="+entity.getField(MultipleFields.ID));
                }
                tv_zp_name.setText(zpzlName);
                if(imgUri!=null){
                    Glide.with(mContext).load(imgUri).into(imageView);
                }else {
                    Glide.with(mContext).load(R.mipmap.ic_photo_add).into(imageView);
//                    imageView.setBackgroundResource(R.drawable.item_addphoto_bg);

                }
                break;
            default:
                break;
        }
    }
}
