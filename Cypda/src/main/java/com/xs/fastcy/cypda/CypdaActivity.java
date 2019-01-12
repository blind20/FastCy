package com.xs.fastcy.cypda;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.WindowManager;

import com.flj.latte.activities.ProxyActivity;
import com.flj.latte.app.Latte;
import com.flj.latte.delegates.LatteDelegate;
import com.xs.fastcy.cypda.fragment.LoginFrm;
import com.xs.fastcy.cypda.fragment.SignatureFrm;
import com.xs.fastcy.cypda.fragment.ggxx.ggimage.GgImageFrm;
import com.xs.fastcy.cypda.fragment.takephoto.TakePhotoFrm;

import qiu.niorgai.StatusBarCompat;

public class CypdaActivity extends ProxyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LoginFrm();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void post(Runnable runnable) {}
}
