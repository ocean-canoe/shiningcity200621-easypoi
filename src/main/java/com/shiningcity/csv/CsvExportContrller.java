/**
 * 
 */
package com.shiningcity.csv;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiningcity.entity.StudentEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
@RestController
public class CsvExportContrller {
    
    @GetMapping("/export")
    public String export(HttpServletResponse response) {
        // 查询需要导出的数据
        List<StudentEntity> dataList = getStudentList();
        if (CollectionUtils.isEmpty(dataList)) {
            return "无可用数据";
        }
 
        // 构造导出数据结构
        String titles = "学生姓名,学生性别,出生日期,进校日期";  // 设置表头
        String keys = "name,sex,birthday,registrationDate";  // 设置每列字段
        
        // 构造导出数据
        List<Map<String, Object>> datas = new ArrayList<>();
        Map<String, Object> map = null;
        for (StudentEntity data : dataList) {
            map = new HashMap<>();
            map.put("name", data.getName());
            map.put("sex", data.getSex());
            map.put("birthday", data.getBirthday());
            map.put("registrationDate", data.getRegistrationDate());
            datas.add(map);
        }
 
        // 设置导出文件前缀
        String fName = "data_";
 
        // 文件导出
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            CsvExportUtil.responseSetProperties(fName, response);
            CsvExportUtil.doExport(datas, titles, keys, os);
            os.close();
        } catch (Exception e) {
            log.error("导出失败", e);
            return "导出失败";
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    log.error("导出失败", e);
                }
            }
        }
        return "导出成功";
    }
    
    @GetMapping("/export2")
    public String export2(HttpServletResponse response) {
        // 查询需要导出的数据
        List<StudentEntity> dataList = getStudentList();
        if (CollectionUtils.isEmpty(dataList)) {
            return "无可用数据";
        }
        
        // 构造导出数据结构
        String titles = "学生姓名,学生性别,出生日期,进校日期";  // 设置表头
        String keys = "name,sex,birthday,registrationDate";  // 设置每列字段
        
        // 构造导出数据
        List<Map<String, Object>> datas = new ArrayList<>();
        Map<String, Object> map = null;
        for (StudentEntity data : dataList) {
            map = new HashMap<>();
            map.put("name", data.getName());
            map.put("sex", data.getSex());
            map.put("birthday", data.getBirthday());
            map.put("registrationDate", data.getRegistrationDate());
            datas.add(map);
        }
        
        // jar包根目录
        ApplicationHome home = new ApplicationHome(getClass());
        File jarF = home.getSource();
        String dirPath = jarF.getParentFile().toString()+"/csvTemp";
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        
        // 文件导出
        FileOutputStream fos = null;
        try {
            // 创建临时文件
            String fileNamePrefix = "filename_";
            File tempFile = File.createTempFile(fileNamePrefix, ".csv", dirFile);//创建临时文件
            fos = new FileOutputStream(tempFile);
            CsvExportUtil.doExport(datas, titles, keys, fos);
        } catch (Exception e) {
            log.error("导出失败", e);
            return "导出失败";
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    log.error("导出失败", e);
                }
            }
        }
        return "导出成功";
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
}

