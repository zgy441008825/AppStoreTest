package com.wtAppStore.mytools.net

import com.wtAppStore.mytools.utils.GsonUtils
import com.wtAppStore.mytools.utils.Logger
import org.xutils.http.app.ResponseParser
import org.xutils.http.request.UriRequest
import java.lang.reflect.Type

/**
 * Description:
 * 通过Gson解析对应的类型，在使用x.http进行网络请求是可作为回调的泛型传入
 * @author gaoyuanzou
 * @time 2021/09/30
 * Amendant record:
 **/
open class GsonResponseParser<T> : ResponseParser<String> {

    private val TAG = "GsonResponseParser"

    override fun beforeRequest(request: UriRequest?) {
    }

    override fun afterRequest(request: UriRequest?) {
    }

    override fun parse(resultType: Type, resultClass: Class<*>?, result: String): Any? {
        Logger.i(TAG, "parse type:$resultType class:$resultClass result:$result")
        return GsonUtils.getBean(result, resultType)
    }

}