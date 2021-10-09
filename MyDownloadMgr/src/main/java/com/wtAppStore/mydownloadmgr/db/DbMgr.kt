package com.wtAppStore.mydownloadmgr.db

import android.content.Context
import com.wtAppStore.mydownloadmgr.db.DBConstant.DB_DL_COL_URL
import com.wtAppStore.mydownloadmgr.db.DBConstant.DB_FILE_NAME
import com.wtAppStore.mydownloadmgr.db.DBConstant.DB_VERSION
import com.wtAppStore.mytools.DB.BaseDBMgr
import com.wtAppStore.mytools.utils.SingletonHolder
import org.xutils.db.sqlite.WhereBuilder

/**
 * Description:
 * @author gaoyuanzou
 * @time 2021/09/30
 * Amendant record:
 **/
class DbMgr private constructor() : BaseDBMgr() {

    override fun getDBName(): String {
        return DB_FILE_NAME
    }

    override fun getDBVersion(): Int {
        return DB_VERSION
    }

    companion object {
        val instance: DbMgr by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            DbMgr()
        }
    }

    fun getDownloadBean(url: String): DownLoadBean? {
        return getBean(WhereBuilder.b(DB_DL_COL_URL, "=", url), DownLoadBean::class.java)
    }
}