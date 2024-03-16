package com.snsproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

@Configuration
public class AWSSNSConfig {
	public static final String SECRET_KEY = "";//enter your secret key here
    public static final String ACCESS_KEY = "AKIAQEWEA442BEL2FMXJ";//enter your access key
	
	@Primary
	@Bean
	public AmazonSNSClient getSnsClient() {
	// Create an SNS client
		return (AmazonSNSClient) AmazonSNSClientBuilder.standard().withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider((AWSCredentials) new BasicAWSCredentials(ACCESS_KEY,SECRET_KEY)))
                .build();
	}

}
