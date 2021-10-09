package com.wtAppStore.mytools.net

import org.xutils.common.Callback.CancelledException
import java.io.File

/**
 * Description:下载回调
 * @author GaoYuanZou
 * @time 2021/09/30
 * Amendant record:
 **/
interface IDownloadCallback{

    /**
     * 等待状态
     */
    fun onWaiting()

    /**
     * 已经开始
     */
    fun onStarted()

    /**
     * 下载中
     */
    fun onLoading(total: Long, current: Long, isDownloading: Boolean)

    /**
     * 成功
     */
    fun onSuccess(result: File)

    /**
     * 下载错误
     */
    fun onError(ex: Throwable?, isOnCallback: Boolean)

    /**
     * 取消下载
     */
    fun onCancelled(cex: CancelledException?)

}