package com.luamas.aws.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws")
@Getter
@Setter
public class AwsProperties {
    private Boolean enable = Boolean.TRUE;
    private String accessKeyId = "";
    private String secretAccessKeyId = "";
}
