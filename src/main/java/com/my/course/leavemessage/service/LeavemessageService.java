package com.my.course.leavemessage.service;

import com.github.pagehelper.PageInfo;
import com.my.course.model.CommResult;
import com.my.course.model.LeaveMessage;

public interface LeavemessageService {
    CommResult<PageInfo<LeaveMessage>> getLeavemessage(String params);
    CommResult createLeavemeessage(String params);
}
