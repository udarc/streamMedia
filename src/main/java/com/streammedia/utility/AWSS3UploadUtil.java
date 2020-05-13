package com.streammedia.utility;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.Part;
import java.io.File;

/**
 * The type Awss 3 upload util.
 * Responsible of authenticating user and upload to S3.
 *
 * @author Jeanne
 * @version 1.0
 * @since 09 -05-2020
 */
@Log4j2
public class AWSS3UploadUtil {
    /**
     * Upload to awss 3 string.
     *
     * @param part            the part
     * @param accessKeyId     the access key id
     * @param secretAccessKey the secret access key
     * @param region          the region
     * @param bucketName      the bucket name
     * @param fileObjKeyName  the file obj key name
     * @param fileToUpload    the file to upload
     * @return the string https://stackoverflow.com/questions/21487066/get-urllink-of-a-public-s3-object-programmatically  https://stackoverflow.com/questions/54610830/how-come-doesobjectexist-and-listobjects-do-not-agree-on-s3s-java-api  https://stackoverflow.com/questions/7763239/delete-files-directories-and-buckets-in-amazon-s3-java
     */
    public String uploadToAWSS3(Part part, String accessKeyId, String secretAccessKey, String region, String bucketName, String fileObjKeyName, String fileToUpload) {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();

        if (s3Client.doesObjectExist(bucketName, fileObjKeyName)) {

            s3Client.deleteObject(new DeleteObjectRequest(bucketName, fileObjKeyName));
        }
        PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, new File(fileToUpload));
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(part.getContentType());
        request.setMetadata(metadata);
        request.setCannedAcl(CannedAccessControlList.PublicRead);
        PutObjectResult result = s3Client.putObject(request);
        log.debug("S3 Result: " + result.getContentMd5());
        return "https://" + bucketName + ".s3." + Regions.US_EAST_2.getName() + ".amazonaws.com/" + fileObjKeyName;
    }

}
