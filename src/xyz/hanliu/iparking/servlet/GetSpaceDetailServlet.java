package xyz.hanliu.iparking.servlet;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import xyz.hanliu.iparking.dao.ParkingSpaceDao;
import xyz.hanliu.iparking.domain.ParkingSpace;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author HanLiu
 * @create 2020-03-11-21:00
 * @blogip 47.110.70.206
 */

/*
 *处理客户端发来的“获取某个车位的详细信息”的请求(select 条件spaceid)
 */
@WebServlet("/GetSpaceDetailServlet")
public class GetSpaceDetailServlet extends HttpServlet {
    /*
     * 处理客户端发来的Post请求
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取id参数，并且进行查询，返回查到的ParkingSpace对象
        int id = Integer.valueOf(request.getParameter("id"));
        ParkingSpaceDao dao = new ParkingSpaceDao();
        ParkingSpace space = dao.findById(id);


        //把查询到的ParkingSpace对象转换成JSON字符串
        String responseMessage = JSON.toJSONString(space);

        //设置返回数据格式和编码
        response.setContentType("application/json;charset=utf-8");
        //获取响应的输出流，将响应的JSON字符串写出
        PrintWriter out = response.getWriter();
        out.print(responseMessage);
        out.flush();
        out.close();
    }

    /*
     * 处理客户端发来的Get请求
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);

    }
}
