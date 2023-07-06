package com.atguigu.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * ClassName: CrowdMainClass
 * Package: com.atguigu.crowd
 * Description:
 *
 * @Author peter
 * @Create 2023/6/28 22:19
 * @Version 1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class CrowdMainClass {
    public static void main(String[] args) {
        SpringApplication.run(CrowdMainClass.class, args);
    }
}