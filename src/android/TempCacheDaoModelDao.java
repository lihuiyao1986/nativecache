package com.huasco.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TEMP_CACHE_DAO_MODEL".
*/
public class TempCacheDaoModelDao extends AbstractDao<TempCacheDaoModel, Long> {

    public static final String TABLENAME = "TEMP_CACHE_DAO_MODEL";

    /**
     * Properties of entity TempCacheDaoModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property CacheKey = new Property(1, String.class, "cacheKey", false, "cacheKey");
        public final static Property CacheValue = new Property(2, String.class, "cacheValue", false, "cacheValue");
        public final static Property CreateTime = new Property(3, java.util.Date.class, "createTime", false, "createTime");
        public final static Property UpdateTime = new Property(4, java.util.Date.class, "updateTime", false, "updateTime");
    }


    public TempCacheDaoModelDao(DaoConfig config) {
        super(config);
    }
    
    public TempCacheDaoModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TEMP_CACHE_DAO_MODEL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"cacheKey\" TEXT," + // 1: cacheKey
                "\"cacheValue\" TEXT," + // 2: cacheValue
                "\"createTime\" INTEGER," + // 3: createTime
                "\"updateTime\" INTEGER);"); // 4: updateTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TEMP_CACHE_DAO_MODEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TempCacheDaoModel entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String cacheKey = entity.getCacheKey();
        if (cacheKey != null) {
            stmt.bindString(2, cacheKey);
        }
 
        String cacheValue = entity.getCacheValue();
        if (cacheValue != null) {
            stmt.bindString(3, cacheValue);
        }
 
        java.util.Date createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindLong(4, createTime.getTime());
        }
 
        java.util.Date updateTime = entity.getUpdateTime();
        if (updateTime != null) {
            stmt.bindLong(5, updateTime.getTime());
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TempCacheDaoModel entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String cacheKey = entity.getCacheKey();
        if (cacheKey != null) {
            stmt.bindString(2, cacheKey);
        }
 
        String cacheValue = entity.getCacheValue();
        if (cacheValue != null) {
            stmt.bindString(3, cacheValue);
        }
 
        java.util.Date createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindLong(4, createTime.getTime());
        }
 
        java.util.Date updateTime = entity.getUpdateTime();
        if (updateTime != null) {
            stmt.bindLong(5, updateTime.getTime());
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public TempCacheDaoModel readEntity(Cursor cursor, int offset) {
        TempCacheDaoModel entity = new TempCacheDaoModel( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // cacheKey
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // cacheValue
            cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)), // createTime
            cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)) // updateTime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TempCacheDaoModel entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCacheKey(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCacheValue(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCreateTime(cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)));
        entity.setUpdateTime(cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(TempCacheDaoModel entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(TempCacheDaoModel entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(TempCacheDaoModel entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
