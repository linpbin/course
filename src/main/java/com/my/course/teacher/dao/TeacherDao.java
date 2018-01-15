package com.my.course.teacher.dao;

import com.my.course.model.Course;
import com.my.course.model.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by lin.pingbin on 2018/1/9.
 */
@Mapper
public interface TeacherDao {
    @Select("select * from teacher where id=#{id}")
    Teacher selectTeacherById(@Param("id") Integer userId);
    @Select("select * from teacher where id=#{userName} and teacher_password=#{oldPassword}")
    Teacher selectTeacherByIdAndPassword(@Param("userName") Integer userName, @Param("oldPassword") String oldPassword);
    @Update("update teacher set teacher_password=#{newPassword} where id=#{userName}")
    int updateTeacherPw(@Param("userName") Integer userName,@Param("newPassword") String newPassword);
    @Select("select c.* from course c inner join teacher_course tc on tc.course_id=c.id where tc.teacher_id=#{userId}")
    List<Course> selectCourseListByTeacherId(@Param("userId") Integer userId);
}
