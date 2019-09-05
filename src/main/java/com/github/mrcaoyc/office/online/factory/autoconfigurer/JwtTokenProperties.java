package com.github.mrcaoyc.office.online.factory.autoconfigurer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author CaoYongCheng
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "mrcaoyc.jwt")
public class JwtTokenProperties {
    /**
     * 令牌签名密匙
     */
    private String secret = "d070d51bc7e5bab700a471e9515cd788";

    /**
     * token类型
     */
    private String type = "Bearer";

    /**
     * 加密算法
     */
    private String algorithm = "HS256";

    /**
     * 过期时间（单位秒）
     */
    private Long expiresTime = 5 * 60L;
}
