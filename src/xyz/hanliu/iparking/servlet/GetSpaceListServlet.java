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
@WebServlet("/GetSpaceListServlet")
public class GetSpaceListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String area = request.getParameter("area");
        System.out.println(area);
        System.out.println(request.getParameter("pk"));
        ParkingSpaceDao dao = new ParkingSpaceDao();
        List<ParkingSpace_General> list = dao.findByArea(area);

        //把结果写回客户端
        String responseMessage;
        responseMessage = JSON.toJSONString(list);


//        if(result==1)
//        {
//            responseMessage="success";
//        }else{
//            responseMessage="failure";
//        }
        //设置返回数据格式和编码
        response.setContentType("application/json;charset=utf-8");
        //获取响应的输出流，将响应的字符串写出
        PrintWriter out = response.getWriter();
        out.print(responseMessage);
        out.flush();
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
