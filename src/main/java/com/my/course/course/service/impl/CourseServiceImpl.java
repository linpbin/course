package com.my.course.course.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.course.course.dao.CourseDao;
import com.my.course.course.service.CourseService;
import com.my.course.model.*;
import com.my.course.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lin.pingbin on 2018/1/8.
 */
@Service
public class CourseServiceImpl implements CourseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);
    @Autowired
    private CourseDao courseDao;
    @Override
    public CommResult<Course> selectCourseInfo(String courseId) {
        String courseid= JacksonUtil.readValue(courseId,String.class);
        Integer id =Integer.parseInt(courseid);
        CommResult<Course> commResult = new CommResult<>();
        Course course = courseDao.selectCourseInfoById(id);
        Teacher teacher=courseDao.selectTeacherByCourseId(id);
        course.setTeacherId(teacher);
        LOGGER.info("select course info "+course);
        if (course!=null){
            commResult.setResultCode(0);
            commResult.setResultMsg("success");
            commResult.setData(course);
        }else {
            commResult.setResultCode(1);
            commResult.setResultMsg("查询失败！");
            commResult.setData(null);
        }
        return commResult;
    }

    @Override
    public CommResult<Teacher> selectCourseTeacher(String courseId) {
        String courseid= JacksonUtil.readValue(courseId,String.class);
        Integer id =Integer.parseInt(courseid);
        CommResult<Teacher> commResult = new CommResult<>();
        Teacher teacher = courseDao.selectCourseTeacherById(id);
        LOGGER.info("select course teacher info"+teacher);
        if (teacher!=null){
            commResult.setResultCode(0);
            commResult.setResultMsg("success");
            commResult.setData(teacher);
        }else {
            commResult.setResultCode(1);
            commResult.setResultMsg("查询失败！");
            commResult.setData(null);
        }
        return commResult;
    }

    @Override
    public CommResult<PageInfo<Courseware>> selectCourseware(String courseId) {
        PageDTO pageDTO= JacksonUtil.readValue(courseId,PageDTO.class);
        if (pageDTO.getPageSize()==null){
            pageDTO.setPageSize(5);
        }
        CommResult<PageInfo<Courseware>> commResult = new CommResult<>();
        PageHelper.startPage(pageDTO.getPageNo(),pageDTO.getPageSize());
        List<Courseware> coursewareList =courseDao.selectCourseware(pageDTO.getCourseId());
        PageInfo<Courseware> pageInfo = new PageInfo<>(coursewareList);
        LOGGER.info("select all courseware"+coursewareList);
        if (coursewareList!=null&&coursewareList.size()>0){
            commResult.setResultCode(0);
            commResult.setResultMsg("success");
            commResult.setData(pageInfo);
        }else {
            commResult.setResultCode(1);
            commResult.setResultMsg("暂无课件！");
            commResult.setData(null);
        }
        return commResult;
    }

    @Override
    public CommResult<PageInfo<TeacherTask>> selectCoursetask(String courseId) {
        System.out.println(courseId);
        PageDTO pageDTO= JacksonUtil.readValue(courseId,PageDTO.class);
        if (pageDTO.getPageSize()==null){
            pageDTO.setPageSize(5);
        }
        CommResult<PageInfo<TeacherTask>> commResult = new CommResult<>();
        PageHelper.startPage(pageDTO.getPageNo(),pageDTO.getPageSize());
        List<TeacherTask> teacherTaskList =courseDao.selectCourseTask(pageDTO.getCourseId());
        LOGGER.info("select all course task"+teacherTaskList);
        PageInfo<TeacherTask> pageInfo = new PageInfo<>(teacherTaskList);
        if (teacherTaskList!=null&&teacherTaskList.size()>0){
            commResult.setResultCode(0);
            commResult.setResultMsg("success");
            commResult.setData(pageInfo);
        }else {
            commResult.setResultCode(1);
            commResult.setResultMsg("暂无作业！");
            commResult.setData(null);
        }
        return commResult;
    }

    @Override
    public CommResult<PageInfo<StudentDTO>> selectStudentListByCourseId(String pageparam) {
        PageDTO pageDTO = JacksonUtil.readValue(pageparam,PageDTO.class);
        if (pageDTO.getPageSize()==null){
            pageDTO.setPageSize(10);
        }
        CommResult<PageInfo<StudentDTO>> commResult = new CommResult<>();
        PageHelper.startPage(pageDTO.getPageNo(),pageDTO.getPageSize());
        List<StudentDTO> studentList =courseDao.selectStudentListByCourseId(pageDTO.getCourseId());
        PageInfo<StudentDTO> pageInfo = new PageInfo<>(studentList);
        LOGGER.info("studentlist course {}"+pageInfo);
        if (studentList.size()>0 && studentList!=null){
            commResult.setResultCode(0);
            commResult.setResultMsg("success");
            commResult.setData(pageInfo);
        }else {
            commResult.setResultCode(1);
            commResult.setResultMsg("failure");
            commResult.setData(null);
        }
        return  commResult;
    }
}
