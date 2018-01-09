package com.my.course.course.dao;


import com.my.course.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by lin.pingbin on 2018/1/8.
 */
@Mapper
public interface CourseDao {


    //根据课程Id查询课程信息
    @Select("select c.* from course c where c.id=#{courseId}")
    Course selectCourseInfoById(@Param("courseId") Integer id);
    @Select("select t.teacher_name from teacher t inner join teacher_course tc on tc.teacher_id=t.id where tc.course_id=#{courseId}")
    Teacher selectTeacherByCourseId(@Param("courseId") Integer id);
    //根据课程Id查询任课老师信息
    @Select("select t.* from teacher t inner join teacher_course tc on tc.teacher_id=t.id where tc.course_id=#{courseId}")
    Teacher selectCourseTeacherById(@Param("courseId") Integer id);
    //根据课程Id查询课件列表
    @Select("select * from courseware where course_id=#{courseId}")
    List<Courseware> selectCourseware(@Param("courseId") Integer id);
    //根据课程Id查询作业列表
    @Select("select * from teacher_task where course_id=#{courseId}")
    List<TeacherTask> selectCourseTask(@Param("courseId") Integer id);
}
