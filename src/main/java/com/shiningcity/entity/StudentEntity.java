package com.shiningcity.entity;

import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class StudentEntity implements java.io.Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -1305926298795612666L;

    /**
     * id
     */
    private String id;
    /**
     * 学生姓名
     */
    @Excel(name = "学生姓名", width = 20, isImportField = "true_st")
    private String name;
    /**
     * 学生性别
     */
    @Excel(name = "学生性别", replace = { "男_1", "女_2" }, suffix = "生", width = 20, isImportField = "true_st")
    private int sex;

    @Excel(name = "出生日期", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd", isImportField = "true_st", width = 20)
    private Date birthday;

    @Excel(name = "进校日期", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd", width = 20)
    private Date registrationDate;
    
    public StudentEntity(String id, String name, int sex, Date birthday, Date registrationDate) {
        super();
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.registrationDate = registrationDate;
    }
    
    public StudentEntity(String name, int sex) {
        super();
        this.name = name;
        this.sex = sex;
    }

    public StudentEntity() {
        super();
        // TODO Auto-generated constructor stub
    }

 }