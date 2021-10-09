package com.wtAppStore.mydownloadmgr.db

/**
 * Description:
 * @author gaoyuanzou
 * @time 2021/09/30
 * Amendant record:
 **/
object DBConstant {

    //**************数据库相关内容******************/

    /**
     * 数据库名称
     */
    const val DB_FILE_NAME = "WTDownLoadDbFile.db"

    /**
     * 数据库版本
     */
    const val DB_VERSION = 1

    //------------------数据库表 列名---------------//
    /**
     * 下载表:表名
     */
    const val DB_DL_NAME = "downLoad"

    /**
     * 下载表:URL
     */
    const val DB_DL_COL_URL = "fileUrl"

    /**
     * 下载表:保存路径
     */
    const val DB_DL_COL_SAVE_PATH = "fileSavePath"

    /**
     * 下载表:唯一标签
     */
    const val DB_DL_COL_LABEL = "label"

    /**
     * 下载表:文件大小
     */
    const val DB_DL_COL_FILE_SIZE = "fileSize"

    /**
     * 下载表:下载状态
     */
    const val DB_DL_COL_STATE = "downloadState"

    /**
     * 下载表:下载进度
     */
    const val DB_DL_COL_PROGRESS = "progress"

    /**
     * 下载状态
     */
    object DownloadState {

        /**
         * 等待中
         */
        const val STATE_WAIT = 0

        /**
         * 下载中
         */
        const val STATE_LOADING = 1

        /**
         * 下载错误
         */
        const val STATE_ERROR = 2

        /**
         * 下载完成
         */
        const val STATE_FINISH = 3

    }


}