package xyz.hanliu.test;

import xyz.hanliu.dao.UserDao;
import xyz.hanliu.domain.User;

/**
 * @author HanLiu
 * @create 2020-02-25-16:10
 * @blogip 47.110.70.206
 */
public class UserDaoTest {

    @org.junit.Test
    public void testLogin(){
        User loginuser = new User();
        loginuser.setMobile("17355216002");
        loginuser.setPassword("ahut123");


        UserDao dao = new UserDao();
        User user = dao.login(loginuser);

        System.out.println(user);
    }
}
