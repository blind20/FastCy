package com.xs.fastcy.cypda.database;

import android.content.Context;

import com.xs.fastcy.cypda.entity.User;
import com.xs.fastcy.cypda.entity.VehCheckInfo;
import com.xs.fastcy.cypda.entity.VehPhoto;
import com.xs.fastcy.cypda.gen.VehCheckInfoDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Date;
import java.util.List;


public class FastCyDbUtil {
    private DbManager mDbManager;

    public FastCyDbUtil(Context context) {
        this.mDbManager = DbManager.getInstance();
        mDbManager.init(context);
    }

    public void insertUser(User loginUser){
        List<User> users = updateUsersNotLogin();
        if(users ==null){
            mDbManager.getUserDao().insertOrReplace(loginUser);
        }else {
            for (User user:users) {
                if(user.getUsername().equals(loginUser.getUsername())){
                    mDbManager.getUserDao().delete(user);
                }
            }
            mDbManager.getUserDao().insertOrReplace(loginUser);
        }

    }

    public User queryLoginUser(){
        List<User> users = mDbManager.getUserDao().loadAll();
        for (User user:users){
            if(user.getIsLoginTag()){
                return user;
            }
        }
        return null;
    }

    public List<User> updateUsersNotLogin(){
        List<User> users = mDbManager.getUserDao().loadAll();
        if (users==null||users.size()==0){
            return null;
        }else {
            for (User user : users){
                user.setIsLoginTag(false);
            }
            mDbManager.getUserDao().updateInTx(users);
            return users;
        }
    }


    public void insertVehCheckInfo(VehCheckInfo vehCheckInfo){
        deleteVehCheckInfoByLsh(vehCheckInfo.getLsh());
        mDbManager.getVehCheckInfoDao().insertOrReplace(vehCheckInfo);
    }

    public void updateVehCheckInfo(VehCheckInfo vehCheckInfo){
        mDbManager.getVehCheckInfoDao().update(vehCheckInfo);
    }


    public List<VehCheckInfo> queryVehCheckInfoByLsh(String lsh){
        List<VehCheckInfo> vehCheckInfos = mDbManager.getVehCheckInfoDao().queryRaw("where lsh = ?",new String[]{lsh});
        return vehCheckInfos;
    }

    public void deleteVehCheckInfoByLsh(String lsh){
        List<VehCheckInfo> vehs = queryVehCheckInfoByLsh(lsh);
        if (vehs != null && vehs.size()>0) {
            for (VehCheckInfo veh : vehs) {
                mDbManager.getVehCheckInfoDao().delete(veh);
            }
        }
    }

    public void deleteVehCheckInfo(VehCheckInfo vehCheckInfo){
        deleteVehCheckInfoByLsh(vehCheckInfo.getLsh());
    }

    public List<VehCheckInfo> queryAllVehCheckInfo(){
        delete14DayBeforeVehCheckInfoAndPhoto();
        QueryBuilder<VehCheckInfo> qb = mDbManager.getVehCheckInfoDao().queryBuilder()
                .orderDesc(VehCheckInfoDao.Properties.Createtime);
        return qb.list();
    }

    public List<VehCheckInfo> query14DayBeforeVehCheckInfo(){
        Date now = new Date();
        Date before = new Date(now.getTime()-14*24*60*60);
        QueryBuilder<VehCheckInfo> qb = mDbManager.getVehCheckInfoDao().queryBuilder();
        qb.whereOr(VehCheckInfoDao.Properties.Createtime.lt(before),
                VehCheckInfoDao.Properties.Createtime.isNull());
        return qb.list();
    }

    public void delete14DayBeforeVehCheckInfoAndPhoto(){
        List<VehCheckInfo> vehs = query14DayBeforeVehCheckInfo();
        if(vehs!=null && vehs.size()>0){
            mDbManager.getVehCheckInfoDao().deleteInTx(vehs);
            for (VehCheckInfo veh:vehs){
                deleteVehPhotoByLsh(veh.getLsh());
            }
        }

    }


    public void insertVehPhoto(VehPhoto vehPhotoInfo){
        List<VehPhoto> photos = queryVehPhotoByLshAndZpzl(vehPhotoInfo.getLsh(),vehPhotoInfo.getZpzl());
        if(photos!=null && photos.size()> 0){
            for (VehPhoto vehPhoto : photos){
                mDbManager.getVehPhotoDao().delete(vehPhoto);
            }
        }
        mDbManager.getVehPhotoDao().insertOrReplace(vehPhotoInfo);
    }

    public List<VehPhoto> queryVehPhotoByLshAndZpzl(String lsh,String zpzl){
        List<VehPhoto> photos = mDbManager.getVehPhotoDao().queryRaw(
                "where lsh = ? and zpzl =?",
                new String[]{lsh,zpzl});
        return photos;
    }

    public List<VehPhoto> queryVehPhotoByLsh(String lsh){
        List<VehPhoto> photos = mDbManager.getVehPhotoDao().queryRaw(
                "where lsh = ? ",
                new String[]{lsh});
        return photos;
    }

    public void deleteVehPhotoByLsh(String lsh){
        List<VehPhoto> photos = mDbManager.getVehPhotoDao().queryRaw(
                "where lsh = ? ",
                new String[]{lsh});
        if(photos!=null && photos.size()> 0){
            for (VehPhoto vehPhoto : photos){
                mDbManager.getVehPhotoDao().delete(vehPhoto);
            }
        }
    }





}
