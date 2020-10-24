package com.wsq.store.web.service;

import com.wsq.store.common.domain.base.Resource;
import com.wsq.store.common.mapper.ResourceMapper;
import com.wsq.store.web.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
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

    @Autowired
    SnowFlake snowFlake;
    @Autowired
    ResourceMapper resourceMapper;
    /**
     * 现阶段只是测试 需要将路径存到表内 只暴露id，更后面需要尝试搭建文件服务器，而不要用本地（fds）
     * @param file
     * @return
     */
    public Map upload(MultipartFile file) {
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
            String fileName = UUID.randomUUID()+extension;
            //不存在则创建该文件
            String filePath=String.format("%s\\%s",dirs,fileName);
            File newFile = new File(filePath);
            if(null==file){
                newFile.createNewFile();
            }
            //向文件写入数据
            FileOutputStream outputStream=new FileOutputStream(newFile);
            outputStream.write(stream,offset,length);
            outputStream.close();

            //插入关联表
            Resource resource = insertResource(fileName,String.format("\\%s",fileName),length);
            resourceMapper.insertSelective(resource);

            Map<String,Long> res = new HashMap<>();
            res.put("resourceId",resource.getFId());
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 插入resource表
     * @param fileName
     * @param filePath
     * @param length
     */
    private Resource insertResource(String fileName, String filePath, int length) {
        Resource resource = new Resource();
        resource.setFId(snowFlake.nextId());
        resource.setFFileName(fileName);
        resource.setFFilePath(filePath);
        resource.setFLength(length);
        return resource;
    }



    /**
     * //https://blog.csdn.net/xiongyouqiang/article/details/80439883
     * 下载文件
     * @param request
     * @param response
     */
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) {

        Long resourceId = Long.valueOf(request.getParameter("resourceId"));
        Resource resource = resourceMapper.selectByPrimaryKey(resourceId);

        String filePath = resource.getFFilePath();
        String fileName = resource.getFFileName();

        //文件目录和文件全路径
        String dirs = String.format("%s\\%s",System.getProperty("user.dir"),"files");
        String fileAllPath=String.format("%s%s",dirs,filePath);

        FileInputStream inputStream = null;
        BufferedInputStream bis = null;
        File file =new File(fileAllPath);
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
