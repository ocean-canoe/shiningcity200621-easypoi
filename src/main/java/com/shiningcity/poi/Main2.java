/**
 * 
 */
package com.shiningcity.poi;

import java.text.MessageFormat;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;

/**
 * 
 * @author dikai
 * @create 2020-9-15
 */
public class Main2 {
    public static void main(String[] args) {
        String s1 = "123{0}456{1}";
        String s2 = "-";
        String s3 = "=";
        String s4 = MessageFormat.format(s1, s2, s3);
        System.out.println(s4);
    }
    
}
