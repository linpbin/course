package com.my.course.student.service.impl;

import com.my.course.model.*;
import com.my.course.student.dao.StudentDao;
import com.my.course.student.service.StudentService;
import com.my.course.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lin.pingbin on 2018/1/5.
 */
@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Autowired
    private StudentDao studentDao;

    @Override
    public CommResult stuModityPw(String password) {
        CommResult commResult = new CommResult();
        PasswordDTO passwordDTO =JacksonUtil.readValue(password,PasswordDTO.class);
        LOGGER.info("stuModityPw"+password);
        LOGGER.info("stuModityPw"+passwordDTO);
        Student student =studentDao.selectStudentByIdAndPassword(passwordDTO.getUserName(),passwordDTO.getOldPassword());
        if (student!=null){
            int result =studentDao.updateStudnetPw(passwordDTO.getUserName(),passwordDTO.getNewPassword());
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
        Clazz clazz =studentDao.selectClazzByStudentId(Integer.parseInt(userId));
        if (clazz!=null){
            List<Course> courseList = studentDao.selectCourseListByClazzId(clazz.getId());
            if (courseList!=null&&courseList.size()>0){
                for (int i=0;i<courseList.size();i++){
                    Teacher teacher = studentDao.selectTeacherByCourseId(courseList.get(i).getId());
                    courseList.get(i).setTeacherId(teacher);
                }
                commResult.setResultCode(0);
                commResult.setResultMsg("success");
                commResult.setData(courseList);
                LOGGER.info("studentserivceimpl courseList{}"+courseList.get(0));
            }else {
                commResult.setData(null);
                commResult.setResultMsg("暂无课程!");
                commResult.setResultCode(1);
            }
        }else {
            commResult.setData(null);
            commResult.setResultMsg("暂无课程!");
            commResult.setResultCode(1);
        }
        return commResult;
    }

    @Override
    public CommResult<Student> studentInfo(String id) {
        Integer userId=JacksonUtil.readValue(id,Integer.class);
        Student student = studentDao.selectStudentById(userId);
        Clazz clazz =studentDao.selectClazzByStudentId(userId);
        student.setClazz(clazz);
        LOGGER.info("StudentServiceImpl select student info"+student.toString());
        CommResult<Student> commResult = new CommResult<>();
        if (student!=null){
            commResult.setData(student);
            commResult.setResultMsg("success");
            commResult.setResultCode(0);
        }else{
            commResult.setData(null);
            commResult.setResultCode(1);
            commResult.setResultMsg("查询失败");
        }
        return commResult;
    }
}
