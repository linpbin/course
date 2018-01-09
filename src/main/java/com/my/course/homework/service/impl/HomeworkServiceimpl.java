package com.my.course.homework.service.impl;


import com.my.course.exception.BusinessRuntimeException;
import com.my.course.homework.dao.HomeworkDao;
import com.my.course.homework.service.HomeworkService;
import com.my.course.model.Courseware;
import com.my.course.model.TeacherTask;
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
public class HomeworkServiceimpl implements HomeworkService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeworkServiceimpl.class);
    @Autowired
    private HomeworkDao homeworkDao;
    @Override
    public void selectTeacherTaskById(String id) {
        Integer teachertaskId= JacksonUtil.readValue(id,Integer.class);
        TeacherTask teacherTask = homeworkDao.selectTeacherTaskById(teachertaskId);
        LOGGER.info("teacherTask info {}"+teacherTask);
        if (teacherTask!=null){
            try {
                DownloadFile.downloadFile(teacherTask.getTeataskAddress(),teacherTask.getTaskName());
            } catch (Exception e) {
                throw new BusinessRuntimeException("下载作业失败!"+e);
            }
        }
    }
}
