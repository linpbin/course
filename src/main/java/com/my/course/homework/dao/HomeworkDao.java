package com.my.course.homework.dao;

import com.my.course.model.TeacherTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by lin.pingbin on 2018/1/9.
 */
@Mapper
public interface HomeworkDao {
    @Select("select * from teacher_task where id=#{teachertaskId}")
    TeacherTask selectTeacherTaskById(@Param("teachertaskId") Integer teachertaskId);
}
