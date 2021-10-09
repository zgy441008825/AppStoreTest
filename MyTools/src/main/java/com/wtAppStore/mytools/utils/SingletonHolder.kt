package com.wtAppStore.mytools.utils

/**
 * Description: 带参数的单利模式范例
 * @author gaoyuanzou
 * @time 2021/09/30
 * Amendant record:
 **/
open class SingletonHolder<out T, in A>(creator: (A) -> T) {

    private var creator: ((A) -> T)? = creator

    @Volatile
    private var instance: T? = null

    //对上述方法的一种更简洁的写法
    fun getInstance(arg: A): T =
        instance ?: synchronized(this) {
            instance ?: creator!!(arg).apply {
                instance = this
            }
        }

}