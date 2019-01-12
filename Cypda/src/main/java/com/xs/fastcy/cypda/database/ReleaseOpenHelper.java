package com.xs.fastcy.cypda.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.xs.fastcy.cypda.gen.DaoMaster;
import com.xs.fastcy.cypda.gen.UserDao;
import com.xs.fastcy.cypda.gen.VehCheckInfoDao;
import com.xs.fastcy.cypda.gen.VehPhotoDao;

import org.greenrobot.greendao.database.Database;

public class ReleaseOpenHelper extends DaoMaster.OpenHelper {
    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    public ReleaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db,new MigrationHelper.ReCreateAllTableListener(){
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        },UserDao.class,VehPhotoDao.class,VehCheckInfoDao.class);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }
}
