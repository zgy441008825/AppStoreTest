package com.wtAppStore.liveData

import androidx.lifecycle.MutableLiveData
import com.wtAppStore.common.AppConfigBean

/**
 * Description:
 * @author GaoYuanZou
 * @time 2021/10/09
 * Amendant record:
 **/
object MainLiveData {

    /**
     * 首页所有APPList的数据
     */
    val mainAllAppList = MutableLiveData<Array<AppConfigBean>?>()

    /**
     * 已安装的应用列表
     */
    val mainInstallAppList = MutableLiveData<Array<AppConfigBean>?>()

}