package com.github.mrcaoyc.office.online.factory;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CaoYongCheng
 */
@SpringBootApplication
@EnableSwagger2Doc
@ComponentScan(
        "com.github.mrcaoyc"
)
@EnableCaching
public class FactoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(FactoryApplication.class, args);
    }
}
