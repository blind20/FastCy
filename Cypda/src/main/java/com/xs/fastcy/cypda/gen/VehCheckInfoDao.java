package com.xs.fastcy.cypda.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.xs.fastcy.cypda.entity.VehCheckInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "tm_vehcheck".
*/
public class VehCheckInfoDao extends AbstractDao<VehCheckInfo, Long> {

    public static final String TABLENAME = "tm_vehcheck";

    /**
     * Properties of entity VehCheckInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property VehId = new Property(0, Long.class, "vehId", true, "_id");
        public final static Property Lsh = new Property(1, String.class, "lsh", false, "LSH");
        public final static Property Hphm = new Property(2, String.class, "hphm", false, "HPHM");
        public final static Property Hpzl = new Property(3, String.class, "hpzl", false, "HPZL");
        public final static Property Syxz = new Property(4, String.class, "syxz", false, "SYXZ");
        public final static Property Xczl = new Property(5, String.class, "xczl", false, "XCZL");
        public final static Property Clsbdh = new Property(6, String.class, "clsbdh", false, "CLSBDH");
        public final static Property Ywlx = new Property(7, String.class, "ywlx", false, "YWLX");
        public final static Property Jczbh = new Property(8, String.class, "jczbh", false, "JCZBH");
        public final static Property Cllx = new Property(9, String.class, "cllx", false, "CLLX");
        public final static Property Csys = new Property(10, String.class, "csys", false, "CSYS");
        public final static Property Hdzk = new Property(11, String.class, "hdzk", false, "HDZK");
        public final static Property Cyjg = new Property(12, String.class, "cyjg", false, "CYJG");
        public final static Property Veh_sfxc = new Property(13, String.class, "veh_sfxc", false, "VEH_SFXC");
        public final static Property Bz = new Property(14, String.class, "bz", false, "BZ");
        public final static Property Bh = new Property(15, String.class, "bh", false, "BH");
        public final static Property Cyr = new Property(16, String.class, "cyr", false, "CYR");
        public final static Property Cycs = new Property(17, int.class, "cycs", false, "CYCS");
        public final static Property Sfxny = new Property(18, String.class, "sfxny", false, "SFXNY");
        public final static Property Xnyzl = new Property(19, String.class, "xnyzl", false, "XNYZL");
        public final static Property Jy1 = new Property(20, String.class, "jy1", false, "JY1");
        public final static Property Jy2 = new Property(21, String.class, "jy2", false, "JY2");
        public final static Property Jy3 = new Property(22, String.class, "jy3", false, "JY3");
        public final static Property Jy4 = new Property(23, String.class, "jy4", false, "JY4");
        public final static Property Jy5 = new Property(24, String.class, "jy5", false, "JY5");
        public final static Property Jy6 = new Property(25, String.class, "jy6", false, "JY6");
        public final static Property Jy7 = new Property(26, String.class, "jy7", false, "JY7");
        public final static Property Jy8 = new Property(27, String.class, "jy8", false, "JY8");
        public final static Property Jy9 = new Property(28, String.class, "jy9", false, "JY9");
        public final static Property Jy10 = new Property(29, String.class, "jy10", false, "JY10");
        public final static Property Jy11 = new Property(30, String.class, "jy11", false, "JY11");
        public final static Property Jy12 = new Property(31, String.class, "jy12", false, "JY12");
        public final static Property Jy13 = new Property(32, String.class, "jy13", false, "JY13");
        public final static Property Jy14 = new Property(33, String.class, "jy14", false, "JY14");
        public final static Property Jy15 = new Property(34, String.class, "jy15", false, "JY15");
        public final static Property Jy16 = new Property(35, String.class, "jy16", false, "JY16");
        public final static Property Jy17 = new Property(36, String.class, "jy17", false, "JY17");
        public final static Property Jy18 = new Property(37, String.class, "jy18", false, "JY18");
        public final static Property Jy19 = new Property(38, String.class, "jy19", false, "JY19");
        public final static Property Jy20 = new Property(39, String.class, "jy20", false, "JY20");
        public final static Property Jy21 = new Property(40, String.class, "jy21", false, "JY21");
        public final static Property Jy22 = new Property(41, String.class, "jy22", false, "JY22");
        public final static Property Jy23 = new Property(42, String.class, "jy23", false, "JY23");
        public final static Property Jy24 = new Property(43, String.class, "jy24", false, "JY24");
        public final static Property Jy25 = new Property(44, String.class, "jy25", false, "JY25");
        public final static Property Qpzk = new Property(45, String.class, "qpzk", false, "QPZK");
        public final static Property Hpzk = new Property(46, String.class, "hpzk", false, "HPZK");
        public final static Property Createtime = new Property(47, java.util.Date.class, "createtime", false, "CREATETIME");
        public final static Property Updatetime = new Property(48, java.util.Date.class, "updatetime", false, "UPDATETIME");
    }


    public VehCheckInfoDao(DaoConfig config) {
        super(config);
    }
    
    public VehCheckInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"tm_vehcheck\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: vehId
                "\"LSH\" TEXT," + // 1: lsh
                "\"HPHM\" TEXT," + // 2: hphm
                "\"HPZL\" TEXT," + // 3: hpzl
                "\"SYXZ\" TEXT," + // 4: syxz
                "\"XCZL\" TEXT," + // 5: xczl
                "\"CLSBDH\" TEXT," + // 6: clsbdh
                "\"YWLX\" TEXT," + // 7: ywlx
                "\"JCZBH\" TEXT," + // 8: jczbh
                "\"CLLX\" TEXT," + // 9: cllx
                "\"CSYS\" TEXT," + // 10: csys
                "\"HDZK\" TEXT," + // 11: hdzk
                "\"CYJG\" TEXT," + // 12: cyjg
                "\"VEH_SFXC\" TEXT," + // 13: veh_sfxc
                "\"BZ\" TEXT," + // 14: bz
                "\"BH\" TEXT," + // 15: bh
                "\"CYR\" TEXT," + // 16: cyr
                "\"CYCS\" INTEGER NOT NULL ," + // 17: cycs
                "\"SFXNY\" TEXT," + // 18: sfxny
                "\"XNYZL\" TEXT," + // 19: xnyzl
                "\"JY1\" TEXT," + // 20: jy1
                "\"JY2\" TEXT," + // 21: jy2
                "\"JY3\" TEXT," + // 22: jy3
                "\"JY4\" TEXT," + // 23: jy4
                "\"JY5\" TEXT," + // 24: jy5
                "\"JY6\" TEXT," + // 25: jy6
                "\"JY7\" TEXT," + // 26: jy7
                "\"JY8\" TEXT," + // 27: jy8
                "\"JY9\" TEXT," + // 28: jy9
                "\"JY10\" TEXT," + // 29: jy10
                "\"JY11\" TEXT," + // 30: jy11
                "\"JY12\" TEXT," + // 31: jy12
                "\"JY13\" TEXT," + // 32: jy13
                "\"JY14\" TEXT," + // 33: jy14
                "\"JY15\" TEXT," + // 34: jy15
                "\"JY16\" TEXT," + // 35: jy16
                "\"JY17\" TEXT," + // 36: jy17
                "\"JY18\" TEXT," + // 37: jy18
                "\"JY19\" TEXT," + // 38: jy19
                "\"JY20\" TEXT," + // 39: jy20
                "\"JY21\" TEXT," + // 40: jy21
                "\"JY22\" TEXT," + // 41: jy22
                "\"JY23\" TEXT," + // 42: jy23
                "\"JY24\" TEXT," + // 43: jy24
                "\"JY25\" TEXT," + // 44: jy25
                "\"QPZK\" TEXT," + // 45: qpzk
                "\"HPZK\" TEXT," + // 46: hpzk
                "\"CREATETIME\" INTEGER," + // 47: createtime
                "\"UPDATETIME\" INTEGER);"); // 48: updatetime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"tm_vehcheck\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, VehCheckInfo entity) {
        stmt.clearBindings();
 
        Long vehId = entity.getVehId();
        if (vehId != null) {
            stmt.bindLong(1, vehId);
        }
 
        String lsh = entity.getLsh();
        if (lsh != null) {
            stmt.bindString(2, lsh);
        }
 
        String hphm = entity.getHphm();
        if (hphm != null) {
            stmt.bindString(3, hphm);
        }
 
        String hpzl = entity.getHpzl();
        if (hpzl != null) {
            stmt.bindString(4, hpzl);
        }
 
        String syxz = entity.getSyxz();
        if (syxz != null) {
            stmt.bindString(5, syxz);
        }
 
        String xczl = entity.getXczl();
        if (xczl != null) {
            stmt.bindString(6, xczl);
        }
 
        String clsbdh = entity.getClsbdh();
        if (clsbdh != null) {
            stmt.bindString(7, clsbdh);
        }
 
        String ywlx = entity.getYwlx();
        if (ywlx != null) {
            stmt.bindString(8, ywlx);
        }
 
        String jczbh = entity.getJczbh();
        if (jczbh != null) {
            stmt.bindString(9, jczbh);
        }
 
        String cllx = entity.getCllx();
        if (cllx != null) {
            stmt.bindString(10, cllx);
        }
 
        String csys = entity.getCsys();
        if (csys != null) {
            stmt.bindString(11, csys);
        }
 
        String hdzk = entity.getHdzk();
        if (hdzk != null) {
            stmt.bindString(12, hdzk);
        }
 
        String cyjg = entity.getCyjg();
        if (cyjg != null) {
            stmt.bindString(13, cyjg);
        }
 
        String veh_sfxc = entity.getVeh_sfxc();
        if (veh_sfxc != null) {
            stmt.bindString(14, veh_sfxc);
        }
 
        String bz = entity.getBz();
        if (bz != null) {
            stmt.bindString(15, bz);
        }
 
        String bh = entity.getBh();
        if (bh != null) {
            stmt.bindString(16, bh);
        }
 
        String cyr = entity.getCyr();
        if (cyr != null) {
            stmt.bindString(17, cyr);
        }
        stmt.bindLong(18, entity.getCycs());
 
        String sfxny = entity.getSfxny();
        if (sfxny != null) {
            stmt.bindString(19, sfxny);
        }
 
        String xnyzl = entity.getXnyzl();
        if (xnyzl != null) {
            stmt.bindString(20, xnyzl);
        }
 
        String jy1 = entity.getJy1();
        if (jy1 != null) {
            stmt.bindString(21, jy1);
        }
 
        String jy2 = entity.getJy2();
        if (jy2 != null) {
            stmt.bindString(22, jy2);
        }
 
        String jy3 = entity.getJy3();
        if (jy3 != null) {
            stmt.bindString(23, jy3);
        }
 
        String jy4 = entity.getJy4();
        if (jy4 != null) {
            stmt.bindString(24, jy4);
        }
 
        String jy5 = entity.getJy5();
        if (jy5 != null) {
            stmt.bindString(25, jy5);
        }
 
        String jy6 = entity.getJy6();
        if (jy6 != null) {
            stmt.bindString(26, jy6);
        }
 
        String jy7 = entity.getJy7();
        if (jy7 != null) {
            stmt.bindString(27, jy7);
        }
 
        String jy8 = entity.getJy8();
        if (jy8 != null) {
            stmt.bindString(28, jy8);
        }
 
        String jy9 = entity.getJy9();
        if (jy9 != null) {
            stmt.bindString(29, jy9);
        }
 
        String jy10 = entity.getJy10();
        if (jy10 != null) {
            stmt.bindString(30, jy10);
        }
 
        String jy11 = entity.getJy11();
        if (jy11 != null) {
            stmt.bindString(31, jy11);
        }
 
        String jy12 = entity.getJy12();
        if (jy12 != null) {
            stmt.bindString(32, jy12);
        }
 
        String jy13 = entity.getJy13();
        if (jy13 != null) {
            stmt.bindString(33, jy13);
        }
 
        String jy14 = entity.getJy14();
        if (jy14 != null) {
            stmt.bindString(34, jy14);
        }
 
        String jy15 = entity.getJy15();
        if (jy15 != null) {
            stmt.bindString(35, jy15);
        }
 
        String jy16 = entity.getJy16();
        if (jy16 != null) {
            stmt.bindString(36, jy16);
        }
 
        String jy17 = entity.getJy17();
        if (jy17 != null) {
            stmt.bindString(37, jy17);
        }
 
        String jy18 = entity.getJy18();
        if (jy18 != null) {
            stmt.bindString(38, jy18);
        }
 
        String jy19 = entity.getJy19();
        if (jy19 != null) {
            stmt.bindString(39, jy19);
        }
 
        String jy20 = entity.getJy20();
        if (jy20 != null) {
            stmt.bindString(40, jy20);
        }
 
        String jy21 = entity.getJy21();
        if (jy21 != null) {
            stmt.bindString(41, jy21);
        }
 
        String jy22 = entity.getJy22();
        if (jy22 != null) {
            stmt.bindString(42, jy22);
        }
 
        String jy23 = entity.getJy23();
        if (jy23 != null) {
            stmt.bindString(43, jy23);
        }
 
        String jy24 = entity.getJy24();
        if (jy24 != null) {
            stmt.bindString(44, jy24);
        }
 
        String jy25 = entity.getJy25();
        if (jy25 != null) {
            stmt.bindString(45, jy25);
        }
 
        String qpzk = entity.getQpzk();
        if (qpzk != null) {
            stmt.bindString(46, qpzk);
        }
 
        String hpzk = entity.getHpzk();
        if (hpzk != null) {
            stmt.bindString(47, hpzk);
        }
 
        java.util.Date createtime = entity.getCreatetime();
        if (createtime != null) {
            stmt.bindLong(48, createtime.getTime());
        }
 
        java.util.Date updatetime = entity.getUpdatetime();
        if (updatetime != null) {
            stmt.bindLong(49, updatetime.getTime());
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, VehCheckInfo entity) {
        stmt.clearBindings();
 
        Long vehId = entity.getVehId();
        if (vehId != null) {
            stmt.bindLong(1, vehId);
        }
 
        String lsh = entity.getLsh();
        if (lsh != null) {
            stmt.bindString(2, lsh);
        }
 
        String hphm = entity.getHphm();
        if (hphm != null) {
            stmt.bindString(3, hphm);
        }
 
        String hpzl = entity.getHpzl();
        if (hpzl != null) {
            stmt.bindString(4, hpzl);
        }
 
        String syxz = entity.getSyxz();
        if (syxz != null) {
            stmt.bindString(5, syxz);
        }
 
        String xczl = entity.getXczl();
        if (xczl != null) {
            stmt.bindString(6, xczl);
        }
 
        String clsbdh = entity.getClsbdh();
        if (clsbdh != null) {
            stmt.bindString(7, clsbdh);
        }
 
        String ywlx = entity.getYwlx();
        if (ywlx != null) {
            stmt.bindString(8, ywlx);
        }
 
        String jczbh = entity.getJczbh();
        if (jczbh != null) {
            stmt.bindString(9, jczbh);
        }
 
        String cllx = entity.getCllx();
        if (cllx != null) {
            stmt.bindString(10, cllx);
        }
 
        String csys = entity.getCsys();
        if (csys != null) {
            stmt.bindString(11, csys);
        }
 
        String hdzk = entity.getHdzk();
        if (hdzk != null) {
            stmt.bindString(12, hdzk);
        }
 
        String cyjg = entity.getCyjg();
        if (cyjg != null) {
            stmt.bindString(13, cyjg);
        }
 
        String veh_sfxc = entity.getVeh_sfxc();
        if (veh_sfxc != null) {
            stmt.bindString(14, veh_sfxc);
        }
 
        String bz = entity.getBz();
        if (bz != null) {
            stmt.bindString(15, bz);
        }
 
        String bh = entity.getBh();
        if (bh != null) {
            stmt.bindString(16, bh);
        }
 
        String cyr = entity.getCyr();
        if (cyr != null) {
            stmt.bindString(17, cyr);
        }
        stmt.bindLong(18, entity.getCycs());
 
        String sfxny = entity.getSfxny();
        if (sfxny != null) {
            stmt.bindString(19, sfxny);
        }
 
        String xnyzl = entity.getXnyzl();
        if (xnyzl != null) {
            stmt.bindString(20, xnyzl);
        }
 
        String jy1 = entity.getJy1();
        if (jy1 != null) {
            stmt.bindString(21, jy1);
        }
 
        String jy2 = entity.getJy2();
        if (jy2 != null) {
            stmt.bindString(22, jy2);
        }
 
        String jy3 = entity.getJy3();
        if (jy3 != null) {
            stmt.bindString(23, jy3);
        }
 
        String jy4 = entity.getJy4();
        if (jy4 != null) {
            stmt.bindString(24, jy4);
        }
 
        String jy5 = entity.getJy5();
        if (jy5 != null) {
            stmt.bindString(25, jy5);
        }
 
        String jy6 = entity.getJy6();
        if (jy6 != null) {
            stmt.bindString(26, jy6);
        }
 
        String jy7 = entity.getJy7();
        if (jy7 != null) {
            stmt.bindString(27, jy7);
        }
 
        String jy8 = entity.getJy8();
        if (jy8 != null) {
            stmt.bindString(28, jy8);
        }
 
        String jy9 = entity.getJy9();
        if (jy9 != null) {
            stmt.bindString(29, jy9);
        }
 
        String jy10 = entity.getJy10();
        if (jy10 != null) {
            stmt.bindString(30, jy10);
        }
 
        String jy11 = entity.getJy11();
        if (jy11 != null) {
            stmt.bindString(31, jy11);
        }
 
        String jy12 = entity.getJy12();
        if (jy12 != null) {
            stmt.bindString(32, jy12);
        }
 
        String jy13 = entity.getJy13();
        if (jy13 != null) {
            stmt.bindString(33, jy13);
        }
 
        String jy14 = entity.getJy14();
        if (jy14 != null) {
            stmt.bindString(34, jy14);
        }
 
        String jy15 = entity.getJy15();
        if (jy15 != null) {
            stmt.bindString(35, jy15);
        }
 
        String jy16 = entity.getJy16();
        if (jy16 != null) {
            stmt.bindString(36, jy16);
        }
 
        String jy17 = entity.getJy17();
        if (jy17 != null) {
            stmt.bindString(37, jy17);
        }
 
        String jy18 = entity.getJy18();
        if (jy18 != null) {
            stmt.bindString(38, jy18);
        }
 
        String jy19 = entity.getJy19();
        if (jy19 != null) {
            stmt.bindString(39, jy19);
        }
 
        String jy20 = entity.getJy20();
        if (jy20 != null) {
            stmt.bindString(40, jy20);
        }
 
        String jy21 = entity.getJy21();
        if (jy21 != null) {
            stmt.bindString(41, jy21);
        }
 
        String jy22 = entity.getJy22();
        if (jy22 != null) {
            stmt.bindString(42, jy22);
        }
 
        String jy23 = entity.getJy23();
        if (jy23 != null) {
            stmt.bindString(43, jy23);
        }
 
        String jy24 = entity.getJy24();
        if (jy24 != null) {
            stmt.bindString(44, jy24);
        }
 
        String jy25 = entity.getJy25();
        if (jy25 != null) {
            stmt.bindString(45, jy25);
        }
 
        String qpzk = entity.getQpzk();
        if (qpzk != null) {
            stmt.bindString(46, qpzk);
        }
 
        String hpzk = entity.getHpzk();
        if (hpzk != null) {
            stmt.bindString(47, hpzk);
        }
 
        java.util.Date createtime = entity.getCreatetime();
        if (createtime != null) {
            stmt.bindLong(48, createtime.getTime());
        }
 
        java.util.Date updatetime = entity.getUpdatetime();
        if (updatetime != null) {
            stmt.bindLong(49, updatetime.getTime());
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public VehCheckInfo readEntity(Cursor cursor, int offset) {
        VehCheckInfo entity = new VehCheckInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // vehId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // lsh
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // hphm
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // hpzl
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // syxz
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // xczl
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // clsbdh
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // ywlx
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // jczbh
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // cllx
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // csys
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // hdzk
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // cyjg
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // veh_sfxc
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // bz
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // bh
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // cyr
            cursor.getInt(offset + 17), // cycs
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // sfxny
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // xnyzl
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // jy1
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // jy2
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // jy3
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // jy4
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // jy5
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // jy6
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // jy7
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // jy8
            cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28), // jy9
            cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29), // jy10
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // jy11
            cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31), // jy12
            cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32), // jy13
            cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33), // jy14
            cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34), // jy15
            cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35), // jy16
            cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36), // jy17
            cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37), // jy18
            cursor.isNull(offset + 38) ? null : cursor.getString(offset + 38), // jy19
            cursor.isNull(offset + 39) ? null : cursor.getString(offset + 39), // jy20
            cursor.isNull(offset + 40) ? null : cursor.getString(offset + 40), // jy21
            cursor.isNull(offset + 41) ? null : cursor.getString(offset + 41), // jy22
            cursor.isNull(offset + 42) ? null : cursor.getString(offset + 42), // jy23
            cursor.isNull(offset + 43) ? null : cursor.getString(offset + 43), // jy24
            cursor.isNull(offset + 44) ? null : cursor.getString(offset + 44), // jy25
            cursor.isNull(offset + 45) ? null : cursor.getString(offset + 45), // qpzk
            cursor.isNull(offset + 46) ? null : cursor.getString(offset + 46), // hpzk
            cursor.isNull(offset + 47) ? null : new java.util.Date(cursor.getLong(offset + 47)), // createtime
            cursor.isNull(offset + 48) ? null : new java.util.Date(cursor.getLong(offset + 48)) // updatetime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, VehCheckInfo entity, int offset) {
        entity.setVehId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setLsh(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setHphm(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setHpzl(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSyxz(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setXczl(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setClsbdh(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setYwlx(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setJczbh(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setCllx(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setCsys(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setHdzk(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setCyjg(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setVeh_sfxc(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setBz(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setBh(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setCyr(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setCycs(cursor.getInt(offset + 17));
        entity.setSfxny(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setXnyzl(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setJy1(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setJy2(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setJy3(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setJy4(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setJy5(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setJy6(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setJy7(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setJy8(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setJy9(cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28));
        entity.setJy10(cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29));
        entity.setJy11(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setJy12(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
        entity.setJy13(cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32));
        entity.setJy14(cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33));
        entity.setJy15(cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34));
        entity.setJy16(cursor.isNull(offset + 35) ? null : cursor.getString(offset + 35));
        entity.setJy17(cursor.isNull(offset + 36) ? null : cursor.getString(offset + 36));
        entity.setJy18(cursor.isNull(offset + 37) ? null : cursor.getString(offset + 37));
        entity.setJy19(cursor.isNull(offset + 38) ? null : cursor.getString(offset + 38));
        entity.setJy20(cursor.isNull(offset + 39) ? null : cursor.getString(offset + 39));
        entity.setJy21(cursor.isNull(offset + 40) ? null : cursor.getString(offset + 40));
        entity.setJy22(cursor.isNull(offset + 41) ? null : cursor.getString(offset + 41));
        entity.setJy23(cursor.isNull(offset + 42) ? null : cursor.getString(offset + 42));
        entity.setJy24(cursor.isNull(offset + 43) ? null : cursor.getString(offset + 43));
        entity.setJy25(cursor.isNull(offset + 44) ? null : cursor.getString(offset + 44));
        entity.setQpzk(cursor.isNull(offset + 45) ? null : cursor.getString(offset + 45));
        entity.setHpzk(cursor.isNull(offset + 46) ? null : cursor.getString(offset + 46));
        entity.setCreatetime(cursor.isNull(offset + 47) ? null : new java.util.Date(cursor.getLong(offset + 47)));
        entity.setUpdatetime(cursor.isNull(offset + 48) ? null : new java.util.Date(cursor.getLong(offset + 48)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(VehCheckInfo entity, long rowId) {
        entity.setVehId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(VehCheckInfo entity) {
        if(entity != null) {
            return entity.getVehId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(VehCheckInfo entity) {
        return entity.getVehId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
