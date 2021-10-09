package com.wtAppStore.mytools.net

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import com.wtAppStore.mytools.utils.Logger
import org.xutils.common.Callback
import org.xutils.http.HttpMethod
import org.xutils.http.RequestParams
import org.xutils.x
import java.io.File

/**
 * Description:网络相关工具类
 * @author gaoyuanzou
 * @time 2021/09/30
 * Amendant record:
 **/
object NetTools {

    private const val TAG = "NetTools"

    /**
     * 判断网络是否可用
     */
    fun isNetworkAvailable(context: Application): Boolean {
        val cMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        cMgr.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networks = it.allNetworks
                for (info in networks) {
                    val netWorkInfo = it.getNetworkInfo(info)
                    if (netWorkInfo?.state == NetworkInfo.State.CONNECTED)
                        return true
                }
            } else {
                val networkInfo = it.allNetworkInfo
                for (info in networkInfo) {
                    if (info.state == NetworkInfo.State.CONNECTED) return true
                }
            }

        }
        return false
    }

    private fun getBaseRequest(url: String): RequestParams = RequestParams(url).apply {
        isAutoResume = true
        maxRetryCount = 5
        method = HttpMethod.GET
        connectTimeout = 5000
        readTimeout = 5000
    }

    /**
     * 从指定路径获取String流，基本上所有的json、xml等配置文件都可以使用这个方法
     */
    fun getStr(url: String, callbak: Callback.CommonCallback<String>) {
        x.http().get(getBaseRequest(url), callbak)
    }

    fun <T> getBean(url: String, callback: Callback.CommonCallback<GsonResponse<T>>) {
        x.http().get(getBaseRequest(url), callback)
    }

    fun <T> getBean(url: String, t: Class<T>): T? {
        return try {
            x.http().getSync(getBaseRequest(url), t)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    /**
     * 从指定路径获取String流，基本上所有的json、xml等配置文件都可以使用这个方法
     *
     * 该方法为同步方法.
     */
    fun getStrSync(url: String): String {
        Logger.i(TAG, "getStrSync url:$url")
        return try {
            x.http().getSync(getBaseRequest(url), String::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    fun downLoadFile(url: String, savePath: String, callback: Callback.ProgressCallback<File>): Callback.Cancelable {
        val requestParams = getBaseRequest(url)
        requestParams.saveFilePath = savePath
        requestParams.isAutoResume = true
        requestParams.isAutoRename = false
        return x.http().get(requestParams, callback)
    }

}