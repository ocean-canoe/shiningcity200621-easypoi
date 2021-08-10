/**
 * 
 */
package com.shiningcity.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiningcity.entity.StudentEntity;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author dikai
 * @create 2020-6-30
 */
@Slf4j
@RestController
@RequestMapping("/easypoi")
public class EasypoiController {
    
    @Value("${testConfig.hello}")
    private String hello;
    
    @RequestMapping("/test/{name}")
    public String test(@PathVariable("name") String name,HttpServletRequest request,HttpServletResponse response) {
//        HttpSession session = request.getSession();
//        String id1 = session.getId();
//        log.info("request.getSession().getId()={}",id1);
//        String id2 = request.getRequestedSessionId();
//        log.info("request.getRequestedSessionId()={}",id2);
//        HttpSessionContext sessionContext = session.getSessionContext();
//        HttpSession session2 = sessionContext.getSession(name);
//        if (null != session2){
//            session2.invalidate();
//        }
//        session.invalidate();
        return "OK! Name = " + name;
    }
    
    @RequestMapping("/test2/{name}")
    public String test2(@PathVariable("name") String name) {
        return "OK! " + hello + " " + name;
    }
    /*
HSSF是POI项目的Excel '97（-2007）文件格式的纯Java实现。
XSSF是POI Project的Excel 2007 OOXML（.xlsx）文件格式的纯Java实现。
自3.8-beta3以来，POI提供了基于XSSF构建的低内存占用SXSSF API。
SXSSF是XSSF的API兼容流式扩展，用于在必须生成非常大的电子表格时使用，并且堆空间有限。SXSSF通过限制对滑动窗口内行的访问来实现其低内存占用，而XSSF允许访问文档中的所有行。不再在窗口中的旧行变得不可访问，因为它们被写入磁盘。
简单的说，HSSF对应旧的xls格式，XSSF对应新的xlsx格式，SXSSF是在XSSF的基础上，支持导出大批量的excel数据。
     */
    
