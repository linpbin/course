package com.my.course.notice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.course.exception.BusinessRuntimeException;
import com.my.course.model.Announcement;
import com.my.course.model.CommResult;
import com.my.course.model.Notice;
import com.my.course.model.PageDTO;
import com.my.course.notice.dao.NoticeDao;
import com.my.course.notice.service.NoticeService;
import com.my.course.util.JacksonUtil;
import com.my.course.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            commResult.setResultMsg("success");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i=0;i<announcementList.size();i++){
                String stringTime =format.format(announcementList.get(i).getCreateTime());
                announcementList.get(i).setStringTime(stringTime);
            }

        }else {
            commResult.setResultCode(1);
            commResult.setResultMsg("暂无公告!");
            commResult.setData(null);
        }
        return commResult;
    }

    @Override
    public CommResult<PageInfo<Announcement>> selectAllAnnouncementForStudent() {
        LOGGER.info("start select all announcement for student{}");
        CommResult<PageInfo<Announcement>> commResult = new CommResult<>();
        PageHelper.startPage(1,5);
        List<Announcement> announcementList =noticeDao.selectAllAnnouncementForStudent();
        PageInfo<Announcement> page = new PageInfo<>(announcementList);
        LOGGER.info("select announcement for stu result"+page);
        if (page.getList()!=null && page.getList().size()>0){
            commResult.setData(page);
            commResult.setResultCode(0);
            commResult.setResultMsg("success");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i=0;i<announcementList.size();i++){
                String stringTime =format.format(announcementList.get(i).getCreateTime());
                announcementList.get(i).setStringTime(stringTime);
            }

        }else {
            commResult.setResultCode(1);
            commResult.setResultMsg("暂无公告!");
            commResult.setData(null);
        }
        return commResult;
    }

    @Override
    public CommResult<PageInfo<Announcement>> selectAllAnnouncementForStudent(String pageParams) {
        LOGGER.info("start select announcement for student{}");
        PageDTO pageDTO = JacksonUtil.readValue(pageParams,PageDTO.class);
        if (pageDTO.getPageSize()==null){
            pageDTO.setPageSize(5);
        }
        LOGGER.info("pageDto{}"+pageDTO.getPageNo()+" "+pageDTO.getPageSize());
        CommResult<PageInfo<Announcement>> commResult = new CommResult<>();
        PageHelper.startPage(pageDTO.getPageNo(),pageDTO.getPageSize());
        List<Announcement> announcementList =noticeDao.selectAllAnnouncementForStudent();
        PageInfo<Announcement> pageInfo = new PageInfo<>(announcementList);
        if (announcementList.size()>0&&announcementList!=null){
            commResult.setData(pageInfo);
            commResult.setResultCode(0);
            commResult.setResultMsg("success");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i=0;i<announcementList.size();i++){
                String stringTime =format.format(announcementList.get(i).getCreateTime());
                announcementList.get(i).setStringTime(stringTime);
            }

        }else {
            commResult.setResultCode(1);
            commResult.setResultMsg("暂无公告!");
            commResult.setData(null);
        }
        return commResult;
    }
    @Override
    public CommResult<PageInfo<Notice>> selectAllCourseNotice(String pageparam) {
        LOGGER.info("start select course notice for student{}");
        PageDTO pageDTO = JacksonUtil.readValue(pageparam, PageDTO.class);
        if (pageDTO.getPageSize() == null) {
            pageDTO.setPageSize(5);
        }
        LOGGER.info("pageDto{}" + pageDTO.getPageNo() + " " + pageDTO.getPageSize());
        CommResult<PageInfo<Notice>> commResult = new CommResult<>();
        PageHelper.startPage(pageDTO.getPageNo(), pageDTO.getPageSize());
        List<Notice> noticeList = noticeDao.selectAllCourseNotice(pageDTO.getCourseId());
        PageInfo<Notice> pageInfo = new PageInfo<>(noticeList);
        LOGGER.info("{}"+pageInfo);
        if (noticeList.size() > 0 && noticeList != null) {
            commResult.setData(pageInfo);
            commResult.setResultCode(0);
            commResult.setResultMsg("success");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i = 0; i < noticeList.size(); i++) {
                String stringTime = format.format(noticeList.get(i).getCreateTime());
                noticeList.get(i).setStringTime(stringTime);
            }

        } else {
            commResult.setResultCode(1);
            commResult.setResultMsg("暂无通知!");
            commResult.setData(null);
        }
        return commResult;
    }

    @Override
    public CommResult<PageInfo<Announcement>> selectAllAnnouncementForTeacher() {
        LOGGER.info("start select all announcement for teacher{}");
        CommResult<PageInfo<Announcement>> commResult = new CommResult<>();
        PageHelper.startPage(1,5);
        List<Announcement> announcementList =noticeDao.selectAllAnnouncementForTeacher();
        PageInfo<Announcement> page = new PageInfo<>(announcementList);
        LOGGER.info("select announcement for tea result"+page);
        if (page.getList()!=null && page.getList().size()>0){
            commResult.setData(page);
            commResult.setResultCode(0);
            commResult.setResultMsg("success");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i=0;i<announcementList.size();i++){
                String stringTime =format.format(announcementList.get(i).getCreateTime());
                announcementList.get(i).setStringTime(stringTime);
            }

        }else {
            commResult.setResultCode(1);
            commResult.setResultMsg("暂无公告!");
            commResult.setData(null);
        }
        return commResult;
    }

    @Override
    public CommResult<PageInfo<Announcement>> selectAllAnnouncementForTeacher(String pageParams) {
        LOGGER.info("start select announcement for teacher{}");
        PageDTO pageDTO = JacksonUtil.readValue(pageParams,PageDTO.class);
        if (pageDTO.getPageSize()==null){
            pageDTO.setPageSize(5);
        }
        LOGGER.info("pageDto{}"+pageDTO.getPageNo()+" "+pageDTO.getPageSize());
        CommResult<PageInfo<Announcement>> commResult = new CommResult<>();
        PageHelper.startPage(pageDTO.getPageNo(),pageDTO.getPageSize());
        List<Announcement> announcementList =noticeDao.selectAllAnnouncementForTeacher();
        PageInfo<Announcement> pageInfo = new PageInfo<>(announcementList);
        if (announcementList.size()>0&&announcementList!=null){
            commResult.setData(pageInfo);
            commResult.setResultCode(0);
            commResult.setResultMsg("success");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i=0;i<announcementList.size();i++){
                String stringTime =format.format(announcementList.get(i).getCreateTime());
                announcementList.get(i).setStringTime(stringTime);
            }
        }else {
            commResult.setResultCode(1);
            commResult.setResultMsg("暂无公告!");
            commResult.setData(null);
        }
        return commResult;
    }
}
