package xyz.hanliu.iparking.servlet;

import com.alibaba.fastjson.JSON;
import xyz.hanliu.iparking.dao.ParkingSpaceDao;
import xyz.hanliu.iparking.domain.ParkingSpace_General;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author HanLiu
 * @create 2020-03-12-19:58
 * @blogip 47.110.70.206
 */

/*
 *处理客户端发来的“获取一个地区内所有车位列表”请求(select 条件 area)
 */
@WebServlet("/GetSpaceListServlet")
public class GetSpaceListServlet extends HttpServlet {
    /*
     * 处理客户端发来的Post请求
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //解决客户端请求参数的中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获取用户选择的区域
        String area = request.getParameter("area");

        //查询该地区内还未过期，未被出租的车位  (where area=? and status=0 and starttime>now())
        ParkingSpaceDao dao = new ParkingSpaceDao();
        List<ParkingSpace_General> list = dao.findByArea(area);

        //将查询到的List转换成JSON字符串
        String responseMessage;
        responseMessage = JSON.toJSONString(list);

        //设置返回数据格式和编码
        response.setContentType("application/json;charset=utf-8");
        //获取响应的输出流，将响应的字符串写出
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
