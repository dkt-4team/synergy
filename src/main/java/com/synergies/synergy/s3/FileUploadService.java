package com.synergies.synergy.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    //bucketName
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String s3Region;

    private final AmazonS3 amazonS3;

    public void uploadFile(String fileName, boolean manager, MultipartFile file) {
        String contentType = file.getContentType();
        System.out.println("contentType: " +contentType);
        String uploadPath;

        if(manager) {
            uploadPath = bucket + "/admin/";
        }
        else {
            uploadPath = bucket + "/student/";
        }

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            byte[] bytes = IOUtils.toByteArray(file.getInputStream());
            metadata.setContentLength(bytes.length);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

            amazonS3.putObject(new PutObjectRequest(bucket, uploadPath+fileName, byteArrayInputStream, metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            byteArrayInputStream.close();
        } catch (AmazonServiceException | IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteObject(boolean isAdmin, String storedFileName) throws AmazonServiceException {
        String filePath;
        if(isAdmin)
            filePath = "/admin/"+storedFileName;
        else
            filePath = "/student/"+storedFileName;

        amazonS3.deleteObject(new DeleteObjectRequest(bucket, bucket+filePath));
    }

    public String getUrl() {
        return "https://"+bucket+".s3."+s3Region+".amazonaws.com/"+bucket;
    }
}
