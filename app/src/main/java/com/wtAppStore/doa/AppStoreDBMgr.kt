package com.wtAppStore.doa

import com.wtAppStore.common.AppConfigBean
import com.wtAppStore.common.AppStoreDBConstant
import com.wtAppStore.mytools.DB.BaseDBMgr
import com.wtAppStore.mytools.utils.Logger
import org.xutils.db.sqlite.WhereBuilder

/**
 * Description:应用商店数据库管理类
 *
 * 保存应用的状态
 * @author GaoYuanZou
 * @time 2021/10/13
 * Amendant record:
 **/
class AppStoreDBMgr private constructor() : BaseDBMgr() {

    private val TAG = "AppStoreDBMgr"

    companion object {
        val instance: AppStoreDBMgr by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            AppStoreDBMgr()
        }
    }

    override fun getDBName(): String = AppStoreDBConstant.DB_FILE_NAME

    override fun getDBVersion(): Int = AppStoreDBConstant.DB_FILE_VER

    fun getAppConfigBean(pgkName: String): AppConfigBean? {
        return getBean(WhereBuilder.b(AppStoreDBConstant.DB_APP_CONFIG_BEAN_COL_APP_PKG_NAME, "=", pgkName), AppConfigBean::class.java)
    }

    /**
     * 保存AppConfigBean
     */
    fun saveAppConfigBean(bean: AppConfigBean) {
        val beanTmp = getAppConfigBean(bean.appPkgName)
        beanTmp?.apply {
            bean.id = id
            dbMgr.saveOrUpdate(bean)
        } ?: dbMgr.saveBindingId(bean)
    }

    /**
     * 保存APPConfigBean列表
     */
    fun saveAppConfigList(list: Array<AppConfigBean>) {
        //需要更新的列表
        val updateBeanList = list.filter { getAppConfigBean(it.appPkgName) != null }
        Logger.i(TAG, "saveAppConfigList updateBeanList:$updateBeanList")
        if (updateBeanList.isNotEmpty()) {
            dbMgr.saveOrUpdate(updateBeanList)
        }
        //新加的列表
        val saveBeanList = list.filter { getAppConfigBean(it.appPkgName) == null }
        Logger.i(TAG, "saveAppConfigList saveBeanList:$saveBeanList")
        if (saveBeanList.isNotEmpty()) {
            dbMgr.saveOrUpdate(saveBeanList)
        }
    }
}