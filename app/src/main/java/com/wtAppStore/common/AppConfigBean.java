package com.wtAppStore.common;

import com.google.gson.annotations.Expose;
import com.wtAppStore.mytools.DB.BaseDBBean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;
import java.util.Objects;

/**
 * Description:
 * 从服务器拉取的APPListItem实例
 *
 * @author GaoYuanZou
 * @time 2021/10/09
 * Amendant record:
 **/
@Table(name = AppStoreDBConstant.DB_TABLE_APP_CONFIG_BEAN_NAME)
public class AppConfigBean extends BaseDBBean {

    /**
     * app名
     */
    @Column(name = AppStoreDBConstant.DB_APP_CONFIG_BEAN_COL_APP_NAME)
    private String appName;

    /**
     * APP包名
     */
    @Column(name = AppStoreDBConstant.DB_APP_CONFIG_BEAN_COL_APP_PKG_NAME)
    private String appPkgName;

    /**
     * APP Icon
     */
    @Column(name = AppStoreDBConstant.DB_APP_CONFIG_BEAN_COL_APP_ICON_URL)
    private String appIconUrl;

    /**
     * 应用说明
     */
    @Column(name = AppStoreDBConstant.DB_APP_CONFIG_BEAN_COL_APP_DESC)
    private String appDescription;

    /**
     * 下载地址
     */
    @Column(name = AppStoreDBConstant.DB_APP_CONFIG_BEAN_COL_DOWNLOAD_URL)
    private String downloadUrl;

    /**
     * 当前应用是否安装
     */
    @Column(name = AppStoreDBConstant.DB_APP_CONFIG_BEAN_COL_INSTALL)
    @Expose(serialize = false, deserialize = false)
    private boolean install;

    public String getAppName() {
        return appName;
    }

    public AppConfigBean setAppName(String appName) {
        this.appName = appName;
        return this;
    }

    public String getAppPkgName() {
        return appPkgName;
    }

    public AppConfigBean setAppPkgName(String appPkgName) {
        this.appPkgName = appPkgName;
        return this;
    }

    public String getAppIconUrl() {
        return appIconUrl;
    }

    public AppConfigBean setAppIconUrl(String appIconUrl) {
        this.appIconUrl = appIconUrl;
        return this;
    }

    public String getAppDescription() {
        return appDescription;
    }

    public AppConfigBean setAppDescription(String appDescription) {
        this.appDescription = appDescription;
        return this;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public AppConfigBean setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
        return this;
    }

    public boolean isInstall() {
        return install;
    }

    public AppConfigBean setInstall(boolean install) {
        this.install = install;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AppConfigBean that = (AppConfigBean) o;
        return Objects.equals(appPkgName, that.appPkgName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appPkgName);
    }

    @Override
    public String toString() {
        return "AppConfigBean{" +
                "appName='" + appName + '\'' +
                ", appPkgName='" + appPkgName + '\'' +
                ", appIconUrl='" + appIconUrl + '\'' +
                ", appDescription='" + appDescription + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", install=" + install +
                '}';
    }
}
