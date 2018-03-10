package com.my.course.leavemessage.web;

import com.github.pagehelper.PageInfo;
import com.my.course.leavemessage.service.LeavemessageService;
import com.my.course.model.CommResult;
import com.my.course.model.LeaveMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LeaveMessageController {
    @Autowired
    private LeavemessageService leavemessageService ;
    @PostMapping("/getleavemessage")
    public CommResult<PageInfo<LeaveMessage>> select(@RequestBody String params){
        CommResult<PageInfo<LeaveMessage>> commResult = new CommResult();
        commResult = leavemessageService.getLeavemessage(params);
        return commResult;
    }
    @PostMapping("/leavemessage")
    public CommResult create(@RequestBody String params){
        CommResult commResult = new CommResult();
        commResult=leavemessageService.createLeavemeessage(params);
        return commResult;
    }
}
