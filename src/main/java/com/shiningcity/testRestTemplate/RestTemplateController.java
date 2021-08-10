/**
 * 
 */
package com.shiningcity.testRestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.shiningcity.entity.StudentEntity;

import javassist.expr.NewArray;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
@RestController
@RequestMapping("/restTemplate")
public class RestTemplateController {

    @Autowired
    private RestTemplate commonRestTemplate;
    
    @RequestMapping("/getInfoClient")
    public StudentEntity getInfoClient(HttpServletRequest request,HttpServletResponse response) {
        log.info("======== getInfoClient ========");
        String url = "http://127.0.0.1:8082/easypoi/receiveRest/returnInfoServer/{pathParam1}/{pathParam2}?getParam1=1&getParam2=true";
        // 方法一
//        Map<String, Object> pathParam = new HashMap<>();
//        pathParam.put("pathParam1", 1);
//        pathParam.put("pathParam2", true);
//        ResponseEntity<StudentEntity> responseEntity = commonRestTemplate.getForEntity(url, StudentEntity.class, pathParam);
        // 方法二
        ResponseEntity<StudentEntity> responseEntity = commonRestTemplate.getForEntity(url, StudentEntity.class, 1, true);
        // 方法三
        //StudentEntity body = commonRestTemplate.getForObject(url, StudentEntity.class, 1, true);
        StudentEntity body = responseEntity.getBody();
        log.info("sendClient responseEntity = {}",JSON.toJSON(responseEntity));
        log.info("sendClient body = {}",JSON.toJSON(body));
        return body;
    }

    @RequestMapping("/getListClient")
    public List getListClient(HttpServletRequest request,HttpServletResponse response) {
        log.info("======== getListClient ========");
        String url = "http://127.0.0.1:8082/easypoi/receiveRest/returnListServer/{pathParam1}/{pathParam2}?getParam1=1&getParam2=true";
        // 方法一
//      Map<String, Object> pathParam = new HashMap<>();
//      pathParam.put("pathParam1", 1);
//      pathParam.put("pathParam2", true);
//      ResponseEntity<StudentEntity> responseEntity = commonRestTemplate.getForEntity(url, StudentEntity.class, pathParam);
        // 方法二
        ResponseEntity<List> responseEntity = commonRestTemplate.getForEntity(url, List.class, 1, true);
        List resultList = responseEntity.getBody();
        // 方法三
        //List resultList = commonRestTemplate.getForObject(url, List.class, 1, true);
        log.info("sendClient responseEntity = {}",JSON.toJSON(responseEntity));
        //log.info("sendClient body = {}",JSON.toJSON(body));
        return resultList;
    }
    
