package com.sjht.school.mobile.config;

import com.sjht.school.mobile.filter.TokenFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration()
public class FilterConfig {

    @Value("${filter.urlPatterns}")
    private String [] urlPatterns;

      @Bean
      public FilterRegistrationBean registFilter() {
           FilterRegistrationBean registration = new FilterRegistrationBean();
           registration.setFilter(tokenFilter());
           registration.addUrlPatterns(urlPatterns);

           registration.setName("tokenFilter");
           registration.setOrder(1);
           return registration;
      }

      @Bean
      public TokenFilter tokenFilter() {
           return new TokenFilter();
      }

    public String[] getUrlPatterns() {
        return urlPatterns;
    }

    public void setUrlPatterns(String[] urlPatterns) {
        this.urlPatterns = urlPatterns;
    }
}