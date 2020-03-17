package xyz.hanliu.iparking.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import xyz.hanliu.iparking.dao.ParkingSpaceDao;
import xyz.hanliu.iparking.domain.ParkingSpace;
import xyz.hanliu.iparking.util.Dateutils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author HanLiu
 * @create 2020-03-09-13:50
 * @blogip 47.110.70.206
 */

/*
 *处理客户端发来的发布车位请求(insert)
 */
@WebServlet("/UploadSpaceServlet")
public class UploadSpaceServlet extends HttpServlet {
    /*
     *处理客户端发来的Post请求
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ParkingSpace space = new ParkingSpace();

        //用户上传的图片保存到虚拟目录里,只在数据库里存储图片名，需要访问图片可以通过URL
        String savePath = "E:/Graduation/IdeaProjects/image";


        /*--------使用Apache文件上传组件(fileupload)处理多类型文件的上传--------*/

        //1、创建一个DiskFileItemFactory工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();

        //2、创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);

        //解决上传文件名的中文乱码
        upload.setHeaderEncoding("UTF-8");

        //3、利用解析器解析请求，返回FileItem类型的List
        List<FileItem> list = null;
        try {
            list = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        //4、逐个取出FileItem类型的List里的数据
        for (FileItem item : list) {
            //如果FileItem中存放的是普通输入项的数据
            if (item.isFormField()) {
                //取出字段名和对应的值
                String name = item.getFieldName();
                String value = item.getString("UTF-8");
                switch (name) {
                    case "area":
                        space.setArea(value);
                        break;
                    case "positionDetail":
                        space.setPositionDetail(value);
                        break;
                    case "startTime":
                        //Date类型是以字符串形式提交的，需要进行解析
                        try {
                            space.setStartTime(Dateutils.strToDate(value));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "endTime":
                        try {
                            space.setEndTime(Dateutils.strToDate(value));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "price":
                        space.setPrice(Float.valueOf(value));
                        break;
                    case "remark":
                        space.setRemark(value);
                        break;
                    case "status":
                        space.setStatus(Integer.valueOf(value));
                        break;
                    case "ownerMobile":
                        space.setOwnerMobile(value);
                        break;
                    default:
                        System.out.println("如果我被执行，就是出错了！");
                }
            }
            //如果FileItem中存放的文件，需要用输入流去读取
            else {
                //上传图片的文件名，已经在客户端指定了（userid+时间戳）
                String filename = item.getName();
                space.setImagePath(filename);

                //获取item中的上传文件的输入流
                InputStream in = item.getInputStream();
                //创建一个文件输出流
                FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
                //创建一个缓冲区
                byte buffer[] = new byte[1024];
                //判断输入流中的数据是否已经读完的标识
                int len = 0;
                //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                while ((len = in.read(buffer)) > 0) {
                    //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                    out.write(buffer, 0, len);
                }
                //关闭输入流
                in.close();
                //关闭输出流
                out.close();
                //删除处理文件上传时生成的临时文件
                item.delete();
            }
        }

        /*--------用户请求参数获取完毕，已经成功封装成ParkingSpace对象--------*/

        //对数据库进行insert操作，添加空闲车位，返回成功操作的条数
        ParkingSpaceDao dao = new ParkingSpaceDao();
        int result = dao.addSpace(space);
        System.out.println(result);

        //把结果写回客户端
        String responseMessage;
        if (result == 1) {
            //如果成功插入，除了返回success，还会传回空闲车位的id，方便查看详情
            int id = dao.findForId(space);
            responseMessage = "success" + "###" + String.valueOf(id);
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
