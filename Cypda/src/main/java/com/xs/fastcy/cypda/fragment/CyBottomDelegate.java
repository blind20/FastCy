package com.xs.fastcy.cypda.fragment;

import android.graphics.Color;

import com.flj.latte.delegates.bottom.BaseBottomDelegate;
import com.flj.latte.delegates.bottom.BottomItemDelegate;
import com.flj.latte.delegates.bottom.BottomTabBean;
import com.flj.latte.delegates.bottom.ItemBuilder;
import com.xs.fastcy.cypda.fragment.waitupload.WaitUploadVehListFrm;

import java.util.LinkedHashMap;

public class CyBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "新车"), new NewCarScanFrm());
        items.put(new BottomTabBean("{fa-sort}", "在用车"), new InUseCarFrm());
        items.put(new BottomTabBean("{fa-save}", "未上传"), new WaitUploadVehListFrm());
        items.put(new BottomTabBean("{fa-compass}", "我的"), new PersonalFrm());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }

    @Override
    public void post(Runnable runnable) {}
}
