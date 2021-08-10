/**
 * 
 */
package com.shiningcity.testRestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/receiveRest")
public class ReceiveRestController {

    @RequestMapping("/returnInfoServer/{pathParam1}/{pathParam2}")
    public StudentEntity returnInfoServer(HttpServletRequest request,HttpServletResponse response,Integer postParam1,Boolean postParam2,@PathVariable("pathParam1") Integer pathParam1,@PathVariable("pathParam2") Boolean pathParam2) {
        log.info("======== returnInfoServer ========");
        String getParam1 = request.getParameter("getParam1");
        String getParam2 = request.getParameter("getParam2");
        log.info("getParam1 = {}, getParam2 = {}",getParam1,getParam2);
        log.info("postParam1 = {}, postParam2 = {}",postParam1,postParam2);
        log.info("pathParam1 = {}, pathParam2 = {}",pathParam1,pathParam2);
        return createStudentInfo();
    }

    @RequestMapping("/returnListServer/{pathParam1}/{pathParam2}")
    public List<StudentEntity> returnListServer(HttpServletRequest request,HttpServletResponse response,Integer postParam1,Boolean postParam2,@PathVariable("pathParam1") Integer pathParam1,@PathVariable("pathParam2") Boolean pathParam2) {
        log.info("======== returnListServer ========");
        String getParam1 = request.getParameter("getParam1");
        String getParam2 = request.getParameter("getParam2");
        log.info("getParam1 = {}, getParam2 = {}",getParam1,getParam2);
        log.info("postParam1 = {}, postParam2 = {}",postParam1,postParam2);
        log.info("pathParam1 = {}, pathParam2 = {}",pathParam1,pathParam2);
        return createStudentList();
    }

    @RequestMapping("/receiveBodyServer/{pathParam1}/{pathParam2}")
    public List<StudentEntity> receiveBodyServer(HttpServletRequest request,HttpServletResponse response,@PathVariable("pathParam1") Integer pathParam1,@PathVariable("pathParam2") Boolean pathParam2,@RequestBody StudentEntity student) {
        log.info("======== receiveBodyServer ========");
        String getParam1 = request.getParameter("getParam1");
        String getParam2 = request.getParameter("getParam2");
        log.info("getParam1 = {}, getParam2 = {}",getParam1,getParam2);
        log.info("pathParam1 = {}, pathParam2 = {}",pathParam1,pathParam2);
        log.info("student = {}",JSON.toJSON(student));
        return createStudentList();
    }

    @RequestMapping("/receiveMultipartFileServer/{pathParam1}/{pathParam2}")
    public String receiveMultipartFileServer(HttpServletRequest request,HttpServletResponse response,Integer postParam1,Boolean postParam2,MultipartFile multipartFile) {
        log.info("======== receiveMultipartFileServer ========");
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
        return "receiveMultipartFileServer success";
    }

    private StudentEntity createStudentInfo(){
        StudentEntity s1 = new StudentEntity();
        s1.setName("张三");
        s1.setSex(1);
        s1.setBirthday(new Date());
        s1.setRegistrationDate(new Date());
        return s1;
    }
    private List<StudentEntity> createStudentList(){
        List<StudentEntity> list = new ArrayList<>();
        StudentEntity s1 = new StudentEntity();
        s1.setName("张三");
        s1.setSex(1);
        s1.setBirthday(new Date());
        s1.setRegistrationDate(new Date());
        StudentEntity s2 = new StudentEntity();
        s2.setName("李四");
        s2.setSex(2);
        s2.setBirthday(new Date());
        s2.setRegistrationDate(new Date());
        list.add(s1);
        list.add(s2);
        return list;
    }
}
