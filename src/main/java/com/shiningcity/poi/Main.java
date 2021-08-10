/**
 * 
 */
package com.shiningcity.poi;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.channels.GatheringByteChannel;
import java.util.List;
import java.util.ArrayList;
import java.util.Formatter.BigDecimalLayoutForm;

import javax.naming.event.NamingListener;

import org.apache.tomcat.util.modeler.BaseAttributeFilter;

/**
 * 
 * @author dikai
 * @create 2020-6-25
 */
public class Main {
    public static void main(String[] args) {
//        BigDecimal bigDecimal = new BigDecimal(1L);
//        int intValue = bigDecimal.intValue();
//        long longValue = bigDecimal.longValue();
//        float floatValue = bigDecimal.floatValue();
//        double doubleValue = bigDecimal.doubleValue();
//        System.out.println();
        
//        Integer i = null;
//        int j = i;
//        System.err.println(j);
        
//        Integer x = new Integer(1);
//        Integer y = new Integer(1);
//        Integer m = new Integer(126);
//        Integer n = new Integer(126);
//        Integer i = new Integer(127);
//        Integer j = new Integer(127);
//        Integer k = new Integer(128);
//        Integer l = new Integer(128);
//        Integer o = new Integer(10086);
//        Integer p = new Integer(10086);
//        
//        System.out.println(x == y);
//        System.out.println(m == n);
//        System.out.println(i == j);
//        System.out.println(k == l);
//        System.out.println(o == p);
//        
//        System.out.println(x.equals(y));
//        System.out.println(m.equals(n));
//        System.out.println(i.equals(j));
//        System.out.println(k.equals(l));
//        System.out.println(o.equals(p));
//        
//        System.out.println(x == 1);
//        System.out.println(m == 126);
//        System.out.println(i == 127);
//        System.out.println(k == 128);
//        System.out.println(p == 10086);
        
        

//        Integer m = Integer.valueOf(126);
//        Integer n = Integer.valueOf(126);
//        Integer k = Integer.valueOf(128);
//        Integer l = Integer.valueOf(128);
//        Integer o = Integer.valueOf(10086);
//        Integer p = Integer.valueOf(10086);
//        
//        System.out.println(m == n);
//        System.out.println(k == l);
//        System.out.println(o == p);
//        
//        System.out.println(m.equals(n));
//        System.out.println(k.equals(l));
//        System.out.println(o.equals(p));
//        
//        System.out.println(m == 126);
//        System.out.println(k == 128);
//        System.out.println(p == 10086);
//        
//        System.out.println(m.equals(126));
//        System.out.println(k.equals(128));
//        System.out.println(p.equals(10086));
        
        
//        byte b = 1;
//        short s = 1;
//        int i = 1;
//        long l = 1;
//        Byte B = 1;
//        Short S = 1;
//        Integer I = 1;
//        Long L = 1L;
        
//        System.out.println(~1);
//        System.out.println(Integer.toBinaryString(1));
//        System.out.println(Integer.toBinaryString(~1));
//        System.out.println(Integer.toBinaryString(-1));
//        System.out.println(Integer.toBinaryString(-2));
//        
//        System.out.println(Long.toBinaryString(1L));
//        System.out.println(Long.toBinaryString(~1L));
//        System.out.println(Long.toBinaryString(-1L));
//        System.out.println(Long.toBinaryString(-2L));
        
//        System.out.println(Integer.toBinaryString(1));
//        System.out.println(Integer.toBinaryString(1<<2));
//        System.out.println(Integer.toBinaryString(16));
//        System.out.println(Integer.toBinaryString(16>>2));
//        
//        System.out.println(Integer.toBinaryString(-1));
//        System.out.println(Integer.toBinaryString(-1<<2));
//        System.out.println(Integer.toBinaryString(-16));
//        System.out.println(Integer.toBinaryString(-16>>2));
//        
//        System.out.println("========");
//        
//        System.out.println(Integer.toBinaryString(8));
//        System.out.println(Integer.toBinaryString(7));
//        System.out.println(Integer.toBinaryString(6));
//        System.out.println(Integer.toBinaryString(5));
//        System.out.println(Integer.toBinaryString(4));
//        System.out.println(Integer.toBinaryString(3));
//        System.out.println(Integer.toBinaryString(2));
//        System.out.println(Integer.toBinaryString(1));
//        System.out.println(Integer.toBinaryString(0));
//        System.out.println(Integer.toBinaryString(-1));
//        System.out.println(Integer.toBinaryString(-2));
//        System.out.println(Integer.toBinaryString(-3));
//        System.out.println(Integer.toBinaryString(-4));
//        System.out.println(Integer.toBinaryString(-5));
//        System.out.println(Integer.toBinaryString(-6));
//        System.out.println(Integer.toBinaryString(-7));
//        System.out.println(Integer.toBinaryString(-8));
        
//        System.out.println(1&-1);
//        System.out.println(-1&-2);
//        System.out.println(2&-2);
        
//        System.out.println(Integer.toBinaryString(~1));
        
//        byte b = 2*2*2*2*2*2;
//        System.out.println(b << 1);
//        int i = 256 * 256 * 256 * 64 *2 -1;
//        System.out.println(Integer.toBinaryString(i));
//        System.out.println(Integer.toBinaryString(i << 2));
//        System.out.println(Long.toBinaryString(i << 2));
//        System.out.println(i << 2);
//        System.out.println((long)i << 2);
        
//        System.out.println(Integer.toBinaryString(-16));
//        System.out.println(Integer.toBinaryString(-16 >>> 4));
//        System.out.println(-16 >>> 4);
//        
//        System.out.println(Integer.toBinaryString(127));
//        System.out.println(Integer.toBinaryString(-128));
        
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MIN_VALUE);
//        
//        System.out.println(Long.MAX_VALUE);
//        System.out.println(Long.MIN_VALUE);
//        
//        BigDecimal bg = BigDecimal.valueOf(Long.MAX_VALUE);
        
//        BigDecimal bg = BigDecimal.valueOf(1);
//        BigDecimal bg10 = BigDecimal.valueOf(10);
//        BigDecimal bg20 = BigDecimal.valueOf(20);
//        BigDecimal bg202 = BigDecimal.valueOf(202);
//        BigDecimal bg3 = BigDecimal.valueOf(3);
//        BigDecimal bg25 = BigDecimal.valueOf(25);
//        BigDecimal bg3_4 = BigDecimal.valueOf(3.4444);
//        BigDecimal bg3_5 = BigDecimal.valueOf(3.5555);
//        BigDecimal bg3_6 = BigDecimal.valueOf(3.6666);
        
//        BigDecimal add = bg202.add(bg25, new MathContext(2, RoundingMode.HALF_UP));
//        System.out.println(bg202.toEngineeringString());
//        System.out.println(bg20.ulp());
        
//        System.out.println(bg10.compareTo(bg20));
//        System.out.println(bg20.compareTo(bg10));
//        System.out.println(bg20.compareTo(bg202));
//        BigDecimal[] divArr = bga.divideAndRemainder(bgb);
        
//        System.out.println(bg20.divide(divisor, scale, RoundingMode.CEILING));
//        System.out.println(bg25.divide(bg10));
//        new MathContext(setPrecision, setRoundingMode)
        
        
        
//        int roundCeiling = BigDecimal.ROUND_CEILING;
//        System.out.println(roundCeiling);
//        BigDecimal.ROUND_DOWN
//        BigDecimal.ROUND_FLOOR
//        BigDecimal.ROUND_HALF_DOWN
//        BigDecimal.ROUND_HALF_EVEN
//        BigDecimal.ROUND_HALF_UP
//        BigDecimal.ROUND_UNNECESSARY
//        BigDecimal.ROUND_UP
        
//        int roundCeiling2 = bg.ROUND_CEILING;
//        System.out.println(roundCeiling2);
//        bg.ROUND_DOWN
//        bg.ROUND_FLOOR
//        bg.ROUND_HALF_DOWN
//        bg.ROUND_HALF_EVEN
//        bg.ROUND_HALF_UP
//        bg.ROUND_UNNECESSARY
//        bg.ROUND_UP
        
//        
//        System.out.println(bg3_4.divide(bg, 2, BigDecimal.ROUND_HALF_DOWN));
//        System.out.println(bg3_4.divide(bg, 3, BigDecimal.ROUND_HALF_DOWN));
//        System.out.println(bg3_5.divide(bg, 2, BigDecimal.ROUND_HALF_DOWN));
//        System.out.println(bg3_5.divide(bg, 3, BigDecimal.ROUND_HALF_DOWN));
//        System.out.println(bg3_6.divide(bg, 2, BigDecimal.ROUND_HALF_DOWN));
//        System.out.println(bg3_6.divide(bg, 3, BigDecimal.ROUND_HALF_DOWN));
//        System.out.println("====");
//        System.out.println(bg3_4.divide(bg, 2, RoundingMode.HALF_DOWN));
//        System.out.println(bg3_4.divide(bg, 3, RoundingMode.HALF_DOWN));
//        System.out.println(bg3_5.divide(bg, 2, RoundingMode.HALF_DOWN));
//        System.out.println(bg3_5.divide(bg, 3, RoundingMode.HALF_DOWN));
//        System.out.println(bg3_6.divide(bg, 2, RoundingMode.HALF_DOWN));
//        System.out.println(bg3_6.divide(bg, 3, RoundingMode.HALF_DOWN));
//        System.out.println("====");
//        System.out.println(bg3_4.divide(bg, new MathContext(2, RoundingMode.HALF_DOWN)));
//        System.out.println(bg3_4.divide(bg, new MathContext(2, RoundingMode.HALF_DOWN)));
//        System.out.println(bg3_5.divide(bg, new MathContext(2, RoundingMode.HALF_DOWN)));
//        System.out.println(bg3_5.divide(bg, new MathContext(2, RoundingMode.HALF_DOWN)));
//        System.out.println(bg3_6.divide(bg, new MathContext(2, RoundingMode.HALF_DOWN)));
        
        
        
//        double d = 0.2; System.out.println(new BigDecimal(d));
//        System.out.println(new BigDecimal("0.2"));
//        double f = 0.2; System.out.println(BigDecimal.valueOf(d));
        
//        BigDecimal.valueOf(0.2);
//        
//        BigDecimal valueOf = BigDecimal.valueOf(1);
        
//        bg.add(augend)
        
//        System.out.println(bg);
        
//        System.out.println(Long.toBinaryString(4294967290L));
        
