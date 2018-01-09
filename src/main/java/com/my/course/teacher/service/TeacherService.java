package com.my.course.teacher.service;

import com.my.course.model.CommResult;
import com.my.course.model.Student;
import com.my.course.model.Teacher;

/**
 * Created by lin.pingbin on 2018/1/9.
 */
public interface TeacherService {
    CommResult<Teacher> teacherInfo(String id);

    CommResult teaModityPw(String password);

    CommResult selectCourseList(String username);
}
