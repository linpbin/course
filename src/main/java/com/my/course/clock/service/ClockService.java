package com.my.course.clock.service;

import com.github.pagehelper.PageInfo;
import com.my.course.model.CommResult;
import com.my.course.model.SignRecord;
import com.my.course.model.SignRule;

public interface ClockService {
    //签到规则

    //create params:courseId startTime endTime
    CommResult createSignRull(String params);
    //get all
    CommResult<PageInfo<SignRule>> getSignAllRull(String pageparam);
    //delete
    CommResult deleteSignRullById(String id);
    //update
    CommResult updateSignRullById(String params);

    //签到记录

    //get by id pageinfo  signruleid pagesize pageno
    CommResult<PageInfo<SignRecord>> getAllSignRecordBySignRuleIdAndPage(String params);
    //get all
    CommResult getAllSignRecordBySignRuleId(String signRuleId);
    //insert signrecord
    CommResult insertSignRecord(String params);
    //delete by id
    CommResult deleteSignRecord(String id);

}
