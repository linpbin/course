package com.my.course.util;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;

/**
 * Created by lin.pingbin on 2018/1/9.
 */
public class DownloadFile {
    public static ResponseEntity<byte[]> downloadFile(String filePath, String fileName)throws Exception{
        File file =new File(filePath);
        HttpHeaders headers=new HttpHeaders();
        headers.setContentDispositionFormData("attachment",fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
    }
}
