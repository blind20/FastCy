package com.xs.fastcy.cypda.ui;

import android.view.View;

public abstract  class OnMultiClickListener implements View.OnClickListener {
    private static final int MIN_CLICK_DELAY_TIME = 5000;
    private static long lastClickTime;

    public abstract void onMultiClick(View view);

    @Override
    public void onClick(View view) {
        long curClickTime = System.currentTimeMillis();
        if((curClickTime - lastClickTime )>MIN_CLICK_DELAY_TIME){
            lastClickTime = curClickTime;
            onMultiClick(view);
        }

    }
}
