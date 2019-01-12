package com.xs.fastcy.cypda.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by 傅令杰 on 2017/3/29
 */

public class FontCyModule implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "iconfont2.ttf";
    }

    @Override
    public Icon[] characters() {
        return CyIcons.values();
    }
}
