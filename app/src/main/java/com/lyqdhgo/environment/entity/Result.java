package com.lyqdhgo.environment.entity;

import java.util.List;

/**
 * Created by QiDeHong on 2017/6/24.
 */

public class Result {
    private boolean success = true;// 是否成功
    private String msg = "操作成功";// 提示信息
    private List<UserVo> obj;

    public List<UserVo> getObj() {
        return obj;
    }

    public void setObj(List<UserVo> obj) {
        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {//向json中添加属性，在js中访问，请调用data.msg
        this.msg = msg;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


}
