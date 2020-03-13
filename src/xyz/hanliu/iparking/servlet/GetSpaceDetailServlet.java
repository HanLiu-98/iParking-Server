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
@WebServlet("/GetSpaceDetailServlet")
public class GetSpaceDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int id = Integer.valueOf(request.getParameter("id"));
        System.out.println(id);

        ParkingSpaceDao dao = new ParkingSpaceDao();
        ParkingSpace space = dao.findById(id);
        System.out.println(space.toString());


        //把查询到的User转换成JSON字符串

        String responseMessage = JSON.toJSONString(space);
//        Gson gson=new Gson();
//        String responseMessage=gson.toJson(space);
        System.out.println(responseMessage);

        //设置返回数据格式和编码
        response.setContentType("application/json;charset=utf-8");
        //获取响应的输出流，将响应的JSON字符串写出
        PrintWriter out = response.getWriter();
        out.print(responseMessage);
        out.flush();
        out.close();


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
