package com.huasco.greendao.utils;

import com.huasco.base.BaseApplication;
import com.huasco.greendao.gen.DaoMaster;
import com.huasco.greendao.gen.DaoSession;

/**
 * DAO管理器
 */
public class GreenDaoManager {

    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private static final String DATABASE_NAME = "database";

    private GreenDaoManager() {
        init();
    }

    /**
     * 静态内部类，实例化对象使用
     */
    private static class SingleInstanceHolder {
        private static final GreenDaoManager INSTANCE = new GreenDaoManager();
    }

    /**
     * 对外唯一实例的接口
     *
     * @return
     */
    public static GreenDaoManager getInstance() {
        return SingleInstanceHolder.INSTANCE;
    }

    /**
     * 初始化数据
     */
    private void init() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(BaseApplication.getInstance(),
                DATABASE_NAME);
        daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public DaoSession getNewDaoSession() {
        daoSession = daoMaster.newSession();
        return daoSession;
    }
}
