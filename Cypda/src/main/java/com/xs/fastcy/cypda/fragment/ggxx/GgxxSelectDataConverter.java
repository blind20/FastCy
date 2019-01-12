package com.xs.fastcy.cypda.fragment.ggxx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flj.latte.ui.recycler.DataConverter;
import com.flj.latte.ui.recycler.MultipleFields;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.xs.fastcy.cypda.fragment.VehItemType;
import com.xs.fastcy.cypda.util.ToolUtil;

import java.util.ArrayList;

public class GgxxSelectDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray array = JSON.parseArray(getJsonData());
        final int size = array.size();
        for (int i = 0; i < size; i++) {
            final JSONObject jo = array.getJSONObject(i);
            if (jo != null && !jo.isEmpty()) {
                String bh = jo.getString("BH");
                String clxh = jo.getString("CLXH");
                String ggrq = jo.getString("GGRQ");
                if (ToolUtil.isEmpty(bh)|| ToolUtil.isEmpty(clxh)|| ToolUtil.isEmpty(ggrq)) {
                    break;
                }
                final MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setField(MultipleFields.ITEM_TYPE, VehItemType.ITEM_GGXX_SELECT)
                        .setField(MultipleFields.ID, i + 1)
                        .setField(GgxxSelectItemFields.ITEM_GGBH, bh)
                        .setField(GgxxSelectItemFields.ITEM_CLXH, clxh)
                        .setField(GgxxSelectItemFields.ITEM_GGRQ, ggrq)
                        .setField(GgxxSelectItemFields.ITEM_GGXX_JSON,jo)
                        .build();
                ENTITIES.add(entity);
            }
        }
        return ENTITIES;
    }
}
