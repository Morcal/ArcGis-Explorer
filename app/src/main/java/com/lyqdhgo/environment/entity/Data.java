package com.lyqdhgo.environment.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by QiDeHong on 2017/5/10.
 */

public class Data extends RealmObject {
    @PrimaryKey
    private String id;

    private String oneData;
    private String twoData;

    public String getOneData() {
        return oneData;
    }

    public void setOneData(String oneData) {
        this.oneData = oneData;
    }

    public String getTwoData() {
        return twoData;
    }

    public void setTwoData(String twoData) {
        this.twoData = twoData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
