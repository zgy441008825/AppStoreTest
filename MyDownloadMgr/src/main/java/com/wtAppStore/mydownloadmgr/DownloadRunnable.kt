package com.wtAppStore.mydownloadmgr

import com.wtAppStore.mydownloadmgr.db.DBConstant
import com.wtAppStore.mydownloadmgr.db.DbMgr
import com.wtAppStore.mydownloadmgr.db.DownLoadBean
import com.wtAppStore.mytools.net.IDownloadCallback
import com.wtAppStore.mytools.net.NetTools
import org.xutils.common.Callback
import java.io.File

/**
 * Description:
 * 下载任务执行器
 * @author gaoyuanzou
 * @time 2021/09/30
 * Amendant record:
 **/
class DownloadRunnable constructor(
    private val downLoadBean: DownLoadBean,
    private val callBack: IDownloadCallback,
    private val downloadExecuteImpl: DefDownloadExecuteImpl
) : Runnable {


    private lateinit var cancelable: Callback.Cancelable

    override fun run() {
        cancelable = NetTools.downLoadFile(downLoadBean.url, downLoadBean.fileSavePath, callback)
    }

    fun stopDownload() {
        cancelable.cancel()
        callBack.onCancelled(Callback.CancelledException("user stop"))
    }

    fun getState(): Int = downLoadBean.downloadState

    private val callback = object : Callback.ProgressCallback<File> {

        override fun onSuccess(result: File?) {
            result?.let {
                callBack.onSuccess(it)
            }
            downloadExecuteImpl.stopDownload(downLoadBean.apply {
                downloadState = DBConstant.DownloadState.STATE_FINISH
            }, false)
        }

        override fun onError(ex: Throwable?, isOnCallback: Boolean) {
            callBack.onError(ex, isOnCallback)
            downloadExecuteImpl.stopDownload(downLoadBean.apply {
                downloadState = DBConstant.DownloadState.STATE_ERROR
            }, false)
        }

        override fun onCancelled(cex: Callback.CancelledException?) {
            callBack.onCancelled(cex)
            downloadExecuteImpl.stopDownload(downLoadBean.apply { downloadState = DBConstant.DownloadState.STATE_ERROR }, true)
        }

        override fun onFinished() {
            downloadExecuteImpl.stopDownload(downLoadBean.apply { downloadState = DBConstant.DownloadState.STATE_FINISH }, true)
        }

        override fun onWaiting() {
            callBack.onWaiting()
            DbMgr.instance.addOrUpgradeBean(downLoadBean.apply { downloadState = DBConstant.DownloadState.STATE_WAIT })
        }

        override fun onStarted() {
            callBack.onStarted()
            DbMgr.instance.addOrUpgradeBean(downLoadBean.apply { downloadState = DBConstant.DownloadState.STATE_LOADING })
        }

        override fun onLoading(total: Long, current: Long, isDownloading: Boolean) {
            callBack.onLoading(total, current, isDownloading)
            DbMgr.instance.addOrUpgradeBean(downLoadBean.apply {
                downloadState = DBConstant.DownloadState.STATE_LOADING
                progress = ((current.toFloat() / total.toFloat()) * 100).toInt()
            })
        }

    }
}