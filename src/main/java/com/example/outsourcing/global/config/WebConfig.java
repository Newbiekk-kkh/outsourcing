package com.example.outsourcing.global.config;

import com.example.outsourcing.global.filter.LoginFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final OwnerInterceptor ownerInterceptor;
    private final AdminInterceptor adminInterceptor;

    public WebConfig(OwnerInterceptor ownerInterceptor, AdminInterceptor adminInterceptor) {
        this.ownerInterceptor = ownerInterceptor;
        this.adminInterceptor = adminInterceptor;
    }

    
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoginFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ownerInterceptor)
                .addPathPatterns("/stores/owner", "/stores/owner/*");

        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/admin/*");
    }




}
