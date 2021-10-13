package com.wtAppStore.mytools.utils

import android.util.Log
import org.xutils.BuildConfig

/**
 * Description:日志打印
 * @author gaoyuanzou
 * @time 2021/09/30
 * Amendant record:
 **/
object Logger {

    private const val TAG_VER = BuildConfig.VERSION_NAME
    private const val TAG_APP = "WT_AppStore"

    private fun getTag(tag: String): String = "[${TAG_APP}_${TAG_VER}] $tag"

    fun d(tag: String, msg: String) {
        Log.d(getTag(tag), msg)
    }

    fun i(tag: String, msg: String) {
        Log.i(getTag(tag), msg)
    }

    fun w(tag: String, msg: String, throwable: Throwable? = null) {
        throwable?.apply {
            Log.w(getTag(tag), msg, throwable)
        } ?: Log.w(getTag(tag), msg)
    }

    fun e(tag: String, msg: String, throwable: Throwable? = null) {
        throwable?.apply {
            Log.e(getTag(tag), msg, throwable)
        } ?: Log.e(getTag(tag), msg)
    }

}