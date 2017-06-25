package com.lyqdhgo.environment.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by QiDeHong on 2017/6/24.
 */
@Entity
public class UserVo {
    private String id;
    private String loginName;// 登录名
    private String password;// 密码
    private String name;    // 姓名
    private String email;    // 邮箱
    private String phone;    // 电话
    private String mobile;    // 手机
    @Generated(hash = 650142004)
    public UserVo(String id, String loginName, String password, String name,
            String email, String phone, String mobile) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
    }
    @Generated(hash = 1967506689)
    public UserVo() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getLoginName() {
        return this.loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

   
}
