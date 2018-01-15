package com.my.course.notice.dao;

import com.my.course.model.Announcement;
import com.my.course.model.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by lin.pingbin on 2018/1/4.
 */
@Mapper
public interface NoticeDao {
    //查询通知学生的公告
    @Select("select context,create_time from announcement where is_student=1 order by create_time desc limit 5")
    List<Announcement> selectAnnouncementForStudent();
    //查询所有通知学生的公告
    @Select("select context,create_time from announcement where is_student=1 order by create_time")
    List<Announcement> selectAllAnnouncementForStudent();
    //查询所有课程通知
    @Select("select n.* from notice n inner join course_notice cn on cn.notice_id=n.id where cn.course_id=#{courseId}")
    List<Notice> selectAllCourseNotice(@Param("courseId") Integer id);
    @Select("select context,create_time from announcement where is_teacher=1 order by create_time")
    List<Announcement> selectAllAnnouncementForTeacher();
    @Select("select context,create_time from announcement where is_teacher=1 order by create_time desc limit 5")
    List<Announcement> selectAnnouncementForTeacher();
}
