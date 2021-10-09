package com.wtAppStore.mytools.DB;

import static com.wtAppStore.mytools.DB.BaseDBBeanConstant.DB_COLUMN_ID;

import org.xutils.db.annotation.Column;

import java.io.Serializable;

/**
 * Description:
 *
 * @author gaoyuanzou
 * @time 2021/09/30
 * Amendant record:
 **/
public class BaseDBBean implements Serializable {

    @Column(name = DB_COLUMN_ID, isId = true)
    private int id = 0;

    public int getId() {
        return id;
    }

    public BaseDBBean setId(int id) {
        this.id = id;
        return this;
    }
}
