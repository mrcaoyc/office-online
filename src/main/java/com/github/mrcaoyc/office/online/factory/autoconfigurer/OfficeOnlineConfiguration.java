package com.github.mrcaoyc.office.online.factory.autoconfigurer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author CaoYongCheng
 */
@Configuration
@ConfigurationProperties(prefix = "office-online")
@Data
public class OfficeOnlineConfiguration {
    /**
     * wopi安装主机地址
     */
    private String host;

    /**
     * 域服务器地址
     */
    private String domainServerHost;

    /**
     * 指定referer，解决防盗链问题
     */
    private String referer;

    /**
     * 单位（秒）
     */
    private Long fileCacheExpires = 3600 * 24L;
}
