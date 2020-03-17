package xyz.hanliu.iparking.domain;

/**
 * @author HanLiu
 * @create 2020-02-25-12:55
 * @blogip 47.110.70.206
 */

/*
 * 平台用户实体类
 */
public class User {
    private String mobile;
    private String nickname;
    private String fullname;
    private String password;

    /*
     * getter方法
     */
    public String getMobile() {
        return mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPassword() {
        return password;
    }

    /*
     * setter方法
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*
     *toString方法
     */
    @Override
    public String toString() {
        return "User{" +
                "mobile='" + mobile + '\'' +
                ", nickname='" + nickname + '\'' +
                ", fullname='" + fullname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
