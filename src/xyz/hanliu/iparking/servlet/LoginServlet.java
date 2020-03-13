package xyz.hanliu.iparking.servlet;

import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;
import xyz.hanliu.iparking.domain.User;
import xyz.hanliu.iparking.dao.UserDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author HanLiu
 * @create 2020-02-25-13:55
 * @blogip 47.110.70.206
 */

/*
 *处理客户端发来的Get请求
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends javax.servlet.http.HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        //获取请求参数，并且使用BeanUtils封装成User对象
        Map<String, String[]> map = request.getParameterMap();

        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用UserDao的login方法，进行数据库操作，返回查询到的User
        UserDao dao = new UserDao();
        User user = dao.login(loginUser);

        //把查询到的User转换成JSON字符串
        Gson gson=new Gson();
        String responseMessage=gson.toJson(user);

        //设置返回数据格式和编码
        response.setContentType("application/json;charset=utf-8");
        //获取响应的输出流，将响应的JSON字符串写出
        PrintWriter out = response.getWriter();
        out.print(responseMessage);
        out.flush();
        out.close();

    }


    /*
     *处理客户端发来的Get请求
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {

            this.doPost(request, response);
    }
}

