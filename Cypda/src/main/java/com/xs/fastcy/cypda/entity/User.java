package com.xs.fastcy.cypda.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "tm_user")
public class User {

    @Id(autoincrement = true)
    private Long userId;
    private String username;
    private String password;
    private String sfzmhm;
    private String yhxm;
    private String glbm;
    private String bmmc;
    private boolean isLoginTag;
    @Generated(hash = 764565386)
    public User(Long userId, String username, String password, String sfzmhm,
            String yhxm, String glbm, String bmmc, boolean isLoginTag) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.sfzmhm = sfzmhm;
        this.yhxm = yhxm;
        this.glbm = glbm;
        this.bmmc = bmmc;
        this.isLoginTag = isLoginTag;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getSfzmhm() {
        return this.sfzmhm;
    }
    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
    }
    public String getYhxm() {
        return this.yhxm;
    }
    public void setYhxm(String yhxm) {
        this.yhxm = yhxm;
    }
    public String getGlbm() {
        return this.glbm;
    }
    public void setGlbm(String glbm) {
        this.glbm = glbm;
    }
    public String getBmmc() {
        return this.bmmc;
    }
    public void setBmmc(String bmmc) {
        this.bmmc = bmmc;
    }
    public boolean getIsLoginTag() {
        return this.isLoginTag;
    }
    public void setIsLoginTag(boolean isLoginTag) {
        this.isLoginTag = isLoginTag;
    }

    

    
}
