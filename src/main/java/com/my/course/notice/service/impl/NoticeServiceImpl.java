package com.my.course.notice.service.impl;

import com.my.course.model.Announcement;
import com.my.course.model.CommResult;
import com.my.course.model.Notice;
import com.my.course.notice.dao.NoticeDao;
import com.my.course.notice.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lin.pingbin on 2018/1/4.
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeServiceImpl.class);
    @Autowired
    private NoticeDao noticeDao;
    @Override
    public CommResult<List<Announcement>> selectAnnouncementForStudent() {
        LOGGER.info("start select announcement for student{}");
        CommResult<List<Announcement>> commResult = new CommResult<>();
        List<Announcement> announcementList =noticeDao.selectAnnouncementForStudent();
        if (announcementList.size()>0&&announcementList!=null){
            commResult.setData(announcementList);
            commResult.setResultCode(0);
        }else {
            commResult.setResultCode(1);
            commResult.setResultMsg("暂无公告!");
        }
        return commResult;
    }
}
