package com.wtAppStore.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.wtAppStore.mydownloadmgr.DownloadMgr
import com.wtAppStore.mytools.net.IDownloadCallback
import org.xutils.x

class NetService : Service() {

    private lateinit var myBind: MyBind

    override fun onCreate() {
        super.onCreate()
        myBind = MyBind()
    }


    override fun onBind(intent: Intent): IBinder {
        return myBind
    }

    class MyBind : Binder() {

        fun startDown(url: String, savePath: String, callback: IDownloadCallback) {
            DownloadMgr.getInstance(x.app()).startDownLoad(url, savePath, callback)
        }

    }
}