package xyz.hanliu.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import xyz.hanliu.domain.User;
import xyz.hanliu.util.JDBCUtils;

/**
 * @author HanLiu
 * @create 2020-02-25-13:43
 * @blogip 47.110.70.206
 */

/**
 * 操作数据库中User表的类
 */
public class UserDao {

    //声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 登录方法
     * @param loginUser 只有手机号和密码
     * @return user包含用户全部数据,没有查询到，返回null
     */
    public User login(User loginUser){
        try {
            //1.编写sql
            String sql = "select * from user where mobile = ? and password = ?";
            //2.调用query方法
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getMobile(), loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

}
