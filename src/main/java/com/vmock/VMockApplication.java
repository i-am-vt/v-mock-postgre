package com.vmock;

import com.vmock.base.constant.CommonConst;
import com.vmock.base.filter.MockFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * 启动程序
 *
 * @author mock
 */
@EnableAsync
@SpringBootApplication
public class VMockApplication {


    public static void main(String[] args) {
        // 启动主程序
        SpringApplication.run(VMockApplication.class, args);
    }

    /**
     * 主要逻辑的filter
     */
    @Bean
    public FilterRegistrationBean mockFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MockFilter());
        // order -> 1
        registrationBean.setOrder(1);
        // filter all request start with [/vmock]
        registrationBean.addUrlPatterns(CommonConst.RESTFUL_PATH + "/*");
        return registrationBean;
    }

}