package com.my.course.coueseware.service.impl;

import com.my.course.coueseware.dao.CoursewareDao;
import com.my.course.coueseware.service.CoursewareService;
import com.my.course.exception.BusinessRuntimeException;
import com.my.course.model.CommResult;
import com.my.course.model.Courseware;
import com.my.course.util.DownloadFile;
import com.my.course.util.JacksonUtil;
import com.my.course.util.UploadFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by lin.pingbin on 2018/1/9.
 */
@Service
public class CoursewareServiceImpl implements CoursewareService{
    private static final Logger LOGGER = LoggerFactory.getLogger(CoursewareServiceImpl.class);
    @Autowired
    private CoursewareDao coursewareDao;
    @Override
    public Courseware selectCoursewareById(String id) {
        Integer coursewareId= JacksonUtil.readValue(id,Integer.class);
        Courseware courseware = coursewareDao.selectCoursewareById(coursewareId);
        LOGGER.info("courseware info {}"+courseware);
       return  courseware;

    }

    @Override
    public CommResult deleteCoursewareById(String id) {
        CommResult commResult = new CommResult();
        Integer courseId = Integer.parseInt(JacksonUtil.readValue(id,String.class));
        Courseware courseware = coursewareDao.selectCoursewareById(courseId);
        if (courseware!=null){
            int result= coursewareDao.deleteCoursewareById(courseId);
            if (result!=0){
                if (courseware.getFileAddress()!=null){
                    File targetFile = new File(courseware.getFileAddress());
                    if (targetFile.exists()){
                        targetFile.delete();
                    }
                }
                commResult.setData(null);
                commResult.setResultCode(0);
                commResult.setResultMsg("success");
            }else {
                commResult.setResultMsg("failure");
                commResult.setResultCode(1);
                commResult.setData(null);
            }
        }else {
            commResult.setData(null);
            commResult.setResultCode(0);
            commResult.setResultMsg("success");
        }
        return  commResult;
    }

    @Override
    public CommResult reUpdateCourseware(String coursewareId, MultipartFile file){
        CommResult commResult = new CommResult();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String realPath=request.getSession().getServletContext().getRealPath("/");

        try {
            if (file!=null){
                String realPath =ResourceUtils.getURL("classpath:").getPath();
                File file1 = UploadFile.generateFile(realPath,file);
                if (file1!=null){
                    int result = coursewareDao.updateFileAddress(file1.getAbsolutePath(),Integer.parseInt(coursewareId));
                    if (result!=0) {
                        commResult.setData(null);
                        commResult.setResultMsg("success");
                        commResult.setResultCode(0);
                    }else {
                        commResult.setData(null);
                        commResult.setResultCode(1);
                        commResult.setResultMsg("文件路径保存失败!");
                    }
                }else {
                    commResult.setData(null);
                    commResult.setResultCode(1);
                    commResult.setResultMsg("文件为空!");
                }
            }else {
                commResult.setData(null);
                commResult.setResultCode(1);
                commResult.setResultMsg("文件为空!!");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return  commResult;


    }
}
