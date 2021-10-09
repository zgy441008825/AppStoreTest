package com.wtAppStore.mydownloadmgr

import com.wtAppStore.mytools.net.IDownloadCallback

/**
 * Description:
 * 下载任务执行器
 * @author GaoYuanZou
 * @time 2021/10/09
 * Amendant record:
 **/
interface IDownloadExecute {

    /**
     * 开始下载
     */
    fun startDownload(url: String, savePath: String, callBack: IDownloadCallback)

    /**
     * 获取下载状态
     */
    fun getDownloadState(url: String): Int

    /**
     * 获取下载进度
     */
    fun getDownloadProgress(url: String): Int

    /**
     * 停止下载
     */
    fun stopDownload(url: String)

    /**
     * 暂停下载
     */
    fun pauseDownload(url: String)

    /**
     * 恢复下载
     */
    fun resumeDownload(url: String)

}