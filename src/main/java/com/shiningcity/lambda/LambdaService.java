/**
 * 
 */
package com.shiningcity.lambda;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.map.HashedMap;
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
public class LambdaService {
    
    // 匿名函数
    public void lambda1() {
        // 简写new Runnable(){}
        //new Thread(()->{ System.out.println(Thread.currentThread().getName()); }).start();
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }

    // 遍历map集合
    public void lambda2() {
        Map<String, Integer> map = new HashedMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
//        map.forEach(new BiConsumer<String, Integer>() {
//            @Override
//            public void accept(String t, Integer u) {
//                System.out.println(t);
//                System.out.println(u);
//            }
//        });
        //map.forEach((s,i) -> {System.out.println(s + "----" + i);});
        map.forEach((s,i) -> System.out.println(s + "----" + i));
    }
    
    // 遍历list集合
    public void lambda11() {
        List<Integer> list = Arrays.asList(1,2,3,4);
        //list.forEach((i)->{System.out.println(i);});
        list.forEach(System.out::println);
    }
    
    // list集合排序
    public void lambda12() {
        List<Integer> list = Arrays.asList(4,2,1,3);
        // 正序
        //list.sort((o1,o2)->{ return o1.compareTo(o2); });
        list.sort((o1,o2) -> o1.compareTo(o2));
        System.out.println(JSON.toJSON(list));
        // 倒序
        //list.sort((o1,o2)->{ return -o1.compareTo(o2); });
        list.sort((o1,o2) -> -o1.compareTo(o2));
        System.out.println(JSON.toJSON(list));
    }
    
    // 函数式接口（函数式接口就是一个有且仅有一个抽象方法, 但可以有多个非抽象方法的接口.）
    // lambda表达式可以使用成员变量（intNum），并对其修改
    // lambda表达式可以使用外部局部变量（num），但不能对其修改
    private int intNum = 2;
    public void lambda13() {
        final int num = 1;
        Converter2<Integer, String> s2 = (param) -> System.out.println(String.valueOf(param + num));
        s2.convert(2);
        Converter3<Integer, String> s3 = () -> System.out.println(String.valueOf(num));
        s3.convert();
        Converter4<Integer, String> s4 = () -> intNum++;
        System.out.println(s4.convert());
    }
    // 自定义函数接口
    @FunctionalInterface    // 该注解不是必须的，该注解用于校验接口是否符合函数接口规范
    public interface Converter2<T1, T2> {
        void convert(T1 i);
    }
    @FunctionalInterface
    public interface Converter3<T1, T2> {
        void convert();
    }
    @FunctionalInterface
    public interface Converter4<T1, T2> {
        int convert();
    }
    /*
JDK1.8之前以有的函数式接口：
    java.lang.Runable
    java.util.concurrent.Callable
    java.security.PrivilegedAction
    java.util.Comparator
    java.io.FileFilter
    java.nio.file.PathMatcher
    java.lang.reflect.InvocationHandler
    java.beans.PropertyChangeListener
    java.awt.event.ActionListener
    javax.swing.event.ChangeListener
    
JDK1.8新增的函数式接口：
java.util.function 包下包含了很多类，用来支持Java的函数式编程，该包下的函数式接口有：
    BiConsumer<T,U>: 代表了一个接受两个输入参数的操作，并且不返回任何结果
    BiFunction<T,U,R>: 代表了一个接受两个输入参数的方法，并且返回一个结果
    BinaryOperator<T>: 代表了一个作用于于两个同类型操作符的操作，并且返回了操作符同类型的结果
    BiPredicate<T,U>: 代表了一个两个参数的boolean值方法
    BooleanSupplier: 代表了boolean值结果的提供方
    Consumer<T>: 代表了接受一个输入参数并且无返回的操作
    DoubleBinaryOperator: 代表了作用于两个double值操作符的操作，并且返回了一个double值的结果。
    DoubleConsumer: 代表一个接受double值参数的操作，并且不返回结果。
    DoubleFunction<R>: 代表接受一个double值参数的方法，并且返回结果
    DoublePredicate: 代表一个拥有double值参数的boolean值方法
    DoubleSupplier: 代表一个double值结构的提供方
    DoubleToIntFunction: 接受一个double类型输入，返回一个int类型结果。
    DoubleToLongFunction: 接受一个double类型输入，返回一个long类型结果
    DoubleUnaryOperator: 接受一个参数同为类型double,返回值类型也为double 。
    Function<T,R>: 接受一个输入参数，返回一个结果。
    IntBinaryOperator: 接受两个参数同为类型int,返回值类型也为int 。
    IntConsumer: 接受一个int类型的输入参数，无返回值 。
    IntFunction<R>: 接受一个int类型输入参数，返回一个结果 。
    IntPredicate: 接受一个int输入参数，返回一个布尔值的结果。
    IntSupplier: 无参数，返回一个int类型结果。
    IntToDoubleFunction: 接受一个int类型输入，返回一个double类型结果 。
    IntToLongFunction: 接受一个int类型输入，返回一个long类型结果。
    IntUnaryOperator: 接受一个参数同为类型int,返回值类型也为int 。
    LongBinaryOperator: 接受两个参数同为类型long,返回值类型也为long。
    LongConsumer: 接受一个long类型的输入参数，无返回值。
    LongFunction<R>: 接受一个long类型输入参数，返回一个结果。
    LongPredicate: R接受一个long输入参数，返回一个布尔值类型结果。
    LongSupplier: 无参数，返回一个结果long类型的值。
    LongToDoubleFunction: 接受一个long类型输入，返回一个double类型结果。
    LongToIntFunction: 接受一个long类型输入，返回一个int类型结果。
    LongUnaryOperator: 接受一个参数同为类型long,返回值类型也为long。
    ObjDoubleConsumer<T>: 接受一个object类型和一个double类型的输入参数，无返回值。
    ObjIntConsumer<T>: 接受一个object类型和一个int类型的输入参数，无返回值。
    ObjLongConsumer<T>: 接受一个object类型和一个long类型的输入参数，无返回值。
    Predicate<T>: 接受一个输入参数，返回一个布尔值结果。
    Supplier<T>: 无参数，返回一个结果。
    ToDoubleBiFunction<T,U>: 接受两个输入参数，返回一个double类型结果
    ToDoubleFunction<T>: 接受一个输入参数，返回一个double类型结果
    ToIntBiFunction<T,U>: 接受两个输入参数，返回一个int类型结果。
    ToIntFunction<T>: 接受一个输入参数，返回一个int类型结果。
    ToLongBiFunction<T,U>: 接受两个输入参数，返回一个long类型结果。
    ToLongFunction<T>: 接受一个输入参数，返回一个long类型结果。
    UnaryOperator<T>: 接受一个参数为类型T,返回值类型也为T。
     */
    
