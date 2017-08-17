package com.huasco.plugins;

import com.alibaba.fastjson.JSON;
import com.huasco.greendao.gen.CacheDaoModel;
import com.huasco.greendao.gen.TempCacheDaoModel;
import com.huasco.greendao.utils.CacheDao;
import com.huasco.greendao.utils.DaoRespModel;
import com.huasco.greendao.utils.TempCacheDao;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 原生缓存插件
 */
public class NativeCache extends CordovaPlugin {

    private CallbackContext callbackContext;

    private final static String ERROR_CODE_LABEL = "errorCode";

    private final static String ERROR_MSG_LABEL = "errorMsg";

    private final static String SUCCESS_FLAG_MSG = "success";

    private final static String ERROR_CODE = "9999";


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        if ("put".equals(action)) { // 添加缓存
            return putCache(args, callbackContext);
        } else if ("get".equals(action)) { // 获取缓存
            return getCache(args, callbackContext);
        } else if ("delete".equals(action)) { //删除缓存
            return deleteCache(args, callbackContext);
        } else if ("clear".equals(action)) { // 清空缓存
            return clearCache(args, callbackContext);
        }
        return super.execute(action, args, callbackContext);
    }

    /**
     * 添加缓存
     *
     * @param args
     * @param callbackContext
     * @return
     */
    private Boolean putCache(JSONArray args, CallbackContext callbackContext) throws JSONException {
        JSONObject options = args.optJSONObject(0);
        String key = trimNull(options.getString("key"), "");
        String value = trimNull(options.getString("value"), "");
        String type = trimNull(options.getString("type"), "0");// 0--正式 1--临时
        if ("0".equals(type)) {
            putNormal(key, value);
        } else {
            putTemp(key, value);
        }
        return true;
    }

    /**
     * 存入正式缓存
     *
     * @param key
     * @param value
     */
    private void putNormal(String key, String value) {
        // 先根据key去查询
        DaoRespModel<CacheDaoModel> queryResult = CacheDao.getInstance().queryByKey(key);
        if (!queryResult.isSuccess()) {
            this.callbackContext.error(this.packError(queryResult));
        } else {
            DaoRespModel result = null;
            CacheDaoModel model = queryResult.getResult();
            if (model == null) { // 不存在对应的key则新增
                model = new CacheDaoModel();
                model.setUpdateTime(new Date());
                model.setCreateTime(new Date());
                model.setCacheKey(key);
                model.setCacheValue(value);
                result = CacheDao.getInstance().put(model);
            } else { // 存在对应的key则更新
                model.setCacheValue(value);
                model.setUpdateTime(new Date());
                result = CacheDao.getInstance().update(model);
            }
            if (!result.isSuccess()) {
                this.callbackContext.error(this.packError(result));
            } else {
                this.callbackContext.success(model == null ? "" : model.getCacheValue());
            }
        }
    }

    /**
     * 存入正式缓存
     *
     * @param key
     * @param value
     */
    private void putTemp(String key, String value) {
        // 先根据key去查询
        DaoRespModel<TempCacheDaoModel> queryResult = TempCacheDao.getInstance().queryByKey(key);
        if (!queryResult.isSuccess()) {
            this.callbackContext.error(this.packError(queryResult));
        } else {
            DaoRespModel result = null;
            TempCacheDaoModel model = queryResult.getResult();
            if (model == null) { // 不存在对应的key则新增
                model = new TempCacheDaoModel();
                model.setUpdateTime(new Date());
                model.setCreateTime(new Date());
                model.setCacheKey(key);
                model.setCacheValue(value);
                result = TempCacheDao.getInstance().put(model);
            } else { // 存在对应的key则更新
                model.setCacheValue(value);
                model.setUpdateTime(new Date());
                result = TempCacheDao.getInstance().update(model);
            }
            if (!result.isSuccess()) {
                this.callbackContext.error(this.packError(result));
            } else {
                this.callbackContext.success(model == null ? "" : model.getCacheValue());
            }
        }
    }

    /**
     * 获取缓存
     *
     * @param args
     * @param callbackContext
     * @return
     */
    private Boolean getCache(JSONArray args, CallbackContext callbackContext) throws JSONException {
        JSONObject options = args.optJSONObject(0);
        String key = trimNull(options.getString("key"), "");
        String type = trimNull(options.getString("type"), "0");// 0--正式 1--临时
        if ("0".equals(type)) {
            getNormal(key);
        } else {
            getTemp(key);
        }

        return true;
    }

    /**
     * 从正式缓存库取
     *
     * @param key
     */
    private void getNormal(String key) {
        DaoRespModel<CacheDaoModel> result = CacheDao.getInstance().queryByKey(key);
        if (!result.isSuccess()) {
            this.callbackContext.error(this.packError(result));
        } else {

            this.callbackContext.success(result.getResult() == null ? "" : result.getResult().getCacheValue());
        }
    }

    /**
     * 从临时缓存库取
     *
     * @param key
     */
    private void getTemp(String key) {
        DaoRespModel<TempCacheDaoModel> result = TempCacheDao.getInstance().queryByKey(key);
        if (!result.isSuccess()) {
            this.callbackContext.error(this.packError(result));
        } else {

            this.callbackContext.success(result.getResult() == null ? "" : result.getResult().getCacheValue());
        }
    }

    /**
     * 删除缓存
     *
     * @param args
     * @param callbackContext
     * @return
     */
    private Boolean deleteCache(JSONArray args, CallbackContext callbackContext) throws JSONException {
        JSONObject options = args.optJSONObject(0);
        String key = trimNull(options.getString("key"), "");
        String type = trimNull(options.getString("type"), "0");// 0--正式 1--临时
        if ("0".equals(type)) {
            delNormal(key);
        } else {
            delTemp(key);
        }
        return true;
    }

    /**
     * 从正式缓存库删除
     *
     * @param key
     */
    private void delNormal(String key) {
        DaoRespModel<CacheDaoModel> result = CacheDao.getInstance().delete(key);
        if (!result.isSuccess()) {
            this.callbackContext.error(this.packError(result));
        } else {
            this.callbackContext.success();
        }
    }

    /**
     * 从临时缓存库取
     *
     * @param key
     */
    private void delTemp(String key) {
        DaoRespModel<TempCacheDaoModel> result = TempCacheDao.getInstance().delete(key);
        if (!result.isSuccess()) {
            this.callbackContext.error(this.packError(result));
        } else {
            this.callbackContext.success();
        }
    }


    /**
     * 清空缓存
     *
     * @param args
     * @param callbackContext
     * @return
     */
    private Boolean clearCache(JSONArray args, CallbackContext callbackContext) throws JSONException {
        JSONObject options = args.optJSONObject(0);
        String type = trimNull(options.getString("type"), "0");// 0--正式 1--临时
        if ("0".equals(type)) {
            clearNormal();
        } else {
            clearTemp();
        }
        return true;
    }

    /**
     * 从正式缓存库删除
     */
    private void clearNormal() {
        DaoRespModel<CacheDaoModel> result = CacheDao.getInstance().clearAll();
        if (!result.isSuccess()) {
            this.callbackContext.error(this.packError(result));
        } else {
            this.callbackContext.success();
        }
    }

    /**
     * 从临时缓存库取
     */
    private void clearTemp() {
        DaoRespModel<TempCacheDaoModel> result = TempCacheDao.getInstance().clearAll();
        if (!result.isSuccess()) {
            this.callbackContext.error(this.packError(result));
        } else {
            this.callbackContext.success();
        }
    }

    /**
     * 组装错误信息
     *
     * @param respModel
     * @return
     */
    private String packError(DaoRespModel respModel) {
        Map<String, Object> errorInfo = new HashMap<String, Object>();
        errorInfo.put(ERROR_CODE_LABEL, ERROR_CODE);
        errorInfo.put(SUCCESS_FLAG_MSG, false);
        errorInfo.put(ERROR_MSG_LABEL, respModel.getMessage());
        return JSON.toJSONString(errorInfo);
    }


    /**
     *
     * @param str
     * @return
     */
    public boolean isEmpty(String str){
        if(str == null || str.trim().equals(""))
            return true;
        return false;
    }

    public String trimNull(String str,String defaultStr){
        if (isEmpty(str)){
            return defaultStr;
        }else{
            return str.trim();
        }
    }
}
