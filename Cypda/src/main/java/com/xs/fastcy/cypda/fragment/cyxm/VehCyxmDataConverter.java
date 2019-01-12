package com.xs.fastcy.cypda.fragment.cyxm;

import com.flj.latte.ui.recycler.DataConverter;
import com.flj.latte.ui.recycler.MultipleFields;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.fragment.list.VehCyxmItemFields;
import com.xs.fastcy.cypda.fragment.list.VehCyxmItemType;
import com.xs.fastcy.cypda.util.ToolUtil;

import java.util.ArrayList;

public class VehCyxmDataConverter extends DataConverter {
    private String mXczl;

    public VehCyxmDataConverter setmXczl(String mXczl) {
        this.mXczl = mXczl;
        return this;
    }

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        String[] cyxms;
        if (ToolUtil.isEmpty(mXczl)){
            cyxms = getContext().getResources().getStringArray(R.array.veh_cyxm_common);
        }else {
            cyxms = getContext().getResources().getStringArray(R.array.veh_cyxm_school);
        }

        for (int i=0;i<cyxms.length;i++){
            String field = "jy"+String.valueOf(i+1);
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE,VehCyxmItemType.ITEM_VEH_CYXM)
                    .setField(MultipleFields.ID,i+1)
                    .setField(VehCyxmItemFields.ITEM_CYXM_NAME,cyxms[i])
                    .setField(VehCyxmItemFields.ITEM_CYXM_FIELD,field)
                    .setField(VehCyxmItemFields.ITEM_CYXM_RESULT_VALUE,0)
                    .setField(VehCyxmItemFields.ITEM_CYXM_RESULT_NAME,"√")
                    .build();
            if (i>=9){
                entity.setField(VehCyxmItemFields.ITEM_CYXM_RESULT_VALUE,1);
                entity.setField(VehCyxmItemFields.ITEM_CYXM_RESULT_NAME,"-");
            }
            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
