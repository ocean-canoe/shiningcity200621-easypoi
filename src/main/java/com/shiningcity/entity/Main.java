/**
 * 
 */
package com.shiningcity.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author dikai
 * @create 2020-8-17
 */
public class Main {
    public static void main(String[] args) {
        
        ConcurrentHashMap<String, Object> cMap = new ConcurrentHashMap<String, Object>();
        
        TestClass t1 = new TestClass();
        t1.setCMap(cMap);
        
        TestClass t2 = new TestClass();
        t2.setCMap(cMap);
        
        ConcurrentHashMap<String, Object> cMap1 = t1.getCMap();
        ConcurrentHashMap<String, Object> cMap2 = t2.getCMap();
        
        cMap1.put("test1", 1008610086);
        cMap2.put("test2", 2008620086);
        
        System.out.println(JSON.toJSON(cMap));
        
        System.out.println(cMap.size());
        
        for (Iterator<Entry<String, Object>> it = cMap.entrySet().iterator(); it.hasNext();){
            Map.Entry<String, Object> item = it.next();
            System.out.println(item.getKey());
            System.out.println(item.getValue());
            it.remove();
        }

        System.out.println(JSON.toJSON(cMap));
        
        System.out.println(cMap.size());
        
    }
}
