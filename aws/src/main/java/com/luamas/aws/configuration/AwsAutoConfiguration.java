package com.luamas.aws.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties(AwsProperties.class)
@ConditionalOnClass(AWSCredentialsProvider.class)
@ConditionalOnProperty(prefix = "aws", name = "enable", matchIfMissing = true)
public class AwsAutoConfiguration {
    @Autowired
    private AwsProperties awsProperties;

    @Bean
    public AWSCredentialsProvider awsCredentialsProvider(){
        AWSCredentials credentials = new BasicAWSCredentials(awsProperties.getAccessKeyId(), awsProperties.getSecretAccessKeyId());
        return new AWSStaticCredentialsProvider(credentials);
    }


}