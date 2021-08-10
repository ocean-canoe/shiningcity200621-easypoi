/**
 * 
 */
package com.shiningcity.testHttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import javassist.expr.NewArray;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
@RestController
@RequestMapping("/httpClient")
public class HttpClientController {

    @RequestMapping("/sendClient")
    public String sendClient(HttpServletRequest request,HttpServletResponse response) {
        log.info("======== sendClient ========");
        // 
        Map<String, String> getParam = new HashMap<>();
        getParam.put("getParam1", "1");
        getParam.put("getParam2", "true");
        Map<String, String> postParam = new HashMap<>();
        postParam.put("postParam1", "1");
        postParam.put("postParam2", "true");
        String url = "http://127.0.0.1:8082/easypoi/httpClient/receiveClient";
        String resultString = HttpClientUtil.doPost(url, null, getParam, postParam);
        log.info("sendClient resultString = {}",resultString);
        return resultString;
    }

    @RequestMapping("/sendFileClient")
    public String sendFileClient(HttpServletRequest request,HttpServletResponse response) {
        log.info("======== sendFileClient ========");
        // 获得jar包根目录
        ApplicationHome home = new ApplicationHome(getClass());
        File jarF = home.getSource();
        String dirPath = jarF.getParentFile().toString()+"/csvTemp";
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        // 获得文件夹下所有文件
        File[] listFiles = new File(dirPath).listFiles();
        // 获得文件夹下某个文件
        File oneFile = new File(dirPath+"/filename_5457496707332029853.csv");
        
        //
        Map<String, String> getParam = new HashMap<>();
        getParam.put("getParam1", "1");
        getParam.put("getParam2", "true");
        Map<String, String> postParam = new HashMap<>();
        postParam.put("postParam1", "1");
        postParam.put("postParam2", "true");
        Map<String, File> fileParam = new HashMap<>();
        fileParam.put("multipartFile", oneFile);
        String url = "http://127.0.0.1:8082/easypoi/httpClient/receiveMultipartFileClient";
        String resultString = HttpClientUtil.doPostFileWithUrlParam(url, null, getParam, postParam, fileParam);
        log.info("sendFileClient resultString = {}",resultString);
        return resultString;
    }

    @RequestMapping("/sendMultipartFileClient")
    public String sendMultipartFileClient(HttpServletRequest request,HttpServletResponse response,MultipartFile multipartFile) {
        log.info("======== sendMultipartFileClient ========");
        Map<String, String> getParam = new HashMap<>();
        getParam.put("getParam1", "2");
        getParam.put("getParam2", "true");
        Map<String, String> postParam = new HashMap<>();
        postParam.put("postParam1", "2");
        postParam.put("postParam2", "true");
        Map<String, MultipartFile> multipartFileParam = new HashMap<>();
        multipartFileParam.put("multipartFile", multipartFile);
        String url = "http://127.0.0.1:8082/easypoi/httpClient/receiveMultipartFileClient";
        String resultString = HttpClientUtil.doPostMultipartFileWithUrlParam(url, null, getParam, postParam, multipartFileParam);
        log.info("sendMultipartFileClient resultString = {}",resultString);
        return resultString;
    }

    @RequestMapping("/receiveClient")
    public String receiveClient(HttpServletRequest request,HttpServletResponse response,Integer postParam1,Boolean postParam2) {
        log.info("======== receiveClient ========");
        String getParam1 = request.getParameter("getParam1");
        String getParam2 = request.getParameter("getParam2");
        log.info("getParam1 = {}, getParam2 = {}",getParam1,getParam2);
        log.info("postParam1 = {}, postParam2 = {}",postParam1,postParam2);
        return "receiveClient success";
    }
    
    @RequestMapping("/receiveMultipartFileClient")
    public String receiveMultipartFileClient(HttpServletRequest request,HttpServletResponse response,Integer postParam1,Boolean postParam2,MultipartFile multipartFile) {
        log.info("======== receiveMultipartFileClient ========");
        String getParam1 = request.getParameter("getParam1");
        String getParam2 = request.getParameter("getParam2");
        log.info("getParam1 = {}, getParam2 = {}",getParam1,getParam2);
        log.info("postParam1 = {}, postParam2 = {}",postParam1,postParam2);
        try {
            if (postParam1 == 1) {
                byte[] bytes = multipartFile.getBytes();
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                String filename = format.format(new Date())+".csv";
                File file = new File("D:\\Java_Projects_Deployment_Folder\\easypoi\\files\\"+filename);
                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.write(bytes);
                outputStream.close();
                log.info("multipartFile = {}",multipartFile.getName());
            } else if (postParam1 == 2) {
                byte[] bytes = multipartFile.getBytes();
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                String filename = format.format(new Date())+".jpg";
                File file = new File("D:\\Java_Projects_Deployment_Folder\\easypoi\\files\\"+filename);
                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.write(bytes);
                outputStream.close();
                log.info("multipartFile = {}",multipartFile.getName());
            }
        } catch (IOException e) {
            log.error("IOException",e);
        }
        return "receiveMultipartFileClient success";
    }
    
//    public File getFileFromJarRootDir() {
//        
//    }
}
