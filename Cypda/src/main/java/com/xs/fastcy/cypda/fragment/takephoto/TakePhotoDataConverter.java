package com.xs.fastcy.cypda.fragment.takephoto;

import android.net.Uri;

import com.flj.latte.ui.recycler.DataConverter;
import com.flj.latte.ui.recycler.MultipleFields;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.entity.VehPhoto;

import java.util.ArrayList;
import java.util.List;

public class TakePhotoDataConverter extends DataConverter {
    private List<VehPhoto> mVehPhotos;

    public TakePhotoDataConverter(List<VehPhoto> vehPhotos) {
        this.mVehPhotos = vehPhotos;
    }

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final  String[] zpzl = getContext().getResources().getStringArray(R.array.zpzl);
        final  String[] zpzlCode = getContext().getResources().getStringArray(R.array.zpzlcode);
        for (int i = 0; i <zpzl.length ; i++) {
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(TakePhotoItemType.ITEM_TAKE_PHOTO)
                    .setField(MultipleFields.SPAN_SIZE,1)
                    .setField(MultipleFields.ID,i+1)
                    .setField(TakePhotoItemFields.PHOTO_ZPZL_NAME,zpzl[i])
                    .setField(TakePhotoItemFields.PHOTO_ZPZL_CODE,zpzlCode[i])
                    .setField(TakePhotoItemFields.PHOTO_FILE_URI,null)
                    .build();
            if(mVehPhotos!=null&&mVehPhotos.size()>0){
                for (VehPhoto vehPhoto:mVehPhotos) {
                    if(vehPhoto.getZpzl().equals(zpzlCode[i])){
                        entity.setField(TakePhotoItemFields.PHOTO_FILE_URI,Uri.parse(vehPhoto.getImgPath()));
                    }
                }
            }
            dataList.add(entity);
        }
        return dataList;
    }
}
