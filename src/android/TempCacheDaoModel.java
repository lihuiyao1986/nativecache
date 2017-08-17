package com.huasco.greendao.gen;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jk on 2017/8/15.
 */
@Entity
public class TempCacheDaoModel{

    @Id
    private Long id;

    /**
     * 缓存的key
     */
    @Property(nameInDb = "cacheKey")
    private String cacheKey;

    /**
     * 缓存的value
     */
    @Property(nameInDb = "cacheValue")
    private String cacheValue;

    /**
     * 创建时间
     */
    @Property(nameInDb = "createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @Property(nameInDb = "updateTime")
    private Date updateTime;

    @Generated(hash = 1742811525)
    public TempCacheDaoModel(Long id, String cacheKey, String cacheValue,
            Date createTime, Date updateTime) {
        this.id = id;
        this.cacheKey = cacheKey;
        this.cacheValue = cacheValue;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Generated(hash = 418394880)
    public TempCacheDaoModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCacheKey() {
        return this.cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public String getCacheValue() {
        return this.cacheValue;
    }

    public void setCacheValue(String cacheValue) {
        this.cacheValue = cacheValue;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


}
