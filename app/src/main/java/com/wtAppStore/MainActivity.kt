package com.wtAppStore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wtAppStore.common.NetUtils
import com.wtAppStore.databinding.ActivityMainBinding
import com.wtAppStore.liveData.MainLiveData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(rootView.root)
        requestListData()
    }

    /**
     * 请求列表数据
     * 这里需要请求在线的所有应用列表和已安装的列表
     */
    private fun requestListData() {
        NetUtils.updateAllListData()
    }

    /**
     * 监听列表数据变化
     */
    private fun observerLiveData() {
        MainLiveData.mainAllAppList.observe(this, {

        })
    }
}