package com.xs.fastcy.cypda.fragment.waitupload;

import com.flj.latte.ui.recycler.DataConverter;
import com.flj.latte.ui.recycler.MultipleFields;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.xs.fastcy.cypda.entity.VehCheckInfo;
import com.xs.fastcy.cypda.fragment.VehItemType;

import java.util.ArrayList;
import java.util.List;

public class WaitUploadVehDataConvert extends DataConverter {

    private List<VehCheckInfo> vehCheckInfos;

    public WaitUploadVehDataConvert setVehCheckInfos(List<VehCheckInfo> vehs){
        this.vehCheckInfos = vehs;
        return this;
    }

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        for (int i = 0; i <vehCheckInfos.size() ; i++) {
            VehCheckInfo vehCheckInfo = vehCheckInfos.get(i);
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, VehItemType.ITEM_WAIT_UPLOAD_VEHS)
                    .setField(MultipleFields.ID, i)
                    .setField(WaitUploadVehItemFields.ITEM_WAIT_UPLOAD_VEH_CLSBDH,vehCheckInfo.getClsbdh())
                    .setField(WaitUploadVehItemFields.ITEM_WAIT_UPLOAD_VEH_HPHM,vehCheckInfo.getHphm())
                    .setField(WaitUploadVehItemFields.ITEM_WAIT_UPLOAD_VEH_LSH,vehCheckInfo.getLsh())
                    .setField(WaitUploadVehItemFields.ITEM_WAIT_UPLOAD_VEH_TIME,"2018-12-12")
                    .build();
            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
