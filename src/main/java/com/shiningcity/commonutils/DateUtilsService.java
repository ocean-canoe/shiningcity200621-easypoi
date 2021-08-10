/**
 * 
 */
package com.shiningcity.commonutils;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shiningcity.entity.StudentEntity;

/**
 * 
 * @author dikaihui
 * @create 2020-8-31
 */
@Service
public class DateUtilsService {
    
    // 匿名函数
    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date todayDate = new Date();
        
        // 当前时间加1天
        Date addDays = DateUtils.addDays(todayDate, 1);
        System.out.println(format.format(addDays));
        
        // 当前时间加一周
        Date addWeeks = DateUtils.addWeeks(todayDate, 1);
        System.err.println(format.format(addWeeks));
        
        // 常量--每天的秒数
        long millisPerDay = DateUtils.MILLIS_PER_DAY;
        System.out.println(millisPerDay);
        
        // 以todayDate为基数，以Calendar.DATE为单位，计算时间上限
        Date ceiling = DateUtils.ceiling(todayDate, Calendar.DATE);
        System.out.println(format.format(ceiling));
        
        // 以todayDate为基数，以Calendar.YEAR为单位，计算已经度过的天数
        long fragmentInDays = DateUtils.getFragmentInDays(todayDate, Calendar.YEAR);
        System.out.println(fragmentInDays);
        
        // TODO 。。。。。。
    }

}
