package com.my.course.course.service;

import com.github.pagehelper.PageInfo;
import com.my.course.model.*;

import java.util.List;

/**
 * Created by lin.pingbin on 2018/1/8.
 */
public interface CourseService {

    //查询课程信息
    CommResult<Course> selectCourseInfo(String courseId);
    //查询任课老师
    CommResult<Teacher> selectCourseTeacher(String courseId);
    //查询课件列表
    CommResult<PageInfo<Courseware>> selectCourseware(String courseId);
    //查询课程作业列表
    CommResult<PageInfo<TeacherTask>> selectCoursetask(String pageparam);

    CommResult<PageInfo<StudentDTO>> selectStudentListByCourseId(String pageparam);
}
