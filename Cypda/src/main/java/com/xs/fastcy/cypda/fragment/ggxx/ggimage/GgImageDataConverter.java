package com.xs.fastcy.cypda.fragment.ggxx.ggimage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.ToastUtils;
import com.flj.latte.ui.recycler.DataConverter;
import com.flj.latte.ui.recycler.MultipleFields;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.flj.latte.util.log.LatteLogger;
import com.xs.fastcy.cypda.CyConstant;
import com.xs.fastcy.cypda.fragment.VehItemType;

import java.util.ArrayList;

public class GgImageDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        JSONArray jsonArray = JSON.parseArray(getJsonData());
        for (int i = 0; i <jsonArray.size() ; i++) {
            final JSONObject data = jsonArray.getJSONObject(i);
            final String thumbUrl = urlConvert(data.getString("url"));
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, VehItemType.ITEM_GGXX_IMAGE)
                    .setField(MultipleFields.SPAN_SIZE,1)
                    .setField(MultipleFields.ID, i)
                    .setField(MultipleFields.IMAGE_URL, thumbUrl)
                    .build();
            ENTITIES.add(entity);
        }
        return ENTITIES;
    }

    private String urlConvert(String path){
        String newPath = path.replace("\\","/");
        String ggImageUrl = CyConstant.SETTING_PDA_BASEURL+"static/cache";
        return ggImageUrl+newPath;
    }

}
