package com.my.course.leavemessage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.course.leavemessage.dao.LeavemessageDao;
import com.my.course.leavemessage.service.LeavemessageService;
import com.my.course.model.CommResult;
import com.my.course.model.LeaveMessage;
import com.my.course.model.LeaveMessageDTO;
import com.my.course.model.LeaveMessageVO;
import com.my.course.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LeavemessageServiceImpl implements LeavemessageService {
    @Autowired
    private LeavemessageDao leavemessageDao;
    @Override
    public CommResult getLeavemessage(String params) {
        LeaveMessageDTO leaveMessageDTO = JacksonUtil.readValue(params,LeaveMessageDTO.class);
        CommResult<PageInfo<LeaveMessage>> commResult = new CommResult<>();
        if (leaveMessageDTO.getPageSize()==null){
            leaveMessageDTO.setPageSize(15);
        }
        PageHelper.startPage(leaveMessageDTO.getPageNo(),leaveMessageDTO.getPageSize());
        List<LeaveMessage> signRecordList = leavemessageDao.leaveMessageList(leaveMessageDTO.getCourseId());
        PageInfo<LeaveMessage> pageInfo = new PageInfo<>(signRecordList);
        if (signRecordList!=null && signRecordList.size()>0){
            commResult.setData(pageInfo);
            commResult.setResultCode(0);
            commResult.setResultMsg("success");
            return commResult;
        }else {
            commResult.setResultMsg("暂未有人发言！");
            commResult.setResultCode(1);
            commResult.setData(null);
            return commResult;
        }

    }

    @Override
    public CommResult createLeavemeessage(String params) {
        CommResult commResult = new CommResult();
        LeaveMessageVO leaveMessageVO = JacksonUtil.readValue(params,LeaveMessageVO.class);
        System.out.println(leaveMessageVO.getContext());
        String content = leaveMessageVO.getName()+":"+leaveMessageVO.getContext();
        LeaveMessage leaveMessage = new LeaveMessage();
        leaveMessage.setContext(content);
        System.out.println(content);
        leaveMessage.setCreateTime(new Date());
        leaveMessage.setCourseId(leaveMessageVO.getCourseId());
        System.out.println(leaveMessageVO.getCourseId());
        int result = leavemessageDao.createLeaveMessage(leaveMessage);
        if (result!=0){
            commResult.setData(null);
            commResult.setResultMsg("success");
            commResult.setResultCode(0);
            return commResult;
        }else {
            commResult.setData(null);
            commResult.setResultMsg("failure");
            commResult.setResultCode(1);
            return commResult;
        }

    }
}
