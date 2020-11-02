package servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import test.*;
import util.ReadExcel;
import util.ReadExcelForWeb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;


public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("aaaaaaaaaaaaa");

        //允许上传的文件类型
        String fileType = "xls,xlsx";
        //允许上传的文件最大大小(100M,单位为byte)
        int maxSize = 1024*1024*100;
        response.addHeader("Access-Control-Allow-Origin", "*");
        //文件要保存的路径
        String savePath = request.getRealPath("/") +"parseExcel/";
        response.setContentType("text/html; charset=UTF-8");
        //检查目录
        File uploadDir = new File(savePath);
        if ( !uploadDir.exists())
        {
            uploadDir.mkdirs();
        }
        if ( !uploadDir.canWrite())
        {
            System.out.println("上传目录没有写权限！");
            /*return "上传目录没有写权限！";*/
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //设置缓冲区大小，这里是1M
        factory.setSizeThreshold(1024 * 1024);
        //设置缓冲区目录
        factory.setRepository(uploadDir);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        List<FileItem>items = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        Iterator<FileItem> it = items.iterator();
        FileItem item = null;
        String fileName = "";
        String name = "";
        String extName = "";
        String newFileName = "";
        System.out.println("cccccccccccccccccccccccccccccccccc-------------------------");
        while (it.hasNext())
        {
            item = (FileItem)it.next();
            fileName = item.getName();
            if (null == fileName || "".equals(fileName))
            {
                continue;
            }
            //判断文件大小是否超限
            if (item.getSize() > maxSize)
            {
                item.delete();
                JOptionPane.showMessageDialog(null, "文件大小超过限制！应小于" + maxSize/ 1024 / 1024 + "M");
                System.out.println("文件大小超过限制！应小于");
                /*return "文件大小超过限制！应小于" + maxSize;*/
            }
            //获取文件名称
            name = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.lastIndexOf("."));
            //获取文件后缀名
            extName = fileName.substring(fileName.indexOf(".") + 1).toLowerCase().trim();

            //判断是否为允许上传的文件类型
            if ( !Arrays.<String> asList(fileType.split(",")).contains(extName))
            {
                item.delete();
                System.out.println("文件类型不正确，必须为"+ fileType + "的文件！");
                /*return "文件类型不正确，必须为" + fileType + "的文件！";*/
            }
            newFileName = name  + "." + extName;
            File uploadedFile = new File(savePath, newFileName);

            try {
                item.write(uploadedFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //在这里添加读取Excel的方法来根据路径读取Excel内容
           // ReadExcel.excel();
            try {
                ReadExcelForWeb.excel(uploadedFile);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //删除刚上传的excel
            uploadedFile.delete();
        }
        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbb-------------------------");
        response.sendRedirect("success.jsp");
        //response.getWriter().println("提煤单生成成功");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}