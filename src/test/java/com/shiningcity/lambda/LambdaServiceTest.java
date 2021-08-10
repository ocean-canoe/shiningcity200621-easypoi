/**
 * 
 */
package com.shiningcity.lambda;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shiningcity.MyApplication;

/**
 * 
 * @author dikaihui
 * @create 2020-8-31
 */
@SpringBootTest(classes = MyApplication.class)
@RunWith(SpringRunner.class)
public class LambdaServiceTest {

    @Autowired
    private LambdaService lambdaService;
    
    @Test
    public void testLambda1() {
        lambdaService.lambda1();
    }

    @Test
    public void testLambda2() {
        lambdaService.lambda2();
    }

    @Test
    public void testLambda11() {
        lambdaService.lambda11();
    }

    @Test
    public void testLambda12() {
        lambdaService.lambda12();
    }

    @Test
    public void testLambda13() {
        lambdaService.lambda13();
    }

    @Test
    public void testLambda14() {
        lambdaService.lambda14();
    }

    @Test
    public void testLambda15() {
        lambdaService.lambda15();
    }

    @Test
    public void testLambda16() {
        lambdaService.lambda16();
    }

    @Test
    public void testLambda17() {
        lambdaService.lambda17();
    }

}
