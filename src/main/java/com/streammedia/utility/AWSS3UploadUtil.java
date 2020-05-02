package com.streammedia.utility;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.log4j.Log4j2;

import java.util.Properties;

@Log4j2
public class AWSS3UploadUtil  implements PropertiesLoader {
    private Properties properties;

    {
        try {
            properties = loadProperties("aws.properties");
        } catch (Exception e) {
            log.error(e);
        }
    }
    public AmazonS3 getS3Client(){
        BasicAWSCredentials credentials =  new BasicAWSCredentials(
                properties.getProperty("aws.secret.access.key"), properties.getProperty("aws.access.key.id"));
        final AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion("us-east-2")
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();

        return s3Client;
    }

}
