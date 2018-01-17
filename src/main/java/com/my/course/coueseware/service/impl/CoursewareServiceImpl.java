package com.my.course.coueseware.service.impl;

import com.my.course.coueseware.dao.CoursewareDao;
import com.my.course.coueseware.service.CoursewareService;
import com.my.course.exception.BusinessRuntimeException;
import com.my.course.model.CommResult;
import com.my.course.model.Courseware;
import com.my.course.util.DownloadFile;
import com.my.course.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;


/**
 * Created by lin.pingbin on 2018/1/9.
 */
@Service
public class CoursewareServiceImpl implements CoursewareService{
    private static final Logger LOGGER = LoggerFactory.getLogger(CoursewareServiceImpl.class);
    @Autowired
    private CoursewareDao coursewareDao;
    @Override
    public void selectCoursewareById(String id) {
        Integer coursewareId= JacksonUtil.readValue(id,Integer.class);
        Courseware courseware = coursewareDao.selectCoursewareById(coursewareId);
        LOGGER.info("courseware info {}"+courseware);
        if (courseware!=null){
            try {
                DownloadFile.downloadFile(courseware.getFileAddress(),courseware.getCoursewareName());
            } catch (Exception e) {
                throw new BusinessRuntimeException("下载课件失败!"+e);
            }
        }

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
}
