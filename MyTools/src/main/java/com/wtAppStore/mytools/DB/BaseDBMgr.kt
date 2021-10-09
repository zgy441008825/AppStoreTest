package com.wtAppStore.mytools.DB

import android.content.Context
import com.wtAppStore.mytools.DB.BaseDBBeanConstant.DB_COLUMN_ID
import com.wtAppStore.mytools.utils.Logger
import org.xutils.DbManager
import org.xutils.db.sqlite.WhereBuilder
import org.xutils.x

/**
 * Description:
 * @author gaoyuanzou
 * @time 2021/09/30
 * Amendant record:
 **/
abstract class BaseDBMgr {

    private val TAG = "BaseDBMgr"

    protected var dbMgr: DbManager

    abstract fun getDBName(): String

    abstract fun getDBVersion(): Int

    var context: Context? = null

    init {
        val daoConfig = DbManager.DaoConfig().apply {
            dbName = getDBName()
            dbVersion = getDBVersion()
        }

        dbMgr = x.getDb(daoConfig)
    }

    fun <T : BaseDBBean> getBean(where: WhereBuilder, t: Class<T>): T? = try {
        dbMgr.selector(t).where(where).findFirst()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    fun <T : BaseDBBean> getBeans(where: WhereBuilder?, t: Class<T>): List<T>? {
        return try {
            if (where == null) {
                dbMgr.selector(t).findAll()
            } else {
                dbMgr.selector(t).where(where).findAll()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun <T : BaseDBBean> addOrUpgradeBean(bean: T) {
        Logger.i(TAG, "addOrUpgradeBean bean:$bean")
        val iBean = getBean(WhereBuilder.b(DB_COLUMN_ID, "=", bean.id), bean::class.java)
        Logger.i(TAG, "addOrUpgradeBean getBean:$iBean")
        if (iBean != null) {
            dbMgr.delete(iBean)
        }
        dbMgr.saveBindingId(bean)
    }

    fun <T : BaseDBBean> delBean(bean: T?) {
        bean.let {
            dbMgr.delete(it)
        }
    }

}