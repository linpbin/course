package com.my.course.homework.service.impl;


import com.my.course.exception.BusinessRuntimeException;
import com.my.course.homework.dao.HomeworkDao;
import com.my.course.homework.service.HomeworkService;
import com.my.course.model.CommResult;
import com.my.course.model.Courseware;
import com.my.course.model.TeacherTask;
import com.my.course.util.DownloadFile;
import com.my.course.util.JacksonUtil;
import com.my.course.util.UploadFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Override
    public CommResult insertCourseTask(String courseId,String taskName, String taskDescribe, String taskDeadline, MultipartFile file) {
        CommResult commResult = new CommResult();
        TeacherTask teacherTask = new TeacherTask();
        teacherTask.setTaskName(taskName);
        teacherTask.setTaskDescribe(taskDescribe);
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(taskDeadline);
        } catch (ParseException e) {
            throw new BusinessRuntimeException("字符串转日期失败！");
        }
        teacherTask.setTaskDeadline(date);
        teacherTask.setCourseId(Integer.parseInt(courseId));
        String path="";
        File file1 = UploadFile.generateFile(path,file);

        if (taskName==null){
            commResult.setResultCode(1);
            commResult.setResultMsg("作业名字不能为空");
            commResult.setData(null);
        }
        if (taskDescribe==null){
            commResult.setResultCode(1);
            commResult.setResultMsg("作业描述不能为空");
            commResult.setData(null);
        }
        if (taskDeadline==null){
            commResult.setResultCode(1);
            commResult.setResultMsg("作业截至时间不能为空");
            commResult.setData(null);
        }
        return commResult;
    }
}
