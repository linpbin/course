package com.my.course.homework.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.course.exception.BusinessRuntimeException;
import com.my.course.homework.dao.HomeworkDao;
import com.my.course.homework.service.HomeworkService;
import com.my.course.model.*;
import com.my.course.util.JacksonUtil;
import com.my.course.util.UploadFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lin.pingbin on 2018/1/9.
 */
@Service
public class HomeworkServiceimpl implements HomeworkService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeworkServiceimpl.class);
    @Autowired
    private HomeworkDao homeworkDao;
    @Override
    public TeacherTask selectTeacherTaskById(String id) {
        Integer teachertaskId= JacksonUtil.readValue(id,Integer.class);
        TeacherTask teacherTask = homeworkDao.selectTeacherTaskById(teachertaskId);
       return teacherTask;
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
        String realPath = null;
        try {
            realPath = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            throw new BusinessRuntimeException("HomeworkServiceImpl 获取路径失败！");
        }
        File file1 = UploadFile.generateFile(realPath,file);
        teacherTask.setTeataskAddress(file1.getAbsolutePath());
        LOGGER.info("HomeWork Path{}"+file1.getAbsolutePath());
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
        int result = homeworkDao.insertHomework(teacherTask);
        if (result!=0){
            commResult.setData(null);
            commResult.setResultMsg("success");
            commResult.setResultCode(0);
        }else {
            commResult.setResultCode(1);
            commResult.setResultMsg("插入失败！");
            commResult.setData(null);
        }
        return commResult;
    }

    @Override
    public CommResult commitHomework(String coursetaskId, MultipartFile file,String studentId) {
        CommResult commResult = new CommResult();
        TeacherTask teacherTask = homeworkDao.selectTeacherTaskById(Integer.parseInt(coursetaskId));
        Date date = new Date();
        int va = date.compareTo(teacherTask.getTaskDeadline());
        if (va>1){
            commResult.setData(null);
            commResult.setResultMsg("已超过截止时间");
            commResult.setResultCode(1);
        }else {
            try {
                if (file!=null){
                    String realPath =ResourceUtils.getURL("classpath:").getPath();
                    File file1 = UploadFile.generateFile(realPath,file);
                    if (file1!=null){
                        StudentTask studentTask = new StudentTask();
                        studentTask.setCourseId(teacherTask.getCourseId());
                        studentTask.setStutaskAddress(file1.getAbsolutePath());
                        studentTask.setUploadTime(new Date());
                        studentTask.setTaskId(teacherTask.getId());
                        studentTask.setTaskName(teacherTask.getTaskName());
                        studentTask.setStudentId(Integer.parseInt(studentId));
                        int result = homeworkDao.insertStudentTask(studentTask);
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
        }

        return  commResult;
    }

    @Override
    public CommResult<PageInfo<StudentTask>> selectStudentTask(String pageparam) {
        CommResult<PageInfo<StudentTask>> commResult = new CommResult<>();
        StudentJson studentJson = JacksonUtil.readValue(pageparam,StudentJson.class);
        if (studentJson.getPageSize()==null){
            studentJson.setPageSize(5);
        }
        PageHelper.startPage(studentJson.getPageNo(),studentJson.getPageSize());
        List<StudentTask> studentTaskList = homeworkDao.selectStudentTaskById(studentJson.getStudentId(),studentJson.getCourseId());
        PageInfo<StudentTask> pageInfo = new PageInfo<>(studentTaskList);
        if (studentTaskList!=null && studentTaskList.size()>0){
            commResult.setData(pageInfo);
            commResult.setResultCode(0);
            commResult.setResultMsg("success");
        }else {
            commResult.setResultMsg("暂未提交作业");
            commResult.setResultCode(1);
            commResult.setData(null);
        }

        return commResult;
    }
}
