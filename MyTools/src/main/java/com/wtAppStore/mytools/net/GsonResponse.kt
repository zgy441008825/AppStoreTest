package com.wtAppStore.mytools.net

import org.xutils.http.annotation.HttpResponse

/**
 * Description:
 * @author gaoyuanzou
 * @time 2021/09/30
 * Amendant record:
 **/
@HttpResponse(parser = GsonResponseParser::class)
class GsonResponse<T> {
    var t: T? = null
        get() = field
        set(value) {
            field = value
        }

    override fun toString(): String {
        return "GsonResponse(t=$t)"
    }


}