package com.my.course.notice.service;

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
}
