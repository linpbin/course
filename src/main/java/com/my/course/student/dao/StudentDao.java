package com.my.course.student.dao;

import com.my.course.model.Clazz;
import com.my.course.model.Course;
import com.my.course.model.Student;
import com.my.course.model.Teacher;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * Created by lin.pingbin on 2018/1/5.
 */
@Mapper
public interface StudentDao {
    @Select("select * from student where id=#{id}")
    Student selectStudentById(@Param("id") Integer id);
    @Select("select c.* from clazz c inner join student_clazz sc on sc.clazz_id=c.id where sc.student_id=#{id}")
    Clazz selectClazzByStudentId(@Param("id") Integer id);
    @Select("select * from student where id=#{userName} and student_password=#{oldPassword}")
    Student selectStudentByIdAndPassword(@Param("userName") Integer userName, @Param("oldPassword") String oldPassword);
    @Update("update student set student_password=#{newPassword} where id=#{userName}")
    int updateStudnetPw(@Param("userName") Integer userName, @Param("newPassword") String newPassword);
    @Select("select c.* from course c inner join clazz_course cc on cc.course_id=c.id inner join clazz zz on zz.id=cc.clazz_id where zz.id=#{clazzId}")
    List<Course> selectCourseListByClazzId( @Param("clazzId") Integer clazzId);
    @Select("select t.teacher_name from teacher t inner join teacher_course tc on tc.teacher_id=t.id where tc.course_id=#{courseId}")
    Teacher selectTeacherByCourseId(@Param("courseId") Integer courseId);
}
