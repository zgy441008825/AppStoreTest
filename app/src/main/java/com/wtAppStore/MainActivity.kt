package com.wtAppStore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wtAppStore.common.NetUtils
import com.wtAppStore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(rootView.root)
        NetUtils.updateAllListData()
    }
}