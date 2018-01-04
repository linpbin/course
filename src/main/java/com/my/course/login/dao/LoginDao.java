package com.my.course.login.dao;

import com.my.course.model.Administrators;
import com.my.course.model.Student;
import com.my.course.model.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by lin.pingbin on 2018/1/4.
 */
@Mapper
public interface LoginDao {
    @Select("select * from student where id=#{username} and student_password=#{password}")
    Student studentLogin(@Param("username") Integer username,
                         @Param("password") String password);
    //根据用户名和密码查找教师，以执行登录验证操作
    @Select("select * from teacher where id=#{username} and teacher_password=#{password}")
    Teacher teacherLogin(@Param("username") Integer username,
                                         @Param("password") String password);
    //根据用户名和密码查找管理员，以执行登录验证操作
    Administrators adminLogin(@Param("username") Integer username,
                                              @Param("password") String password);
}
