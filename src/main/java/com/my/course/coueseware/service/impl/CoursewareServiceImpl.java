package com.my.course.coueseware.service.impl;

import com.my.course.coueseware.dao.CoursewareDao;
import com.my.course.coueseware.service.CoursewareService;
import com.my.course.exception.BusinessRuntimeException;
import com.my.course.model.Courseware;
import com.my.course.util.DownloadFile;
import com.my.course.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
}
