package com.my.course.student.service;

import com.my.course.model.CommResult;
import com.my.course.model.Student;
import com.my.course.model.Teacher;

/**
 * Created by lin.pingbin on 2018/1/5.
 */
public interface StudentService {
    //查询student信息
    CommResult<Student> studentInfo(String id);
    //修改密码
    CommResult stuModityPw(String password);
    //查询课程列表
    CommResult selectCourseList(String username);
}
