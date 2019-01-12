package com.xs.fastcy.cypda.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Date;

@Entity(nameInDb = "tm_vehphoto")

public class VehPhoto {
    @Id(autoincrement = true)
    private Long photoId ;
    private String imgPath;
    private String zpzl;
    private String zpName;
    private String lsh;
    private String clsbdh;
    private String hphm;
    private int jccs;
    private Date createtime;
    @Generated(hash = 1048115500)
    public VehPhoto(Long photoId, String imgPath, String zpzl, String zpName,
            String lsh, String clsbdh, String hphm, int jccs, Date createtime) {
        this.photoId = photoId;
        this.imgPath = imgPath;
        this.zpzl = zpzl;
        this.zpName = zpName;
        this.lsh = lsh;
        this.clsbdh = clsbdh;
        this.hphm = hphm;
        this.jccs = jccs;
        this.createtime = createtime;
    }
    @Generated(hash = 277610246)
    public VehPhoto() {
    }
    public Long getPhotoId() {
        return this.photoId;
    }
    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }
    public String getImgPath() {
        return this.imgPath;
    }
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    public String getZpzl() {
        return this.zpzl;
    }
    public void setZpzl(String zpzl) {
        this.zpzl = zpzl;
    }
    public String getZpName() {
        return this.zpName;
    }
    public void setZpName(String zpName) {
        this.zpName = zpName;
    }
    public String getLsh() {
        return this.lsh;
    }
    public void setLsh(String lsh) {
        this.lsh = lsh;
    }
    public String getClsbdh() {
        return this.clsbdh;
    }
    public void setClsbdh(String clsbdh) {
        this.clsbdh = clsbdh;
    }
    public String getHphm() {
        return this.hphm;
    }
    public void setHphm(String hphm) {
        this.hphm = hphm;
    }
    public int getJccs() {
        return this.jccs;
    }
    public void setJccs(int jccs) {
        this.jccs = jccs;
    }
    public Date getCreatetime() {
        return this.createtime;
    }
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }


   
    



}
