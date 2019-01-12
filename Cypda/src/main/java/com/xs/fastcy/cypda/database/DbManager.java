package com.xs.fastcy.cypda.database;

import android.content.Context;


import com.xs.fastcy.cypda.gen.DaoMaster;
import com.xs.fastcy.cypda.gen.DaoSession;
import com.xs.fastcy.cypda.gen.UserDao;
import com.xs.fastcy.cypda.gen.VehCheckInfoDao;
import com.xs.fastcy.cypda.gen.VehPhotoDao;

import org.greenrobot.greendao.database.Database;

public class DbManager {

    private DaoSession mDaoSession=null;
    private VehCheckInfoDao mVehCheckInfoDao=null;
    private UserDao mUserDao=null;
    private VehPhotoDao mVehPhotoDao=null;

    public DbManager() {}

    public DbManager init(Context context) {
        initDao(context);
        return this;
    }

    private static final class Holder{
        private static final DbManager INSTANCE = new DbManager();
    }

    public static DbManager getInstance(){
        return Holder.INSTANCE;
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context,"fast_cy.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mVehCheckInfoDao = mDaoSession.getVehCheckInfoDao();
        mUserDao = mDaoSession.getUserDao();
        mVehPhotoDao = mDaoSession.getVehPhotoDao();
    }

    public final VehCheckInfoDao getVehCheckInfoDao(){
        return mVehCheckInfoDao;
    }

    public final UserDao getUserDao(){
        return mUserDao;
    }

    public final VehPhotoDao getVehPhotoDao(){
        return mVehPhotoDao;
    }
}
