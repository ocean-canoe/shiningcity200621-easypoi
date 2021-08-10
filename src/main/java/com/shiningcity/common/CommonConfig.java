/**
 * 
 */
package com.shiningcity.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author dikaihui
 * @create 2020-8-27
 */
@Configuration
public class CommonConfig {
    
    @Bean
    public RestTemplate commonRestTemplate() {
        return new RestTemplate();
    }
}
