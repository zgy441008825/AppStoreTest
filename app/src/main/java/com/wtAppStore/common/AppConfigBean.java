package com.wtAppStore.common;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Description:
 * 从服务器拉取的APPListItem实例
 *
 * @author GaoYuanZou
 * @time 2021/10/09
 * Amendant record:
 **/
public class AppConfigBean implements Serializable {

    private String appName;

    private String appPkgName;

    private String appIconUrl;

    private String appDescription;

    private String downloadUrl;

    /**
     * 当前应用是否安装
     */
    @Expose(serialize = false, deserialize = false)
    private boolean install;

}