    // 2003版本，效率高，不能超过65535条
    @PostMapping("/exportExcel2003")
    public void exportExcel2003(HttpServletRequest request,HttpServletResponse response) {
        
        List<StudentEntity> list = getStudentList();
        
        ServletOutputStream outputStream = null;
        try {
            String fileName = new String("可以是中文名称.xls".getBytes("UTF-8"),"ISO_8859_1");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            //response.setHeader("Content-Disposition", "attachment;fileName="+fileName); // attachment：已下载的方式加载文件
            response.setHeader("Content-Disposition", "attachment;filename="+fileName); // attachment：已下载的方式加载文件
            outputStream = response.getOutputStream();
            
            ExportParams exportParams = new ExportParams("标题","sheet名称",ExcelType.HSSF);
            exportParams.setStyle(ExcelStyleImpl.class);
            Workbook workbook = ExcelExportUtil.exportExcel(exportParams, StudentEntity.class, list);
            workbook.write(outputStream);
            
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    // 2007版本效率低。HSSFWorkbook 不能超过65535条，效率较低；SXSSFWorkbook 可以超过65535条，效率较高，但比2003版低
    @PostMapping("/exportExcel2007")
    public void exportExcel2007(HttpServletRequest request,HttpServletResponse response,String name,Integer value) {
        log.info("name={},value={}",name,value);

        List<StudentEntity> list = getStudentList();
        
        ServletOutputStream outputStream = null;
        try {
            String fileName = new String("可以是中文名称.xlsx".getBytes("UTF-8"),"ISO_8859_1");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            //response.setHeader("Content-Disposition", "attachment;fileName="+fileName); // attachment：已下载的方式加载文件
            response.setHeader("Content-Disposition", "attachment;filename="+fileName); // attachment：已下载的方式加载文件
            outputStream = response.getOutputStream();
            
            ExportParams exportParams = new ExportParams("标题","sheet名称",ExcelType.XSSF);
            // 表格样式，（宽度在实体类的注解中设置）
            exportParams.setStyle(ExcelStyleImpl.class);
            Workbook workbook = ExcelExportUtil.exportExcel(exportParams, StudentEntity.class, list);
            workbook.write(outputStream);
            
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    private List<StudentEntity> getStudentList(){
        
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
    

    @RequestMapping("/testEx")
    public String testEx() {
        
        try {
            //int i = 1/0;
            Integer i = null;
            Integer j = i/0;
        } catch (Exception e) {
            log.error("exception",e);
        }
        
        return "666";
    }

    // 测试锁1
    /*
========1-外========
====1-内====
========1-外========
====1-内====
========1-外========
====1-内====
========1-外========
====1-内====
========1-外========
====1-内====
========1-外========
====1-内====
========1-外========
====1-内====
========1-外========
====1-内====
========1-外========
====1-内====
====2-内====
========2-外========
========1-外========
====1-内====
====2-内====
========2-外========
========1-外========
====1-内====
========1-外========
====1-内====
====2-内====
========2-外========
========1-外========
====1-内====
====2-内====
========2-外========
========1-外========
====1-内====
====2-内====
========2-外========
====2-内====
========2-外========
========1-外========
====1-内====
====2-内====
========2-外========
========1-外========
====1-内====
========1-外========
====1-内====
====2-内====
========2-外========
========1-外========
====1-内====
====2-内====
========2-外========
========1-外========
====1-内====
====2-内====
========2-外========
========1-外========
====1-内====
====2-内====
========2-外========
========1-外========
====1-内====
====2-内====
========2-外========
========1-外========
====1-内====
====2-内====
========2-外========
========1-外========
====1-内====
====2-内====
========2-外========
====2-内====
========2-外========
====2-内====
========2-外========
====2-内====
========2-外========
====2-内====
========2-外========
====2-内====
========2-外========
====2-内====
========2-外========
====2-内====
========2-外========
====2-内====
========2-外========
====2-内====
========2-外========
     */
    @RequestMapping("/testSync1")
    public String testSync1() {
        System.out.println("========1-外========");
        System.out.println("====1-内====");
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("====2-内====");
        System.out.println("========2-外========");
        return "666";
    }

    // 测试锁2
    /*
========1-外========
====1-内====
========1-外========
========1-外========
========1-外========
========1-外========
========1-外========
========1-外========
========1-外========
========1-外========
====2-内====
========2-外========
====1-内====
========1-外========
========1-外========
========1-外========
========1-外========
========1-外========
========1-外========
========1-外========
========1-外========
========1-外========
====2-内====
========2-外========
====1-内====
========1-外========
========1-外========
====2-内====
========2-外========
====1-内====
====2-内====
========2-外========
====1-内====
====2-内====
========2-外========
====1-内====
====2-内====
========2-外========
====1-内====
====2-内====
========2-外========
====1-内====
====2-内====
========2-外========
====1-内====
====2-内====
========2-外========
====1-内====
====2-内====
========2-外========
====1-内====
====2-内====
========2-外========
====1-内====
====2-内====
========2-外========
====1-内====
====2-内====
========2-外========
====1-内====
====2-内====
========2-外========
====1-内====
====2-内====
========2-外========
====1-内====
====2-内====
========2-外========
====1-内====
====2-内====
========2-外========
====1-内====
====2-内====
========2-外========
====1-内====
====2-内====
========2-外========
====1-内====
====2-内====
========2-外========
     */
    @RequestMapping("/testSync2")
    public String testSync2() {
        System.out.println("========1-外========");
        synchronized (this) {
            testSync2Methord();
        }
        System.out.println("========2-外========");
        return "666";
    }
    public void testSync2Methord() {
        System.out.println("====1-内====");
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        if (0 == 0) {
//            return "123";  // return可以释放锁
//        }
        System.out.println("====2-内====");
    }
    
}
