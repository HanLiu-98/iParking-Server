package xyz.hanliu.iparking.test;

import org.junit.Test;
import xyz.hanliu.iparking.dao.ParkingSpaceDao;
import xyz.hanliu.iparking.dao.UserDao;
import xyz.hanliu.iparking.domain.ParkingSpace;
import xyz.hanliu.iparking.domain.User;
import xyz.hanliu.iparking.util.Dateutils;

import java.text.ParseException;
import java.util.Date;

/**
 * @author HanLiu
 * @create 2020-02-25-16:10
 * @blogip 47.110.70.206
 */
public class UserDaoTest {

    @Test
    public void testLogin() {
        User loginuser = new User();
        loginuser.setMobile("17355216002");
        loginuser.setPassword("ahut12");


        UserDao dao = new UserDao();
        User user = dao.login(loginuser);

        System.out.println(user);
    }

    @Test
    public void testRegister() {
        User registerUser = new User();
        registerUser.setMobile("17355216003");
        registerUser.setPassword("ahut123");
        registerUser.setFullname("张三");
        registerUser.setNickname("小熊维尼");
        UserDao dao = new UserDao();
        int count = dao.register(registerUser);
        System.out.println(count);

    }

    @Test
    public void testResetpwd() {
        User registerUser = new User();
        registerUser.setMobile("17355216004");
        registerUser.setPassword("ahut123");
        registerUser.setFullname("张三");
        registerUser.setNickname("小熊维尼");
        UserDao dao = new UserDao();
        int count = dao.resetpwd(registerUser);
        System.out.println(count);

    }


}
