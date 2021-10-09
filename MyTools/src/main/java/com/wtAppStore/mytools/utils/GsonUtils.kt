package com.wtAppStore.mytools.utils

import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * Description:
 * @author gaoyuanzou
 * @time 2021/09/30
 * Amendant record:
 **/
object GsonUtils {

    private val gson = Gson()

    fun <T> getBean(beanString: String, t: Class<T>): T? = gson.fromJson(beanString, t)

    fun <T> getBean(beanString: String, type: Type): T? = gson.fromJson(beanString, type)

    fun bean2Str(obj: Any): String? = gson.toJson(obj)

}