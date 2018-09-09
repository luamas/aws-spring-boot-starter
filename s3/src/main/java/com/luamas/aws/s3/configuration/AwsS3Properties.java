package com.luamas.aws.s3.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "aws.s3")
@Getter
@Setter
public class AwsS3Properties {
    private Boolean enable = Boolean.TRUE;
    private Boolean isAws = Boolean.TRUE;
    private Boolean checkBucketNameExist = Boolean.TRUE;
    private String serviceEndpoint = "";
    private String region = "us-east-1";
    private String bucketName = "";
}
