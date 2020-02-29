package xyz.hanliu.domain;

/**
 * @author HanLiu
 * @create 2020-02-25-12:55
 * @blogip 47.110.70.206
 */

public class User {
    private String mobile;
    private String nickname;
    private String fullname;
    private String password;

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
