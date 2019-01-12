package com.xs.fastcy.cypda.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by 傅令杰 on 2017/3/29
 */

public enum CyIcons implements Icon {
    icon_scan('\ue630'),
    icon_add_photo('\ue600');

    private char character;

    CyIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