    @RequestMapping("/postInfoClient")
    public StudentEntity postInfoClient(HttpServletRequest request,HttpServletResponse response) {
        log.info("======== postInfoClient ========");
        String url = "http://127.0.0.1:8082/easypoi/receiveRest/returnInfoServer/{pathParam1}/{pathParam2}?getParam1=1&getParam2=true";
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);// application/x-www-form-urlencoded
        // 请求参数
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("postParam1", 1);
        map.add("postParam2", true);
        // 包装请求头和参数
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(map,headers);
        // 发送请求
        ResponseEntity<StudentEntity> responseEntity = commonRestTemplate.postForEntity(url, httpEntity, StudentEntity.class, 1, true);
        StudentEntity body = responseEntity.getBody();
        log.info("sendClient responseEntity = {}",JSON.toJSON(responseEntity));
        log.info("sendClient body = {}",JSON.toJSON(body));
        StudentEntity studentEntity = commonRestTemplate.postForObject(url, httpEntity, StudentEntity.class, 1, true);
        log.info("sendClient studentEntity = {}",JSON.toJSON(studentEntity));
        return body;
    }
    
    @RequestMapping("/postBodyClient")
    public List postBodyClient(HttpServletRequest request,HttpServletResponse response) {
        log.info("======== postBodyClient ========");
        String url = "http://127.0.0.1:8082/easypoi/receiveRest/receiveBodyServer/{pathParam1}/{pathParam2}?getParam1=1&getParam2=true";
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);// application/json
        // 请求参数
        StudentEntity s1 = new StudentEntity();
        s1.setName("张三");
        s1.setSex(1);
        s1.setBirthday(new Date());
        s1.setRegistrationDate(new Date());
        // 包装请求头和参数
        HttpEntity<String> httpEntity = new HttpEntity<>(JSON.toJSONString(s1),headers);
        // 发送请求
        ResponseEntity<List> responseEntity = commonRestTemplate.postForEntity(url, httpEntity, List.class, 1, true);
        List body = responseEntity.getBody();
        log.info("sendClient responseEntity = {}",JSON.toJSON(responseEntity));
        log.info("sendClient body = {}",JSON.toJSON(body));
        List studentList = commonRestTemplate.postForObject(url, httpEntity, List.class, 1, true);
        log.info("sendClient studentList = {}",JSON.toJSON(studentList));
        return body;
    }

    @RequestMapping("/sendFileClient")
    public String sendFileClient(HttpServletRequest request,HttpServletResponse response) {
        log.info("======== sendFileClient ========");
        String url = "http://127.0.0.1:8082/easypoi/receiveRest/receiveMultipartFileServer/{pathParam1}/{pathParam2}?getParam1=1&getParam2=true";
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);// multipart/form-data
        // 获得jar包根目录
        ApplicationHome home = new ApplicationHome(getClass());
        File jarF = home.getSource();
        String dirPath = jarF.getParentFile().toString()+"/csvTemp";
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        // 获得文件夹下所有文件
        //File[] listFiles = new File(dirPath).listFiles();
        // 获得文件夹下某个文件
        File file = new File(dirPath+"/filename_5457496707332029853.csv");
        // 请求参数
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("postParam1", 1);
        map.add("postParam2", true);
        map.add("multipartFile", fileSystemResource);
        // 包装请求头和参数
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(map,headers);
        // 发送请求
        ResponseEntity<String> responseEntity = commonRestTemplate.postForEntity(url, httpEntity, String.class, 1, true);
        String body = responseEntity.getBody();
        log.info("sendClient responseEntity = {}",JSON.toJSON(responseEntity));
        log.info("sendClient body = {}",JSON.toJSON(body));
        return body;
    }

    // 我不推荐这样用，对文件长度有限制
    @RequestMapping("/sendFileByteClient")
    public String sendFileByteClient(HttpServletRequest request,HttpServletResponse response) {
        log.info("======== sendFileByteClient ========");
        String url = "http://127.0.0.1:8082/easypoi/receiveRest/receiveMultipartFileServer/{pathParam1}/{pathParam2}?getParam1=1&getParam2=true";
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);// multipart/form-data
        // 获得jar包根目录
        ApplicationHome home = new ApplicationHome(getClass());
        File jarF = home.getSource();
        String dirPath = jarF.getParentFile().toString()+"/csvTemp";
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        // 获得文件夹下所有文件
        //File[] listFiles = new File(dirPath).listFiles();
        // 获得文件夹下某个文件
        File file = new File(dirPath+"/filename_5457496707332029853.csv");
        // 请求参数
        long length = file.length();
        byte[] bytes = null;
        if (length < Integer.MAX_VALUE) {
            bytes = new byte[(int) length];
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(bytes);
            } catch (FileNotFoundException e) {
                log.error("FileNotFoundException",e);
            } catch (IOException e) {
                log.error("IOException",e);
            }
        }
        if (null == bytes) {
            return "false";
        }
        ByteArrayResource byteArrayResource = new ByteArrayResource(bytes) {
            @Override
            public String getFilename() {
                // 这个方法需要重写
                return file.getName();
            }
        };
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("postParam1", 1);
        map.add("postParam2", true);
        map.add("multipartFile", byteArrayResource);
        // 包装请求头和参数
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(map,headers);
        // 发送请求
        ResponseEntity<String> responseEntity = commonRestTemplate.postForEntity(url, httpEntity, String.class, 1, true);
        String body = responseEntity.getBody();
        log.info("sendClient responseEntity = {}",JSON.toJSON(responseEntity));
        log.info("sendClient body = {}",JSON.toJSON(body));
        return body;
    }

    @RequestMapping("/sendMultipartFileClient")
    public String sendMultipartFileClient(HttpServletRequest request,HttpServletResponse response,MultipartFile multipartFile) {
        log.info("======== sendMultipartFileClient ========");
        String url = "http://127.0.0.1:8082/easypoi/receiveRest/receiveMultipartFileServer/{pathParam1}/{pathParam2}?getParam1=1&getParam2=true";
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);// multipart/form-data
        // 请求参数
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            log.error("IOException",e);
        }
        if (null == inputStream) {
            return "false";
        }
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream) {
            @Override
            public long contentLength() throws IOException {
                // 这个方法需要重写，否则无法正确上传文件；原因在于父类是通过读取流数据来计算大小
                return multipartFile.getInputStream().available();
            }
            @Override
            public String getFilename() {
                // 这个方法需要重写
                return multipartFile.getName();
            }
        };
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("postParam1", 1);
        map.add("postParam2", true);
        map.add("multipartFile", inputStreamResource);
        // 包装请求头和参数
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(map,headers);
        // 发送请求
        ResponseEntity<String> responseEntity = commonRestTemplate.postForEntity(url, httpEntity, String.class, 1, true);
        String body = responseEntity.getBody();
        log.info("sendClient responseEntity = {}",JSON.toJSON(responseEntity));
        log.info("sendClient body = {}",JSON.toJSON(body));
        return body;
    }

    @RequestMapping("/sendMultipartFileByteClient")
    public String sendMultipartFileByteClient(HttpServletRequest request,HttpServletResponse response,MultipartFile multipartFile) {
        log.info("======== sendMultipartFileByteClient ========");
        String url = "http://127.0.0.1:8082/easypoi/receiveRest/receiveMultipartFileServer/{pathParam1}/{pathParam2}?getParam1=1&getParam2=true";
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);// multipart/form-data
        // 请求参数
        byte[] bytes = null;
        try {
            bytes = multipartFile.getBytes();
        } catch (IOException e) {
            log.error("IOException",e);
        }
        if (null == bytes) {
            return "false";
        }
        ByteArrayResource byteArrayResource = new ByteArrayResource(bytes) {
            @Override
            public String getFilename() {
                // 这个方法需要重写
                return multipartFile.getName();
            }
        };
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("postParam1", 1);
        map.add("postParam2", true);
        map.add("multipartFile", byteArrayResource);
        // 包装请求头和参数
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(map,headers);
        // 发送请求
        ResponseEntity<String> responseEntity = commonRestTemplate.postForEntity(url, httpEntity, String.class, 1, true);
        String body = responseEntity.getBody();
        log.info("sendClient responseEntity = {}",JSON.toJSON(responseEntity));
        log.info("sendClient body = {}",JSON.toJSON(body));
        return body;
    }

}
