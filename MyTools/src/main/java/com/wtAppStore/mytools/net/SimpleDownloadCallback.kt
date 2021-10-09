package com.wtAppStore.mytools.net

import org.xutils.common.Callback
import java.io.File

/**
 * Description:
 * @author GaoYuanZou
 * @time 2021/09/30
 * Amendant record:
 **/
class SimpleDownloadCallback : IDownloadCallback {

    override fun onWaiting() {
        TODO("Not yet implemented")
    }

    override fun onStarted() {
        TODO("Not yet implemented")
    }

    override fun onLoading(total: Long, current: Long, isDownloading: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onSuccess(result: File) {
        TODO("Not yet implemented")
    }

    override fun onError(ex: Throwable?, isOnCallback: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onCancelled(cex: Callback.CancelledException?) {
        TODO("Not yet implemented")
    }

}