    // 函数式接口Predicate举例
    public void lambda14() {
        List<String> list = Arrays.asList("Java","String","Jack","Hello","Jackson","Hi");
        Predicate<String> p1 = (str)->str.startsWith("J");
        Predicate<String> p2 = (str)->str.length() >= 4;
        Predicate<String> por = p1.or(p2);
        Predicate<String> pand = p1.and(p2);
        System.out.println("-------- p1.test()");
        list.forEach((s) -> System.out.println(p1.test(s)));
        System.out.println("-------- p1.test() or p2.test()");
        list.forEach((s) -> System.out.println(por.test(s)));
        System.out.println("-------- p1.test() and p2.test()");
        list.forEach((s) -> System.out.println(pand.test(s)));
    }

    // 流式编程1，String 各种操作
    public void lambda15() {
        List<String> list = Arrays.asList("Java","String","Jack","Hello","Jackson","Hi","Java");

        // 在lambda表达式中不能对集合中原元素的值做更改。
        System.out.println("-------- .forEach()");
        list.forEach((s) -> s = s + ".ci");
        list.forEach(System.out::println);

        // .stream().map()可以对集合元素做更改后使用collect()生成新的集合，原集合元素不变。
        System.out.println("-------- .stream().map().collect()");
        Stream<String> stream = list.stream();
        Stream<String> map = stream.map((s) -> s + ".hi");
        List<String> collect = map.collect(Collectors.toList());
        list.forEach(System.out::println);
        collect.forEach(System.out::println);

        // 合并语句如下：
        System.out.println("-------- .stream().map().collect()");
        List<String> collect2 = list.stream().map((s) -> s + ".li").collect(Collectors.toList());
        list.forEach(System.out::println);
        collect2.forEach(System.out::println);
        
        // 使用collect()生成新的汇总对象
        System.out.println("-------- .stream().map().reduce()");
        String appenderResult = list.stream().map((s) -> s + ".mi.").reduce((appender,s) -> appender + s).get();
        list.forEach(System.out::println);
        System.out.println(appenderResult);
        
        // 使用filter()对元素做过滤后生成新集合
        System.out.println("-------- .stream().filter().collect()");
        Predicate<String> p1 = (s) -> s.length() <= 4;
        List<String> collect3 = list.stream().filter(p1).collect(Collectors.toList());
        collect3.forEach(System.out::println);
        
        // 使用filter()对元素做过滤后生成汇总对象
        System.out.println("-------- .stream().filter().reduce()");
        Predicate<String> p2 = (s) -> s.length() <= 4;
        String appenderResult2 = list.stream().filter(p2).reduce((appender,s) -> appender + s).get();
        System.out.println(appenderResult2);
        
        // 拼接字符串
        System.out.println("-------- .stream().map().collect(Collectors.joining())");
        String joiningResult1 = list.stream().map((s) -> s.toUpperCase()).collect(Collectors.joining(";"));
        String joiningResult2 = list.stream().map((s) -> s.toLowerCase()).collect(Collectors.joining(";"));
        String joiningResult3 = list.stream().map(String::valueOf).collect(Collectors.joining(";"));
        System.out.println(joiningResult1);
        System.out.println(joiningResult2);
        System.out.println(joiningResult3);

        // 去除重复
        System.out.println("-------- .stream().map().distinct().collect()");
        List<String> collect4 = list.stream().map(String::valueOf).distinct().collect(Collectors.toList());
        collect4.forEach(System.out::println);
    }
    
