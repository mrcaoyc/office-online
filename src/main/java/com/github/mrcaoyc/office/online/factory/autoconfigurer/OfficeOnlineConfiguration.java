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
    private String host;

    private String domainServerHost;
}
