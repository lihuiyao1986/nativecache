package com.huasco.greendao.utils;

import com.huasco.greendao.gen.CacheDaoModel;
import com.huasco.greendao.gen.CacheDaoModelDao;
import com.huasco.utils.StringUtils;

import java.util.List;

import static android.R.id.list;
import static android.media.CamcorderProfile.get;

/**
 * Created by jk on 2017/8/13.
 */

public class CacheDao {

    private static CacheDao INSTANCE = null;

    /**
     * 静态内部类，实例化对象使用
     */
    private static class SingleInstanceHolder {
        private static final CacheDao INSTANCE = new CacheDao();
    }

    /**
     * 对外唯一实例的接口
     *
     * @return
     */
    public static CacheDao getInstance() {
        return SingleInstanceHolder.INSTANCE;
    }


    /**
     * 添加缓存
     *
     * @param daoModel
     * @return
     */
    public DaoRespModel put(CacheDaoModel daoModel) {
        DaoRespModel result = new DaoRespModel();
        if (daoModel == null) {
            result.setSuccess(false);
            result.setMessage("缓存内容为空");
        } else if (StringUtils.isEmpty(daoModel.getCacheKey())) {
            result.setSuccess(false);
            result.setMessage("缓存的key为空");
        } else {
            long numbers = GreenDaoManager
                    .getInstance()
                    .getDaoSession()
                    .getCacheDaoModelDao()
                    .insert(daoModel);
            if (numbers == 0) {
                result.setSuccess(false);
                result.setMessage("缓存失败");
            }
        }
        return result;
    }


    /**
     * 清空缓存
     * @return
     */
    public DaoRespModel clearAll(){
        DaoRespModel result = new DaoRespModel();
        GreenDaoManager
                .getInstance()
                .getDaoSession()
                .getCacheDaoModelDao().deleteAll();
        return result;
    }

    /**
     * 删除缓存
     *
     * @param cacheKey
     * @return
     */
    public DaoRespModel delete(String cacheKey) {
        DaoRespModel result = new DaoRespModel();
        if (StringUtils.isEmpty(cacheKey)) {
            result.setMessage("查询的缓存key为空");
            result.setSuccess(false);
        } else {
            GreenDaoManager
                    .getInstance()
                    .getDaoSession()
                    .getCacheDaoModelDao()
                    .queryBuilder().where(CacheDaoModelDao.Properties.CacheKey.eq(cacheKey.trim()))
                    .buildDelete()
                    .executeDeleteWithoutDetachingEntities();
        }
        return result;
    }


    /**
     * 更新缓存
     * @param daoModel
     * @return
     */
    public DaoRespModel update(CacheDaoModel daoModel){
        DaoRespModel result = new DaoRespModel();
        if (StringUtils.isEmpty(daoModel.getCacheKey())) {
            result.setMessage("更新的缓存key为空");
            result.setSuccess(false);
        } else {
            DaoRespModel<CacheDaoModel> queryResult = this.queryByKey(daoModel.getCacheKey());
            if (queryResult.isSuccess() && queryResult.getResult() != null){
                daoModel.setId(queryResult.getResult().getId());
                GreenDaoManager
                        .getInstance()
                        .getDaoSession()
                        .getCacheDaoModelDao()
                        .update(daoModel);
            }else{
                result.setMessage("更新的缓存失败");
                result.setSuccess(false);
            }
        }
        return result;
    }

    /**
     * 根据可以查询缓存信息
     *
     * @param cacheKey
     * @return
     */
    public DaoRespModel<CacheDaoModel> queryByKey(String cacheKey) {
        DaoRespModel<CacheDaoModel> result = new DaoRespModel<CacheDaoModel>();
        if (StringUtils.isEmpty(cacheKey)) {
            result.setMessage("查询的缓存key为空");
            result.setSuccess(false);
        } else {
            List<CacheDaoModel> list = GreenDaoManager
                    .getInstance()
                    .getDaoSession()
                    .getCacheDaoModelDao()
                    .queryBuilder()
                    .where(CacheDaoModelDao.Properties.CacheKey.eq(cacheKey.trim()))
                    .list();
            result.setResult(list.size() > 0 ? list.get(0) : null);
        }
        return result;
    }
}
