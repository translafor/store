package com.wsq.store.web.controller;

import com.wsq.store.StoreApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={StoreApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResourceControllerTest {
    @Autowired
    ResourceController resourceController;

    @Test
    public void logUpload() throws Exception {
//        String url = "http://127.0.0.1:8090/fileUpload";
////        String pathname = new File("logs" + File.separator + "log_20190310.log").getCanonicalPath();
//        String pathname = "C:\\Users\\wushiquan\\Desktop\\test.txt";
//        logUpload(url, pathname);
    }

    private static void logUpload(String url, String pathname) {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);

        try {
            FilePart filePart = new FilePart("file", new File(pathname));
            Part[] parts = {filePart};

            MultipartRequestEntity multipartRequestEntity = new MultipartRequestEntity(parts, new HttpMethodParams());
            postMethod.setRequestEntity(multipartRequestEntity);
            httpClient.executeMethod(postMethod);
            String result = postMethod.getResponseBodyAsString();

            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }
    }
}
