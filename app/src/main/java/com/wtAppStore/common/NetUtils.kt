package com.wtAppStore.common

import com.wtAppStore.BuildConfig
import com.wtAppStore.doa.AppStoreDBMgr
import com.wtAppStore.liveData.MainLiveData
import com.wtAppStore.mydownloadmgr.DownloadMgr
import com.wtAppStore.mytools.net.NetTools
import com.wtAppStore.mytools.utils.GsonUtils
import com.wtAppStore.mytools.utils.Logger
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.xutils.x

/**
 * Description:
 * @author GaoYuanZou
 * @time 2021/10/09
 * Amendant record:
 **/
object NetUtils {

    val TAG = "NetUtils"

    /**
     * 获取所有应用的列表
     */
    fun updateAllListData() {
        Flowable.just(NetTools.getStrSync(BuildConfig.ConfigHostUrl))
            .subscribeOn(Schedulers.io())
            .map {
                Logger.i(TAG, "bindAllList map str:$it")
                val beanList: Array<AppConfigBean>? = GsonUtils.getBean(it, Array<AppConfigBean>::class.java)
                Logger.i(TAG, "bindAllList map beanList:$beanList")
                if (beanList == null) {
                    throw NullPointerException("updateAllListData get AppList error")
                }
                return@map beanList
            }
            .subscribe({
                if (it.isNotEmpty()) {
                    MainLiveData.mainAllAppList.postValue(it)
                    AppStoreDBMgr.instance.saveAppConfigList(it)
                } else
                    MainLiveData.mainAllAppList.postValue(null)
            }, {
                MainLiveData.mainAllAppList.postValue(null)
                it.printStackTrace()
            })
    }

}