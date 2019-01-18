package com.xs.fastcy.cypda.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;
import java.util.Date;

@Entity(nameInDb = "tm_vehcheck")
public class VehCheckInfo implements Serializable {

    private static final long serialVersionUID = -9194378328248343997L;

    @Id(autoincrement = true)
    private Long vehId;

    private String lsh;
    private String hphm;
    private String hpzl;
    private String syxz;
    private String xczl;
    private String clsbdh;
    private String ywlx;
    private String jczbh;
    private String cllx;
    private String csys;
    private String hdzk;
    private String cyjg;
    private String veh_sfxc;
    private String bz;
    private String bh; //公告编号
    private String cyr;
    private int cycs;
    private String sfxny;
    private String xnyzl;

    private String jy1;
    private String jy2;
    private String jy3;
    private String jy4;
    private String jy5;
    private String jy6;
    private String jy7;
    private String jy8;
    private String jy9;
    private String jy10;
    private String jy11;
    private String jy12;
    private String jy13;
    private String jy14;
    private String jy15;
    private String jy16;
    private String jy17;
    private String jy18;
    private String jy19;
    private String jy20;
    private String jy21;
    private String jy22;
    private String jy23;
    private String jy24;
    private String jy25;

    private String qpzk;
    private String hpzk;
    private Date createtime;
    private Date updatetime;
    @Generated(hash = 2074538091)
    public VehCheckInfo(Long vehId, String lsh, String hphm, String hpzl,
            String syxz, String xczl, String clsbdh, String ywlx, String jczbh,
            String cllx, String csys, String hdzk, String cyjg, String veh_sfxc,
            String bz, String bh, String cyr, int cycs, String sfxny, String xnyzl,
            String jy1, String jy2, String jy3, String jy4, String jy5, String jy6,
            String jy7, String jy8, String jy9, String jy10, String jy11,
            String jy12, String jy13, String jy14, String jy15, String jy16,
            String jy17, String jy18, String jy19, String jy20, String jy21,
            String jy22, String jy23, String jy24, String jy25, String qpzk,
            String hpzk, Date createtime, Date updatetime) {
        this.vehId = vehId;
        this.lsh = lsh;
        this.hphm = hphm;
        this.hpzl = hpzl;
        this.syxz = syxz;
        this.xczl = xczl;
        this.clsbdh = clsbdh;
        this.ywlx = ywlx;
        this.jczbh = jczbh;
        this.cllx = cllx;
        this.csys = csys;
        this.hdzk = hdzk;
        this.cyjg = cyjg;
        this.veh_sfxc = veh_sfxc;
        this.bz = bz;
        this.bh = bh;
        this.cyr = cyr;
        this.cycs = cycs;
        this.sfxny = sfxny;
        this.xnyzl = xnyzl;
        this.jy1 = jy1;
        this.jy2 = jy2;
        this.jy3 = jy3;
        this.jy4 = jy4;
        this.jy5 = jy5;
        this.jy6 = jy6;
        this.jy7 = jy7;
        this.jy8 = jy8;
        this.jy9 = jy9;
        this.jy10 = jy10;
        this.jy11 = jy11;
        this.jy12 = jy12;
        this.jy13 = jy13;
        this.jy14 = jy14;
        this.jy15 = jy15;
        this.jy16 = jy16;
        this.jy17 = jy17;
        this.jy18 = jy18;
        this.jy19 = jy19;
        this.jy20 = jy20;
        this.jy21 = jy21;
        this.jy22 = jy22;
        this.jy23 = jy23;
        this.jy24 = jy24;
        this.jy25 = jy25;
        this.qpzk = qpzk;
        this.hpzk = hpzk;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }
    @Generated(hash = 1874313736)
    public VehCheckInfo() {
    }
    public Long getVehId() {
        return this.vehId;
    }
    public void setVehId(Long vehId) {
        this.vehId = vehId;
    }
    public String getLsh() {
        return this.lsh;
    }
    public void setLsh(String lsh) {
        this.lsh = lsh;
    }
    public String getHphm() {
        return this.hphm;
    }
    public void setHphm(String hphm) {
        this.hphm = hphm;
    }
    public String getHpzl() {
        return this.hpzl;
    }
    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }
    public String getSyxz() {
        return this.syxz;
    }
    public void setSyxz(String syxz) {
        this.syxz = syxz;
    }
    public String getXczl() {
        return this.xczl;
    }
    public void setXczl(String xczl) {
        this.xczl = xczl;
    }
    public String getClsbdh() {
        return this.clsbdh;
    }
    public void setClsbdh(String clsbdh) {
        this.clsbdh = clsbdh;
    }
    public String getYwlx() {
        return this.ywlx;
    }
    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }
    public String getJczbh() {
        return this.jczbh;
    }
    public void setJczbh(String jczbh) {
        this.jczbh = jczbh;
    }
    public String getCllx() {
        return this.cllx;
    }
    public void setCllx(String cllx) {
        this.cllx = cllx;
    }
    public String getCsys() {
        return this.csys;
    }
    public void setCsys(String csys) {
        this.csys = csys;
    }
    public String getHdzk() {
        return this.hdzk;
    }
    public void setHdzk(String hdzk) {
        this.hdzk = hdzk;
    }
    public String getCyjg() {
        return this.cyjg;
    }
    public void setCyjg(String cyjg) {
        this.cyjg = cyjg;
    }
    public String getVeh_sfxc() {
        return this.veh_sfxc;
    }
    public void setVeh_sfxc(String veh_sfxc) {
        this.veh_sfxc = veh_sfxc;
    }
    public String getBz() {
        return this.bz;
    }
    public void setBz(String bz) {
        this.bz = bz;
    }
    public String getBh() {
        return this.bh;
    }
    public void setBh(String bh) {
        this.bh = bh;
    }
    public String getCyr() {
        return this.cyr;
    }
    public void setCyr(String cyr) {
        this.cyr = cyr;
    }
    public int getCycs() {
        return this.cycs;
    }
    public void setCycs(int cycs) {
        this.cycs = cycs;
    }
    public String getSfxny() {
        return this.sfxny;
    }
    public void setSfxny(String sfxny) {
        this.sfxny = sfxny;
    }
    public String getXnyzl() {
        return this.xnyzl;
    }
    public void setXnyzl(String xnyzl) {
        this.xnyzl = xnyzl;
    }
    public String getJy1() {
        return this.jy1;
    }
    public void setJy1(String jy1) {
        this.jy1 = jy1;
    }
    public String getJy2() {
        return this.jy2;
    }
    public void setJy2(String jy2) {
        this.jy2 = jy2;
    }
    public String getJy3() {
        return this.jy3;
    }
    public void setJy3(String jy3) {
        this.jy3 = jy3;
    }
    public String getJy4() {
        return this.jy4;
    }
    public void setJy4(String jy4) {
        this.jy4 = jy4;
    }
    public String getJy5() {
        return this.jy5;
    }
    public void setJy5(String jy5) {
        this.jy5 = jy5;
    }
    public String getJy6() {
        return this.jy6;
    }
    public void setJy6(String jy6) {
        this.jy6 = jy6;
    }
    public String getJy7() {
        return this.jy7;
    }
    public void setJy7(String jy7) {
        this.jy7 = jy7;
    }
    public String getJy8() {
        return this.jy8;
    }
    public void setJy8(String jy8) {
        this.jy8 = jy8;
    }
    public String getJy9() {
        return this.jy9;
    }
    public void setJy9(String jy9) {
        this.jy9 = jy9;
    }
    public String getJy10() {
        return this.jy10;
    }
    public void setJy10(String jy10) {
        this.jy10 = jy10;
    }
    public String getJy11() {
        return this.jy11;
    }
    public void setJy11(String jy11) {
        this.jy11 = jy11;
    }
    public String getJy12() {
        return this.jy12;
    }
    public void setJy12(String jy12) {
        this.jy12 = jy12;
    }
    public String getJy13() {
        return this.jy13;
    }
    public void setJy13(String jy13) {
        this.jy13 = jy13;
    }
    public String getJy14() {
        return this.jy14;
    }
    public void setJy14(String jy14) {
        this.jy14 = jy14;
    }
    public String getJy15() {
        return this.jy15;
    }
    public void setJy15(String jy15) {
        this.jy15 = jy15;
    }
    public String getJy16() {
        return this.jy16;
    }
    public void setJy16(String jy16) {
        this.jy16 = jy16;
    }
    public String getJy17() {
        return this.jy17;
    }
    public void setJy17(String jy17) {
        this.jy17 = jy17;
    }
    public String getJy18() {
        return this.jy18;
    }
    public void setJy18(String jy18) {
        this.jy18 = jy18;
    }
    public String getJy19() {
        return this.jy19;
    }
    public void setJy19(String jy19) {
        this.jy19 = jy19;
    }
    public String getJy20() {
        return this.jy20;
    }
    public void setJy20(String jy20) {
        this.jy20 = jy20;
    }
    public String getJy21() {
        return this.jy21;
    }
    public void setJy21(String jy21) {
        this.jy21 = jy21;
    }
    public String getJy22() {
        return this.jy22;
    }
    public void setJy22(String jy22) {
        this.jy22 = jy22;
    }
    public String getJy23() {
        return this.jy23;
    }
    public void setJy23(String jy23) {
        this.jy23 = jy23;
    }
    public String getJy24() {
        return this.jy24;
    }
    public void setJy24(String jy24) {
        this.jy24 = jy24;
    }
    public String getJy25() {
        return this.jy25;
    }
    public void setJy25(String jy25) {
        this.jy25 = jy25;
    }
    public String getQpzk() {
        return this.qpzk;
    }
    public void setQpzk(String qpzk) {
        this.qpzk = qpzk;
    }
    public String getHpzk() {
        return this.hpzk;
    }
    public void setHpzk(String hpzk) {
        this.hpzk = hpzk;
    }
    public Date getCreatetime() {
        return this.createtime;
    }
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    public Date getUpdatetime() {
        return this.updatetime;
    }
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }


    @Override
    public String toString() {
        return "VehCheckInfo{" +
                "vehId=" + vehId +
                ", lsh='" + lsh + '\'' +
                ", hphm='" + hphm + '\'' +
                ", hpzl='" + hpzl + '\'' +
                ", syxz='" + syxz + '\'' +
                ", xczl='" + xczl + '\'' +
                ", clsbdh='" + clsbdh + '\'' +
                ", ywlx='" + ywlx + '\'' +
                ", jczbh='" + jczbh + '\'' +
                ", cllx='" + cllx + '\'' +
                ", csys='" + csys + '\'' +
                ", hdzk='" + hdzk + '\'' +
                ", cyjg='" + cyjg + '\'' +
                ", veh_sfxc='" + veh_sfxc + '\'' +
                ", bz='" + bz + '\'' +
                ", bh='" + bh + '\'' +
                ", cyr='" + cyr + '\'' +
                ", cycs=" + cycs +
                ", sfxny='" + sfxny + '\'' +
                ", xnyzl='" + xnyzl + '\'' +
                ", jy1='" + jy1 + '\'' +
                ", jy2='" + jy2 + '\'' +
                ", jy3='" + jy3 + '\'' +
                ", jy4='" + jy4 + '\'' +
                ", jy5='" + jy5 + '\'' +
                ", jy6='" + jy6 + '\'' +
                ", jy7='" + jy7 + '\'' +
                ", jy8='" + jy8 + '\'' +
                ", jy9='" + jy9 + '\'' +
                ", jy10='" + jy10 + '\'' +
                ", jy11='" + jy11 + '\'' +
                ", jy12='" + jy12 + '\'' +
                ", jy13='" + jy13 + '\'' +
                ", jy14='" + jy14 + '\'' +
                ", jy15='" + jy15 + '\'' +
                ", jy16='" + jy16 + '\'' +
                ", jy17='" + jy17 + '\'' +
                ", jy18='" + jy18 + '\'' +
                ", jy19='" + jy19 + '\'' +
                ", jy20='" + jy20 + '\'' +
                ", jy21='" + jy21 + '\'' +
                ", jy22='" + jy22 + '\'' +
                ", jy23='" + jy23 + '\'' +
                ", jy24='" + jy24 + '\'' +
                ", jy25='" + jy25 + '\'' +
                ", qpzk='" + qpzk + '\'' +
                ", hpzk='" + hpzk + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                '}';
    }
}
