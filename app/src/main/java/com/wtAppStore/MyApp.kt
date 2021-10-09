package com.wtAppStore

import android.app.Application
import org.xutils.x

/**
 * Description:
 * @author gaoyuanzou
 * @time 2021/09/30
 * Amendant record:
 **/
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        x.Ext.init(this)
        x.Ext.setDebug(true)
    }

}