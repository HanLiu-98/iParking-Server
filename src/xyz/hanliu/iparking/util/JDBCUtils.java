package xyz.hanliu.iparking.util;

/**
 * @author HanLiu
 * @create 2020-02-25-13:24
 * @blogip 47.110.70.206
 */

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mysql.jdbc.Connection;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC工具类 使用Durid数据库连接池
 */
public class JDBCUtils {

    private static DataSource ds ;

    /**
     * 静态代码块，加载配置文件初始化数据库连接对象
     */
    static {
        try {
            //1.加载配置文件
            Properties pro = new Properties();
            //使用ClassLoader加载配置文件，获取字节输入流
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);
            //2.初始化连接池对象
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取连接池对象
     */
    public static DataSource getDataSource(){
        return ds;
    }


    /**
     * 获取连接Connection对象
     */
    public static Connection getConnection() throws SQLException {
        return (Connection) ds.getConnection();
    }
}
