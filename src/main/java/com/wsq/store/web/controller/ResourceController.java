package com.wsq.store.web.controller;

import com.wsq.store.common.domain.base.ResponseResult;
import com.wsq.store.web.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 在上传文件后 将相对路径保存下来到数据库 只提供前端主键id
 */
@Controller
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    @PostMapping(value = "/fileUpload")
    public ResponseResult<?> fileUpload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        try {
            return ResponseResult.success(resourceService.upload(file));
        }catch (Exception e){
            return ResponseResult.fail(-100,"");
        }
    }

    public void downloadFile(HttpServletRequest request, HttpServletResponse response){
        resourceService.downloadFile(request,response);
    }
}
