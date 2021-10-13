package com.wtAppStore.common

/**
 * Description: 应用商店数据库字典定义
 * @author GaoYuanZou
 * @time 2021/10/13
 * Amendant record:
 **/
object AppStoreDBConstant {

    /**
     * 数据库名
     */
    const val DB_FILE_NAME = "WTAppStore.db"

    /**
     * 数据库版本
     */
    const val DB_FILE_VER = 1

    //------------------数据库表 列名---------------//
    //---------AppConfigBean 表字段-----------//
    /**
     * AppConfigBean表名
     */
    const val DB_TABLE_APP_CONFIG_BEAN_NAME = "AppConfigBean"

    /**
     * 应用名
     */
    const val DB_APP_CONFIG_BEAN_COL_APP_NAME = "appName"

    /**
     * 应用包名
     */
    const val DB_APP_CONFIG_BEAN_COL_APP_PKG_NAME = "appPkgName"

    /**
     * 应用图标地址
     */
    const val DB_APP_CONFIG_BEAN_COL_APP_ICON_URL = "appIconUrl"

    /**
     * 应用说明
     */
    const val DB_APP_CONFIG_BEAN_COL_APP_DESC = "appDescription"

    /**
     * 应用下载地址
     */
    const val DB_APP_CONFIG_BEAN_COL_DOWNLOAD_URL = "downloadUrl"

    /**
     * 应用是否已经安装
     */
    const val DB_APP_CONFIG_BEAN_COL_INSTALL = "install"

}