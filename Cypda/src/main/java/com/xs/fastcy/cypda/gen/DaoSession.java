package com.xs.fastcy.cypda.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.xs.fastcy.cypda.entity.User;
import com.xs.fastcy.cypda.entity.VehCheckInfo;
import com.xs.fastcy.cypda.entity.VehPhoto;

import com.xs.fastcy.cypda.gen.UserDao;
import com.xs.fastcy.cypda.gen.VehCheckInfoDao;
import com.xs.fastcy.cypda.gen.VehPhotoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;
    private final DaoConfig vehCheckInfoDaoConfig;
    private final DaoConfig vehPhotoDaoConfig;

    private final UserDao userDao;
    private final VehCheckInfoDao vehCheckInfoDao;
    private final VehPhotoDao vehPhotoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        vehCheckInfoDaoConfig = daoConfigMap.get(VehCheckInfoDao.class).clone();
        vehCheckInfoDaoConfig.initIdentityScope(type);

        vehPhotoDaoConfig = daoConfigMap.get(VehPhotoDao.class).clone();
        vehPhotoDaoConfig.initIdentityScope(type);

        userDao = new UserDao(userDaoConfig, this);
        vehCheckInfoDao = new VehCheckInfoDao(vehCheckInfoDaoConfig, this);
        vehPhotoDao = new VehPhotoDao(vehPhotoDaoConfig, this);

        registerDao(User.class, userDao);
        registerDao(VehCheckInfo.class, vehCheckInfoDao);
        registerDao(VehPhoto.class, vehPhotoDao);
    }
    
    public void clear() {
        userDaoConfig.clearIdentityScope();
        vehCheckInfoDaoConfig.clearIdentityScope();
        vehPhotoDaoConfig.clearIdentityScope();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public VehCheckInfoDao getVehCheckInfoDao() {
        return vehCheckInfoDao;
    }

    public VehPhotoDao getVehPhotoDao() {
        return vehPhotoDao;
    }

}
