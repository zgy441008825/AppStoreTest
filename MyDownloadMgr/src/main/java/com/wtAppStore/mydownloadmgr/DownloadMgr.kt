package com.wtAppStore.mydownloadmgr

import android.content.Context
import com.wtAppStore.mytools.net.IDownloadCallback
import com.wtAppStore.mytools.net.NetTools
import com.wtAppStore.mytools.utils.SingletonHolder

/**
 * Description:
 * @author GaoYuanZou
 * @time 2021/10/09
 * Amendant record:
 **/
class DownloadMgr private constructor(context: Context) {

    companion object : SingletonHolder<DownloadMgr, Context>(::DownloadMgr)

    var downloadExecute: IDownloadExecute = DefDownloadExecuteImpl()

    fun startDownLoad(url: String, savePath: String, callback: IDownloadCallback) {
        downloadExecute.startDownload(url, savePath, callback)
    }

    fun stopDownload(url: String) {
        downloadExecute.stopDownload(url)
    }

}