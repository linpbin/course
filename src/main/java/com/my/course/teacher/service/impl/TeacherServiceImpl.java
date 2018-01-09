package com.my.course.teacher.service.impl;

import com.my.course.model.*;
import com.my.course.student.service.impl.StudentServiceImpl;
import com.my.course.teacher.dao.TeacherDao;
import com.my.course.teacher.service.TeacherService;
import com.my.course.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lin.pingbin on 2018/1/9.
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherServiceImpl.class);
    @Autowired
    private TeacherDao teacherDao;
    @Override
    public CommResult<Teacher> teacherInfo(String id) {
        Integer userId= JacksonUtil.readValue(id,Integer.class);
        Teacher teacher = teacherDao.selectTeacherById(userId);
        LOGGER.info("TeacherServiceImpl select teacher info"+teacher.toString());
        CommResult<Teacher> commResult = new CommResult<>();
        if (teacher!=null){
            commResult.setData(teacher);
            commResult.setResultMsg("success");
            commResult.setResultCode(0);
        }else{
            commResult.setData(null);
            commResult.setResultCode(1);
            commResult.setResultMsg("查询失败");
        }
        return commResult;
    }

    @Override
    public CommResult teaModityPw(String password) {
        CommResult commResult = new CommResult();
        PasswordDTO passwordDTO =JacksonUtil.readValue(password,PasswordDTO.class);
        LOGGER.info("teaModityPw"+password);
        LOGGER.info("teaModityPw"+passwordDTO);
        Teacher teacher =teacherDao.selectTeacherByIdAndPassword(passwordDTO.getUserName(),passwordDTO.getOldPassword());
        if (teacher!=null){
            int result =teacherDao.updateTeacherPw(passwordDTO.getUserName(),passwordDTO.getNewPassword());
            if (result!=0){
                commResult.setResultMsg("success");
                commResult.setResultCode(0);
                commResult.setData(null);
            }else {
                commResult.setResultMsg("更新失败!");
                commResult.setResultCode(1);
                commResult.setData(null);
            }

        }else {
            commResult.setResultCode(1);
            commResult.setData(null);
            commResult.setResultMsg("旧密码错误!");
        }
        return commResult;
    }

    @Override
    public CommResult selectCourseList(String username) {
        CommResult commResult = new CommResult();
        String userId=JacksonUtil.readValue(username,String.class);
        List<Course> courseList = teacherDao.selectCourseListByTeacherId(Integer.parseInt(userId));
            if (courseList!=null&&courseList.size()>0){
                commResult.setResultCode(0);
                commResult.setResultMsg("success");
                commResult.setData(courseList);
                LOGGER.info("teacherserivceimpl courseList{}"+courseList.get(0));
            }else {
                commResult.setData(null);
                commResult.setResultMsg("暂无课程!");
                commResult.setResultCode(1);
            }
        return commResult;
    }
}
