/**
 * 
 */
package com.shiningcity.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

import com.shiningcity.entity.StudentEntity;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;

/**
 * 
 * @author dikai
 * @create 2020-6-21
 */
public class PoiClass {
    public static void main(String[] args) throws IOException {
        
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
        
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\就放这里吧.xls"));
        
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("标题","sheet名称"),
                StudentEntity.class, list);
        workbook.write(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
    
    // Web方式
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, List<StudentEntity> list) throws IOException {
        String fileName = new String("可以是中文名称.xls".getBytes("UTF-8"),"ISO_8859_1");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;fileName="+fileName); // attachment：已下载的方式加载文件
        ServletOutputStream outputStream = response.getOutputStream();
        
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("标题","sheet名称"),
                StudentEntity.class, list);
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
    
}