        int a = -123456;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(a);
        int b = (a^(a>>31))-(a>>31);
        System.out.println(Integer.toBinaryString(b));
        System.out.println(b);
        
        
    }
    
    // 将第seat位设置1，从右向左计算位置，位置从1开始
    public int setOne(int i,int seat) {
        return i | (1 << (seat-1));
    }
    
    // 将第seat位设置0，从右向左计算位置，位置从1开始
    public int setZero(int i,int seat) {
        return i & (~(1 << (seat-1)));
    }

    // 计算1的个数
    public int oneCount(int i) {
        int count = 0;
        // 注意：如果最高位用于计数的话就不能判断 i > 0，而是要判断i != 0
        while (i != 0) {
            count += (i & 1);
            i = i >>> 1;
        }
        return count;
    }
    
    // 计算0的个数
    public int zeroCount(int i) {
        int count = 32;
        // 注意：如果最高位用于计数的话就不能判断 i > 0，而是要判断i != 0
        while (i != 0) {
            count -= (i & 1);
            i = i >>> 1;
        }
        return count;
    }
    
    // 输出1的位置集合，从右向左计算，从1开始
    public List<Integer> oneSeatList(int i) {
        List<Integer> list = new ArrayList<Integer>();
        int seat = 1;
        // 注意：如果最高位用于计数的话就不能判断 i > 0，而是要判断i != 0
        while (i != 0) {
            if ((i & 1) == 1) {
                list.add(seat);
            }
            seat ++;
            i = i >>> 1;
        }
        return list;
    }
    
    // 输出0的位置集合，从右向左计算，从1开始
    public List<Integer> zeroSeatList(int i) {
        List<Integer> list = new ArrayList<Integer>();
        for (int seat = 1; seat <= 32; seat++) {
            if ((i & 1) == 0) {
                list.add(seat);
            }
            i = i >>> 1;
        }
        return list;
    }
    
}
