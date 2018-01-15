package com.my.course.notice.service;

import com.github.pagehelper.PageInfo;
import com.my.course.model.Announcement;
import com.my.course.model.CommResult;
import com.my.course.model.Notice;

import java.util.List;

/**
 * Created by lin.pingbin on 2018/1/4.
 */
public interface NoticeService {
    //查询公告
    CommResult<List<Announcement>> selectAnnouncementForStudent();
    //查询所有公告
    CommResult<PageInfo<Announcement>> selectAllAnnouncementForStudent();
    //分页查询所有公告
    CommResult<PageInfo<Announcement>> selectAllAnnouncementForStudent(String pageParams);
    //分页查询课程发给学生的通知
    CommResult<PageInfo<Notice>> selectAllCourseNotice(String pageparam);

    CommResult<PageInfo<Announcement>> selectAllAnnouncementForTeacher();
    CommResult<PageInfo<Announcement>> selectAllAnnouncementForTeacher(String pageParams);

    CommResult<List<Announcement>> selectAnnouncementForTeacher();
}
