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
@WebServlet("/UploadSpaceServlet")
public class UploadSpaceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ParkingSpace space = new ParkingSpace();


        /*用户上传的图片保存到虚拟目录里*/
        /*只在数据库里存储图片名*/
        String savePath = "E:/Graduation/IdeaProjects/image";
        File file = new File(savePath);

        /*使用Apache文件上传组件处理文件的上传*/

        /*1、创建一个DiskFileItemFactory工厂*/
        DiskFileItemFactory factory = new DiskFileItemFactory();
        /*2、创建一个文件上传解析器*/
        ServletFileUpload upload = new ServletFileUpload(factory);
        /*解决上传文件名的中文乱码*/
        upload.setHeaderEncoding("UTF-8");
        /*利用解析器解析请求*/
        List<FileItem> list = null;
        try {
            list = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        /*逐个取出请求里的数据*/
        for (FileItem item : list) {
            /*如果fileitem中封装的是普通输入项的数据*/
            if (item.isFormField()) {
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

                System.out.println(name + "=" + value);
            } else    /*如果fileitem中封装的是上传文件*/ {

                String filename = item.getName();
                space.setImagePath(filename);
                System.out.println(filename);
//                if (filename == null || filename.trim().equals("")) {
//                    continue;
//                }
//                //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
//                //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
//                filename = filename.substring(filename.lastIndexOf("\\") + 1);
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


        ParkingSpaceDao dao = new ParkingSpaceDao();
        int result = dao.addSpace(space);
        System.out.println(result);

        //把结果写回客户端
        String responseMessage;
        if (result == 1) {
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


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

}
