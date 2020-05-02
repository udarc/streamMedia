package com.streammedia.controller;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.streammedia.utility.JavaHelperMethods;
import com.streammedia.utility.PropertiesLoader;
import lombok.extern.log4j.Log4j2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

@WebServlet(
        urlPatterns = {"/aws-s3"}
)
@Log4j2
public class UploadS3 extends HttpServlet implements PropertiesLoader {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/uploadS3.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Properties properties = null;
        try {
            properties = loadProperties("/aws.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Part part = req.getPart("file");
        String fileName =  part.getSubmittedFileName().toLowerCase();
        log.info(fileName);
        if(fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith("jpeg")){

            String accessKeyId =properties.getProperty("aws.secret.access.key");
            String secretAccessKey = properties.getProperty("aws.access.key.id");
            String region = properties.getProperty("aws.region");
            String bucketName = properties.getProperty("aws.bucket.name");
            String subdirectory = "images/username/";
            log.info(subdirectory);

//                todo https://docs.aws.amazon.com/AmazonS3/latest/dev/llJavaUploadFile.html
            //AWS Access Key ID and Secret Access Key
            BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);

            //This class connects to AWS S3 for us

            AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(region)
                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).build();
            boolean isBucketExist =s3Client.doesBucketExist(bucketName);
            if(!isBucketExist) {
                s3Client.createBucket(bucketName);
            }
            //Specify the file's size
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(part.getSize());
            String filePartName  = JavaHelperMethods.extractFileName(part);
            //Create the upload request, giving it a bucket name, subdirectory, filename, input stream, and metadata
            PutObjectRequest uploadRequest = new PutObjectRequest(bucketName, subdirectory, new File(filePartName));
            //Set Metadata
            uploadRequest.setMetadata(metadata);
            //Make it public so we can use it as a public URL on the internet
            uploadRequest.setCannedAcl(CannedAccessControlList.PublicRead);

            //Upload the file. This can take a while for big files!
            s3Client.putObject(uploadRequest);
            //Create a URL using the bucket, subdirectory, and file name
            String fileUrl = "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + subdirectory + "/" + filePartName;
        } else {
            resp.getOutputStream().println("<p>Please only upload JPG or PNG files.</p>");
        }
        resp.sendRedirect("aws-s3");
    }
}
