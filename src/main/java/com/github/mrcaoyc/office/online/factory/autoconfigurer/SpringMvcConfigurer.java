package com.github.mrcaoyc.office.online.factory.autoconfigurer;

import com.github.mrcaoyc.office.online.factory.filter.AuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author CaoYongCheng
 */
@Configuration
public class SpringMvcConfigurer implements WebMvcConfigurer {
    private final JwtTokenProperties jwtTokenProperties;

    public SpringMvcConfigurer(JwtTokenProperties jwtTokenProperties) {
        this.jwtTokenProperties = jwtTokenProperties;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationFilter())
                .addPathPatterns("/wopi/files/{name}");
    }

    @Bean
    public AuthorizationFilter authorizationFilter() {
        return new AuthorizationFilter(jwtTokenProperties);
    }
}
