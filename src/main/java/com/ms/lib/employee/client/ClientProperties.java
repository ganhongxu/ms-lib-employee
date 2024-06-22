package com.ms.lib.employee.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "com.ms.lib.organization")
@Data
public class ClientProperties {

    private String organizationUrl;
    private String organizationHttpMethod;
}
