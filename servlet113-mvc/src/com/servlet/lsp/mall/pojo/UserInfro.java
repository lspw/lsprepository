package com.servlet.lsp.mall.pojo;

public class UserInfro {
    private String username;
    private String time;
    private String goodsname;
    private String useraddress;
    private String userphonenumber;

    @Override
    public String toString() {
        return "UserInfro{" +
                "username='" + username + '\'' +
                ", time='" + time + '\'' +
                ", goodsname='" + goodsname + '\'' +
                ", useraddress='" + useraddress + '\'' +
                ", userphonenumber='" + userphonenumber + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public String getUserphonenumber() {
        return userphonenumber;
    }

    public void setUserphonenumber(String userphonenumber) {
        this.userphonenumber = userphonenumber;
    }

    public UserInfro(String username, String time, String goodsname, String useraddress, String userphonenumber) {
        this.username = username;
        this.time = time;
        this.goodsname = goodsname;
        this.useraddress = useraddress;
        this.userphonenumber = userphonenumber;
    }

    public UserInfro() {
    }
}
