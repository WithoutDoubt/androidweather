package com.example.admin.androidweather.gson;

import java.util.List;

public class SessionGson {



    private String name;

    private String loginName;
    private String no;
    private String email;
    private String mobile;


    private String sessionid;
    private String phone;
    private String roleName;

    //用户列表
    private List<UserMenu> userMenu;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //列表
    public List<UserMenu> getUserMenu() {
        return userMenu;
    }

    public void setUserMenu(List<UserMenu> userMenu) {
        this.userMenu = userMenu;
    }
    //


    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


}
