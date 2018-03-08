package com.my.course.clock.web;

import com.github.pagehelper.PageInfo;
import com.my.course.clock.service.ClockService;
import com.my.course.model.CommResult;
import com.my.course.model.SignRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ClockController {
    @Autowired
    private ClockService clockService;
    @PostMapping("/signrule")
    public CommResult createSignRule(@RequestBody String params){
        CommResult commResult = new CommResult();
        commResult = clockService.createSignRull(params);
        return commResult;
    }
    @GetMapping("/signrule")
    public CommResult<PageInfo<SignRule>> getAllSignRule(@RequestBody String params){
        CommResult<PageInfo<SignRule>> commResult = new CommResult();
        commResult = clockService.getSignAllRull(params);
        return  commResult;
    }
    @DeleteMapping("/signrule/{id}")
    public CommResult deleteSignRule(@PathVariable("id") String id){
        CommResult commResult = new CommResult();
        commResult = clockService.deleteSignRullById(id);
        return  commResult;
    }
    @PutMapping("/signrule")
    public CommResult updateSignRule(@RequestBody String params){
        CommResult commResult = new CommResult();
        commResult = clockService.updateSignRullById(params);
        return commResult;
    }
    @GetMapping("/selectAllSignRecord")
    public CommResult selectSignRecord(@RequestBody String signRuleId){
        CommResult commResult = new CommResult();
        commResult=clockService.getAllSignRecordBySignRuleId(signRuleId);
        return commResult;
    }
    @PostMapping("/selectAllSignRecord")
    public CommResult selectSignRecordByPage(@RequestBody String signRuleId){
        CommResult commResult = new CommResult();
        commResult=clockService.getAllSignRecordBySignRuleIdAndPage(signRuleId);
        return commResult;
    }
    @PostMapping("/signrecord")
    public CommResult createSignrecord(@RequestBody String params){
        CommResult commResult = new CommResult();
        commResult = clockService.insertSignRecord(params);
        return  commResult;
    }
    @DeleteMapping("/signrecord/{id}")
    public CommResult deleteSignrecoed(@PathVariable("id") String id){
        CommResult commResult = new CommResult();
        commResult = clockService.deleteSignRecord(id);
        return  commResult;
    }

}
