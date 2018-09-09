package com.luamas.aws.s3.configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.luamas.aws.configuration.AwsAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

@Configuration
@AutoConfigureAfter(AwsAutoConfiguration.class)
@EnableConfigurationProperties(AwsS3Properties.class)
@ConditionalOnBean(AWSCredentialsProvider.class)
@ConditionalOnClass(AmazonS3.class)
@ConditionalOnProperty(prefix = "aws.s3", name = "enable", matchIfMissing = true)
public class AwsS3AutoConfiguration {
    @Autowired
    private AWSCredentialsProvider awsCredentialsProvider;
    @Autowired
    private AwsS3Properties awsS3Properties;
    @Bean
    public AmazonS3 amazonS3(){
        Assert.hasText(awsS3Properties.getServiceEndpoint(),"aws s3 serviceEndpoint not empty");
        Assert.hasText(awsS3Properties.getRegion(),"aws s3 region not empty");
        AmazonS3ClientBuilder amazonS3ClientBuilder = AmazonS3ClientBuilder.standard().withCredentials(awsCredentialsProvider);
        if (!Boolean.TRUE.equals(awsS3Properties.getIsAws())){
            amazonS3ClientBuilder.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsS3Properties.getServiceEndpoint(), awsS3Properties.getRegion()));
        } else {
            amazonS3ClientBuilder.setRegion(awsS3Properties.getRegion());
        }
        amazonS3ClientBuilder.withPathStyleAccessEnabled(true);
        return amazonS3ClientBuilder.build();
    }
}