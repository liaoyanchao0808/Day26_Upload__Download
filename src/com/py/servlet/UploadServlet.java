package com.py.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            upload2(req, resp);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    private void upload2(HttpServletRequest req, HttpServletResponse resp) throws FileUploadException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> list = upload.parseRequest(req);
        for(FileItem fItem :list){
          if(!fItem.isFormField()){
              InputStream is = fItem.getInputStream();
              String path = getServletContext().getRealPath("images");
              File file = new File(path);
              if(!file.exists()){
                  file.mkdir();
              }
              FileOutputStream fos = new FileOutputStream(new File(path, fItem.getName()));
              IOUtils.copy(is,fos);
              fos.close();
              is.close();



          }
        }


    }


    // 1.把文件上传到本地磁盘中
    private void upload1(HttpServletRequest req, HttpServletResponse resp) throws FileUploadException, IOException {
        // 1.创建一个磁盘工厂对象
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 2.通过工厂创建上传对象
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 3.通过上传对象解析请求
        List<FileItem> list = upload.parseRequest(req);
        // 4.遍历集合
        for (FileItem fItem: list)
        {
            // 5.判断是否为非文本文件
            if(!fItem.isFormField())
            {
                // 6.通过请求获取输入流
                InputStream is = fItem.getInputStream();
                // 7.创建输出流--指定保存路径
                FileOutputStream fos = new FileOutputStream("D:\\File\\" + fItem.getName());
                // 8.读写操作--拷贝文件
                byte[] b = new byte[1024];
                int len = 0;
                while ((len = is.read(b)) != -1)
                {
                    fos.write(b,0,len);
                }
                // 9.关闭资源
                fos.close();
                is.close();
            }
        }
    }



}
