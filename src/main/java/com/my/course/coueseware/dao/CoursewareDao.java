package com.my.course.coueseware.dao;

import com.my.course.model.Courseware;
import com.my.course.model.TeacherTask;
import org.apache.ibatis.annotations.*;

/**
 * Created by lin.pingbin on 2018/1/9.
 */
@Mapper
public interface CoursewareDao {
    @Select("select * from courseware where id=#{coursewareId}")
    Courseware selectCoursewareById(@Param("coursewareId") Integer coursewareId);
    @Delete("delete from courseware where id=#{coursewareId}")
    int deleteCoursewareById(@Param("coursewareId") Integer coursewareId);
    @Update("update courseware set file_address=#{realPath} where id=#{coursewareId}")
    int updateFileAddress(@Param("realPath") String realPath,@Param("coursewareId") Integer coursewareId);
}
