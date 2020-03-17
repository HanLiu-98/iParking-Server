package xyz.hanliu.iparking.servlet;

import org.apache.commons.beanutils.BeanUtils;
import xyz.hanliu.iparking.dao.UserDao;
import xyz.hanliu.iparking.domain.User;

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
 *处理客户端发来的注册用户请求(insert)
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        //获取请求参数，并且使用BeanUtils封装成User对象
        Map<String, String[]> map = request.getParameterMap();
        User registerUser = new User();
        try {
            BeanUtils.populate(registerUser, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用UserDao的register方法，进行数据库操作，对user表进行插入操作，返回成功操作的条数
        UserDao dao = new UserDao();
        int result = dao.register(registerUser);

        //把结果写回客户端
        String responseMessage;
        if (result == 1) {
            responseMessage = "success";
        } else {
            responseMessage = "failure";
        }

        //设置返回数据格式和编码
        response.setContentType("application/json;charset=utf-8");
        //获取响应的输出流，将响应的字符串写出
        PrintWriter out = response.getWriter();
        out.print(responseMessage);
        out.flush();
        out.close();
    }


    /*
     *处理客户端发来的Get请求
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doPost(request, response);
    }
}