    // 流式编程2，Integer集合最大值、最小值、平均值、和、数量等。
    public void lambda16() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,5,4,3,2,1);
        IntSummaryStatistics summaryStatistics = list.stream().mapToInt((x) -> x).summaryStatistics();
        double average = summaryStatistics.getAverage();
        int max = summaryStatistics.getMax();
        int min = summaryStatistics.getMin();
        long count = summaryStatistics.getCount();
        long sum = summaryStatistics.getSum();
        System.out.println(MessageFormat.format("average = {0}, max = {1}, min = {2}, count = {3}, sum = {4}", average, max, min, count, sum));
    }
    
    // 方法引用
    /*
规则：
1、引用静态方法
    假如 Lambda 表达式符合如下格式：
([变量1, 变量2, ...]) -> 类名.静态方法名([变量1, 变量2, ...])
    我们可以简写成如下格式：
类名::静态方法名
注意：注意这里静态方法名后面不需要加括号，也不用加参数，因为编译器都可以推断出来。
2、引用对象的方法
    假如 Lambda 表达式符合如下格式：
([变量1, 变量2, ...]) -> 对象引用.方法名([变量1, 变量2, ...])
    我们可以简写成如下格式：
对象引用::方法名
3、引用当前对象的方法
    当我们要执行的表达式是调用 Lambda 表达式所在的类的方法时，我们还可以采用如下格式：
this::方法名
4、引用类的方法
    假如我们的 Lambda 表达式符合如下格式：
(变量1[, 变量2, ...]) -> 变量1.实例方法([变量2, ...])
    我们可以简写成如下格式：
变量1对应的类名::实例方法名
5、引用构造方法
    假如我们的 Lambda 表达式符合如下格式：
([变量1, 变量2, ...]) -> new 类名([变量1, 变量2, ...])
    我们可以简写成如下格式：
类名::new
     */
    public void lambda17() {
        // 普通写法
        System.out.println("-------- 普通写法");
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,5,4,3,2,1);
        Collections.sort(list, (o1,o2) -> MyUtils.compare(o1, o2));
        list.forEach(System.out::println);
        // 方法引用写法
        System.out.println("-------- 方法引用写法");
        List<Integer> list2 = Arrays.asList(1,2,3,4,5,6,5,4,3,2,1);
        Collections.sort(list2, MyUtils::compare);
        list.forEach(System.out::println);
    }
    public static class MyUtils {
        public static int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }
    
}
