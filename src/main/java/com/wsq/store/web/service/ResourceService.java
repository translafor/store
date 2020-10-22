package com.wsq.store.web.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 问题：1.如何在本地保存文件 并配置资源映射  2.扩展：利用nginx做服务器？
 * 参考：https://blog.csdn.net/qq_38762237/article/details/81282444
 * https://www.cnblogs.com/somegenki/p/13369282.html
 *https://blog.csdn.net/joeyon1985/article/details/46624079?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param
 * https://blog.csdn.net/dkbnull/article/details/88858717：自定义文件流（不依赖前端测试）
 */
@Service
public class ResourceService {
    public Object upload(MultipartFile file) {
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }
        try {
            byte[] stream = file.getBytes();
            int offset = 0;
            int length = stream.length;
            String path=System.getProperty("user.dir");
            File newFile = new File(path);
            if(null==file){
                newFile.createNewFile();
            }
            FileOutputStream outputStream=new FileOutputStream(newFile);
            outputStream.write(stream,offset,length);
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
