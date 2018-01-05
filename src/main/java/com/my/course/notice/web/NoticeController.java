package com.my.course.notice.web;

import com.my.course.model.Announcement;
import com.my.course.model.CommResult;
import com.my.course.model.Notice;
import com.my.course.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lin.pingbin on 2018/1/4.
 */
@RestController
@CrossOrigin
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    @GetMapping("/stuAnnouncement")
    public CommResult<List<Announcement>> stuNotice(){
        CommResult<List<Announcement>> commResult = new CommResult<>();
        commResult=noticeService.selectAnnouncementForStudent();
        return commResult;
    }
}
