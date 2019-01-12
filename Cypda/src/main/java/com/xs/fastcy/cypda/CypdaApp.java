package com.xs.fastcy.cypda;

import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;
import com.flj.latte.app.Latte;
import com.flj.latte.net.interceptors.LoggingInterceptor;
import com.xs.fastcy.cypda.database.DbManager;
import com.xs.fastcy.cypda.icon.FontCyModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.xs.fastcy.cypda.net.NewProcessValidSessionInterceptor;

public class CypdaApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontCyModule())
                .withLoaderDelayed(1000)
                .withApiHost(CyConstant.SETTING_PDA_BASEURL)
                .withInterceptor(new LoggingInterceptor())
                .withInterceptor(new NewProcessValidSessionInterceptor())
                .configure();
        initStetho();
        DbManager.getInstance().init(this);
    }

        private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
