package com.wtAppStore.mydownloadmgr

import com.wtAppStore.mydownloadmgr.db.DBConstant
import com.wtAppStore.mydownloadmgr.db.DbMgr
import com.wtAppStore.mydownloadmgr.db.DownLoadBean
import com.wtAppStore.mytools.net.IDownloadCallback
import com.wtAppStore.mytools.utils.Logger
import org.xutils.db.sqlite.WhereBuilder
import java.util.concurrent.Executors

/**
 * Description:默认的下载执行器
 * @author GaoYuanZou
 * @time 2021/09/30
 * Amendant record:
 **/
class DefDownloadExecuteImpl : IDownloadExecute {

    private val TAG = "DefDownloadExecuteImpl"

    private val downLoadExec = Executors.newFixedThreadPool(5)

    /**
     * 当前执行任务的线程
     */
    private val runnableMap = HashMap<String, DownloadRunnable>()

    /**
     * 开始下载一个文件
     */
    override fun startDownload(url: String, savePath: String, callBack: IDownloadCallback) {
        val bean = DownLoadBean().apply {
            this.url = url
            this.label = url
            this.fileSavePath = savePath
            this.progress = 0
            this.downloadState = DBConstant.DownloadState.STATE_WAIT
        }
        Logger.i(TAG, "startDownload bean:$bean")
        //先通过url查找表中是否有相同的下载文件，如果有，查看下载状态
        val iBean = DbMgr.instance.getBean(WhereBuilder.b(DBConstant.DB_DL_COL_URL, "=", url), DownLoadBean::class.java)
        Logger.i(TAG, "startDownload iBean:$iBean")
        if (iBean != null) {
            //如果是下载中、等待则不进行操作 return
            if (iBean.downloadState == DBConstant.DownloadState.STATE_LOADING || iBean.downloadState == DBConstant.DownloadState.STATE_WAIT) {
                //等待中，判断当前是否有执行的Runnable，包含当前任务说明正在下载，不做处理
                Logger.i(TAG, "startDownload runnableMap containsKey:${runnableMap.containsKey(url)}")
                if (runnableMap.containsKey(url)) {
                    return
                }
            }
            startDownload(iBean, callBack)
        } else {
            DbMgr.instance.addOrUpgradeBean(bean)
            startDownload(bean, callBack)
        }
    }

    override fun getDownloadState(url: String): Int {
        return if (runnableMap.containsKey(url)) {
            runnableMap[url]!!.getState()
        } else {
            val iBean = DbMgr.instance.getBean(WhereBuilder.b(DBConstant.DB_DL_COL_URL, "=", url), DownLoadBean::class.java)
            iBean?.downloadState ?: -1
        }
    }

    override fun getDownloadProgress(url: String): Int {
        val iBean = DbMgr.instance.getBean(WhereBuilder.b(DBConstant.DB_DL_COL_URL, "=", url), DownLoadBean::class.java)
        return iBean?.downloadState ?: -1
    }

    private fun startDownload(downLoadBean: DownLoadBean, callBack: IDownloadCallback) {
        Logger.i(TAG, "startDownload downLoadBean:$downLoadBean")
        DbMgr.instance.addOrUpgradeBean(downLoadBean.apply {
            downloadState = DBConstant.DownloadState.STATE_WAIT
        })
        val downloadRunnable = DownloadRunnable(downLoadBean, callBack, this)
        runnableMap[downLoadBean.url] = downloadRunnable
        downLoadExec.submit(downloadRunnable)
    }

    /**
     * 停止下载
     */
    override fun stopDownload(url: String) {
        if (runnableMap.containsKey(url)) {
            runnableMap[url]?.stopDownload()
            runnableMap.remove(url)
        }
        DbMgr.instance.delBean(DbMgr.instance.getDownloadBean(url))
    }

    override fun pauseDownload(url: String) {
        //这里现在是空实现  不能暂停
    }

    override fun resumeDownload(url: String) {
        //这里现在是空实现
    }

    /**
     * 停止下载
     */
    fun stopDownload(downLoadBean: DownLoadBean, removeFromDb: Boolean) {
        if (runnableMap.containsKey(downLoadBean.url)) {
            runnableMap[downLoadBean.url]?.stopDownload()
            runnableMap.remove(downLoadBean.url)
        }
        if (removeFromDb)
            DbMgr.instance.delBean(downLoadBean)
    }

}