package com.my.course.notice.web;

import com.github.pagehelper.PageInfo;
import com.my.course.model.Announcement;
import com.my.course.model.CommResult;
import com.my.course.model.Notice;
import com.my.course.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/stuAllAnnouncement")
    public CommResult<PageInfo<Announcement>> stuAllNoticeOfGet(){
        CommResult<PageInfo<Announcement>> commResult = new CommResult<>();
        commResult=noticeService.selectAllAnnouncementForStudent();
        return commResult;
    }
    @GetMapping("/teaAllAnnouncement")
    public CommResult<PageInfo<Announcement>> teaAllNoticeOfGet(){
        CommResult<PageInfo<Announcement>> commResult = new CommResult<>();
        commResult=noticeService.selectAllAnnouncementForTeacher();
        return commResult;
    }
    @PostMapping("/stuAllAnnouncement")
    public CommResult<PageInfo<Announcement>> stuAllNoticeOfPost(@RequestBody String pageParams){
        CommResult<PageInfo<Announcement>> commResult = new CommResult<>();
        commResult=noticeService.selectAllAnnouncementForStudent(pageParams);
        return commResult;
    }
    @PostMapping("/teaAllAnnouncement")
    public CommResult<PageInfo<Announcement>> teaAllNoticeOfPost(@RequestBody String pageParams){
        CommResult<PageInfo<Announcement>> commResult = new CommResult<>();
        commResult=noticeService.selectAllAnnouncementForTeacher(pageParams);
        return commResult;
    }
    @PostMapping("/stuAllCourseNotice")
    public CommResult stuAllCourseNotice(@RequestBody String pageparam){
        CommResult<PageInfo<Notice>> commResult = new CommResult<>();
        commResult=noticeService.selectAllCourseNotice(pageparam);
        return commResult;
    }
}
