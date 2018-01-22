package com.my.course.util;

import com.my.course.exception.BusinessRuntimeException;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;
/**
 * Created by lin.pingbin on 2018/1/18.
 */
public class UploadFile {
    //    文件存放目录
//    private String fileRealPath;
//    private MultipartFile multipartFile;
//    public UploadFile(String fileRealPath,MultipartFile multipartFile){
//        this.fileRealPath=fileRealPath;
//        this.multipartFile=multipartFile;
//    }
    public UploadFile(){}
    //生成文件
    public static File generateFile(String realPath,MultipartFile file){

        Calendar calendar = Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        String filePath=realPath+year+month+"\\";
        File fileDir=new File(filePath);

        if (!fileDir.isDirectory()){
            fileDir.mkdir();
        }
        if(file!=null&&file.getOriginalFilename()!=null&&file.getOriginalFilename().length()>0){
            //获取文件原名
            String fileOriginalFilename=file.getOriginalFilename();

            //生成随机数
            String newFilename =year+""+month+ UUID.randomUUID()+
                    fileOriginalFilename.substring(fileOriginalFilename.lastIndexOf("."));
            //生成文件
            File newFile = new File(filePath+newFilename);
            try {
                file.transferTo(newFile);
            } catch (IOException e) {
                throw new BusinessRuntimeException("文件转换失败！！！");
            }
            return newFile;
        }
        return null;
    }

}
