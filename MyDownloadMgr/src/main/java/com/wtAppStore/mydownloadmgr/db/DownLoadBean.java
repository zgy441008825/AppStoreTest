package com.wtAppStore.mydownloadmgr.db;

import com.wtAppStore.mytools.DB.BaseDBBean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.Objects;

/**
 * Description:
 *
 * @author gaoyuanzou
 * @time 2021/09/30
 * Amendant record:
 **/
@Table(name = DBConstant.DB_DL_NAME)
public class DownLoadBean extends BaseDBBean {

    @Column(name = DBConstant.DB_DL_COL_URL)
    private String url;

    @Column(name = DBConstant.DB_DL_COL_SAVE_PATH)
    private String fileSavePath;

    @Column(name = DBConstant.DB_DL_COL_LABEL)
    private String label;

    @Column(name = DBConstant.DB_DL_COL_FILE_SIZE)
    private long fileSize;

    @Column(name = DBConstant.DB_DL_COL_STATE)
    private int downloadState = DBConstant.DownloadState.STATE_WAIT;

    @Column(name = DBConstant.DB_DL_COL_PROGRESS)
    private int progress;

    public String getUrl() {
        return url;
    }

    public DownLoadBean setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getFileSavePath() {
        return fileSavePath;
    }

    public DownLoadBean setFileSavePath(String fileSavePath) {
        this.fileSavePath = fileSavePath;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public DownLoadBean setLabel(String label) {
        this.label = label;
        return this;
    }

    public long getFileSize() {
        return fileSize;
    }

    public DownLoadBean setFileSize(long fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public int getDownloadState() {
        return downloadState;
    }

    public DownLoadBean setDownloadState(int downloadState) {
        this.downloadState = downloadState;
        return this;
    }

    public int getProgress() {
        return progress;
    }

    public DownLoadBean setProgress(int progress) {
        this.progress = progress;
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
        DownLoadBean that = (DownLoadBean) o;
        return Objects.equals(url, that.url) && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, label);
    }

    @Override
    public String toString() {
        return "DownLoadBean{" +
                "url='" + url + '\'' +
                ", fileSavePath='" + fileSavePath + '\'' +
                ", label='" + label + '\'' +
                ", fileSize=" + fileSize +
                ", downloadState=" + downloadState +
                ", progress=" + progress +
                "} ";
    }
}
