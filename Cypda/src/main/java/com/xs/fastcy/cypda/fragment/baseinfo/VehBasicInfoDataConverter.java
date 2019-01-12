package com.xs.fastcy.cypda.fragment.baseinfo;


import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.flj.latte.ui.recycler.DataConverter;
import com.flj.latte.ui.recycler.MultipleFields;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.flj.latte.util.log.LatteLogger;
import com.xs.fastcy.cypda.CyConstant;
import com.xs.fastcy.cypda.R;
import com.xs.fastcy.cypda.fragment.VehItemType;
import com.xs.fastcy.cypda.util.ToolUtil;

import java.util.ArrayList;
import java.util.List;

public class VehBasicInfoDataConverter extends DataConverter {

    private boolean mIsSchoolVeh = false;
    private String mHphmFull;

    private static final int ITEM_LEFT_TEXT = 0x100;
    private static final int ITEM_LEFT_FIELD = 0x101;

    public VehBasicInfoDataConverter setIsSchoolVeh(boolean isSchoolVeh) {
        this.mIsSchoolVeh = isSchoolVeh;
        return this;
    }
    public VehBasicInfoDataConverter setHphmFull(String hphm) {
        this.mHphmFull = hphm;
        return this;
    }

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONObject jo;
        if(getJsonData()!=null){
            jo= JSON.parseObject(getJsonData());
        }else {
            jo = null;
        }
        String[] array_item_left_txt = getLeftItems(mIsSchoolVeh, ITEM_LEFT_TEXT);    //显示名:业务类型
        String[] array_item_left_field = getLeftItems(mIsSchoolVeh, ITEM_LEFT_FIELD);  //字段名:ywlx
        String item_right_valuetext;
        String item_right_valuecode;
        for (int i = 0; i < array_item_left_txt.length; i++) {
            if(jo!=null){
                item_right_valuecode = jo.getString(array_item_left_field[i]);
            }else {
                //例如转入业务,调不到基本信息.jo为null
                item_right_valuecode = null;
            }
            //下拉框类型,并且下拉值NULL
            if (ToolUtil.isEmpty(item_right_valuecode)&&isSelectType(array_item_left_txt[i])) {
                item_right_valuecode = CyConstant.PLEASE_SELECT_TYPE;
            }
            if(array_item_left_field[i].equals("csys")){
                item_right_valuecode = convertCsysCode(item_right_valuecode);
            }
            item_right_valuetext = getItemValueNameByItemValueCode(array_item_left_txt[i], item_right_valuecode);
            if(array_item_left_field[i].equals("hphm")&&!ToolUtil.isEmpty(mHphmFull)){
                item_right_valuetext = mHphmFull;
                item_right_valuecode = mHphmFull;
            }
            int id = mIsSchoolVeh ? i : i+1;  //如果是校车则ID从0开始否则从1开始
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, VehItemType.ITEM_VEH_BASIC)
                    .setField(MultipleFields.ID, id)
                    .setField(VehBasicItemFields.ITEM_LEFT_TEXT, array_item_left_txt[i])
                    .setField(VehBasicItemFields.ITEM_LEFT_FIELD, array_item_left_field[i])
                    .setField(VehBasicItemFields.ITEM_RIGHT_VALUE, item_right_valuetext)
                    .setField(VehBasicItemFields.ITEM_RIGHT_VALUE_CODE, item_right_valuecode)
                    .build();
            if (array_item_left_field[i].equals("csys")) {
                entity.setField(VehBasicItemFields.DELEGATE, new VehCsysFrm());
            }
            ENTITIES.add(entity);
        }
        return ENTITIES;
    }

    /**
     *
     * @param isSchool 是否校车
     * @param cnFlag 标记文本？字段？
     * @return
     */
    private String[] getLeftItems(boolean isSchool, int cnFlag) {
        if (isSchool && cnFlag == ITEM_LEFT_TEXT) {
            return ToolUtil.getArrayByResouce(getContext(),R.array.veh_school);
        } else if (isSchool && cnFlag == ITEM_LEFT_FIELD) {
            return ToolUtil.getArrayByResouce(getContext(),R.array.veh_school_en);
        } else if (!isSchool && cnFlag == ITEM_LEFT_FIELD) {
            return ToolUtil.getArrayByResouce(getContext(),R.array.veh_common_en);
        } else {
            return ToolUtil.getArrayByResouce(getContext(),R.array.veh_common);
        }
    }


    private String getItemValueNameByItemValueCode(String itemLeftText, String itemRightValueCode) {
        if (itemLeftText.equals("业务类型")) {
            return ToolUtil.getValueByKeyInArrayRes(getContext(),itemRightValueCode, R.array.ywlxcode, R.array.ywlx);
        } else if (itemLeftText.equals("号牌种类")) {
            return ToolUtil.getValueByKeyInArrayRes(getContext(),itemRightValueCode, R.array.hpzlcode, R.array.hpzl);
        } else if (itemLeftText.equals("使用性质")) {
            return ToolUtil.getValueByKeyInArrayRes(getContext(),itemRightValueCode, R.array.syxzcode, R.array.syxz);
        } else if (itemLeftText.equals("车辆类型")) {
            return ToolUtil.getValueByCodeInSpecialArray(getContext(), itemRightValueCode, R.array.cllx);
        }else if (itemLeftText.equals("校车种类")) {
            return ToolUtil.getValueByKeyInArrayRes(getContext(), itemRightValueCode, R.array.xczlCode, R.array.xczl);
        }else if (itemLeftText.equals("车身颜色")) {
                return getCsysTextByCode(itemRightValueCode);
        } else {
            return itemRightValueCode;
        }
    }

    public boolean isSelectType(String item){
        List<String> list = new ArrayList<>();
        list.add("业务类型");
        list.add("号牌种类");
        list.add("使用性质");
        list.add("车辆类型");
        list.add("校车种类");
        if (list.contains(item)){
            return true;
        }
        return false;
    }

    private String getCsysTextByCode(String csysCode){
        if(ToolUtil.isEmpty(csysCode)){
            return null;
        }
        String[] csysCodeArray = csysCode.split(",");
        String color="";
        for (String csys : csysCodeArray){
            String tempCsys = ToolUtil.getValueByKeyInArrayRes(getContext(),csys,R.array.csyscode,R.array.csys);
            if(ToolUtil.isEmpty(tempCsys)){
                return csys;
            }else {
                color= color + ToolUtil.getValueByKeyInArrayRes(getContext(),csys,R.array.csyscode,R.array.csys);
            }
        }
        return color;
    }

    private String convertCsysCode(String csysCode){
        if(!TextUtils.isEmpty(csysCode)){
            char[] colors = csysCode.toCharArray();
            if(ToolUtil.isContainsStr(csysCode)){
                csysCode = "";
                for (char c:colors) {
                    csysCode = csysCode+","+c;
                }
                csysCode=csysCode.substring(1,csysCode.length());
            }
        }
        return csysCode;
    }


}
