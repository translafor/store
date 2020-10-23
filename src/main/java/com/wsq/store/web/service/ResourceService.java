package com.wsq.store.web.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * 问题：1.如何在本地保存文件 并配置资源映射  2.扩展：利用nginx做服务器？
 * 参考：https://blog.csdn.net/qq_38762237/article/details/81282444
 * https://www.cnblogs.com/somegenki/p/13369282.html
 *https://blog.csdn.net/joeyon1985/article/details/46624079?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param
 * https://blog.csdn.net/dkbnull/article/details/88858717：自定义文件流（不依赖前端测试）
 */
@Service
public class ResourceService {

    /**
     * 现阶段只是测试 需要将路径存到表内 只暴露id，更后面需要尝试搭建文件服务器，而不要用本地（fds）
     * @param file
     * @return
     */
    public Object upload(MultipartFile file) {
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }
        try {
            byte[] stream = file.getBytes();
            int offset = 0;
            int length = stream.length;
            //后缀名
            String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            //如果不存在则获取该文件夹
            String dirs = String.format("%s\\%s",System.getProperty("user.dir"),"files");
            File newDirs = new File(dirs);
            if(!newDirs.isDirectory()){
                newDirs.mkdirs();
            }
            //不存在则创建该文件
            String filePath=String.format("%s\\%s",dirs,UUID.randomUUID()+extension);
            File newFile = new File(filePath);
            if(null==file){
                newFile.createNewFile();
            }
            //向文件写入数据
            FileOutputStream outputStream=new FileOutputStream(newFile);
            outputStream.write(stream,offset,length);
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //https://blog.csdn.net/xiongyouqiang/article/details/80439883
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = request.getParameter("fileName");
        String dirs = String.format("%s\\%s",System.getProperty("user.dir"),"files");
        String filePath=String.format("%s\\%s",dirs,fileName);
        FileInputStream inputStream = null;
        BufferedInputStream bis = null;
        File file =new File(filePath);
        try{
            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            inputStream = new FileInputStream(file);
            bis = new BufferedInputStream(inputStream);
            OutputStream outputStream= response.getOutputStream();
            //文件下载 --分块（1024）
            byte[] buffer=new byte[1024];
            int i=bis.read(buffer);
            while(i!=-1){
                outputStream.write(buffer,0,i);
                i=bis.read(buffer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
