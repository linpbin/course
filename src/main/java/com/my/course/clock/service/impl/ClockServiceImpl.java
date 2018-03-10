package com.my.course.clock.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.course.clock.dao.ClockDao;
import com.my.course.clock.service.ClockService;
import com.my.course.exception.BusinessRuntimeException;
import com.my.course.model.*;
import com.my.course.util.JacksonUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ClockServiceImpl implements ClockService {
    @Autowired
    private ClockDao clockDao;
    @Override
    public CommResult createSignRull(String params) {
        CommResult commResult = new CommResult();
        SignRule signRule = JacksonUtil.readValue(params,SignRule.class);
        if (signRule.getCourseId()!=null && signRule.getStartTime()!=null&&signRule.getEndTime()!=null){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date start = format.parse(signRule.getStartTime());
                Date end = format.parse(signRule.getEndTime());
                int va = start.compareTo(end);
                if (va>0){
                    commResult.setData(null);
                    commResult.setResultMsg("开始日期必须比结束日期小");
                    commResult.setResultCode(1);
                    return commResult;
                }
            } catch (ParseException e) {
                throw new BusinessRuntimeException("日期转换失败");
            }
            int result = clockDao.insertSignRule(signRule);
            if(result!=0){
                commResult.setData(signRule);
                commResult.setResultCode(0);
                commResult.setResultMsg("success");
                return commResult;
            }else {
                commResult.setData(null);
                commResult.setResultCode(1);
                commResult.setResultMsg("发布失败！");
                return commResult;
            }
        }else {
            commResult.setResultMsg("请检查参数是否正确！");
            commResult.setResultCode(1);
            commResult.setData(null);
            return commResult;
        }

    }

    @Override
    public CommResult<PageInfo<SignRule>> getSignAllRull(String pageparam) {
        CommResult<PageInfo<SignRule>> commResult = new CommResult<>();
        SignRuleDTO signRuleDTO = JacksonUtil.readValue(pageparam,SignRuleDTO.class);
        if (signRuleDTO.getPageSize()==null){
            signRuleDTO.setPageSize(5);
        }
        PageHelper.startPage(signRuleDTO.getPageNo(),signRuleDTO.getPageSize());
        List<SignRule> signRuleList = clockDao.getAllSignRule(signRuleDTO.getCourseId());
        PageInfo<SignRule> pageInfo = new PageInfo<>(signRuleList);
        if (signRuleList!=null && signRuleList.size()>0){
            commResult.setData(pageInfo);
            commResult.setResultCode(0);
            commResult.setResultMsg("success");
        }else {
            commResult.setResultMsg("暂未发布过签到！");
            commResult.setResultCode(1);
            commResult.setData(null);
        }
        return commResult;
    }

    @Override
    public CommResult deleteSignRullById(String id) {
        CommResult commResult = new CommResult();
        int result = clockDao.deleteSignRule(Integer.parseInt(id));
        if(result!=0){
            commResult.setData(null);
            commResult.setResultCode(0);
            commResult.setResultMsg("success");
            return commResult;
        }else {
            commResult.setData(null);
            commResult.setResultCode(1);
            commResult.setResultMsg("删除失败！");
            return commResult;
        }
    }

    @Override
    public CommResult updateSignRullById(String params) {
        SignRule signRule =JacksonUtil.readValue(params,SignRule.class);
        CommResult commResult = new CommResult();
        if (signRule.getCourseId()!=null && signRule.getStartTime()!=null&&signRule.getEndTime()!=null){
            int result = clockDao.updateSignRule(signRule);
            if(result!=0){
                commResult.setData(signRule);
                commResult.setResultCode(0);
                commResult.setResultMsg("success");
                return commResult;
            }else {
                commResult.setData(null);
                commResult.setResultCode(1);
                commResult.setResultMsg("更新失败！");
                return commResult;
            }
        }else {
            commResult.setResultMsg("更新失败！");
            commResult.setResultCode(1);
            commResult.setData(null);
            return commResult;
        }
    }
    //sign recoed
    @Override
    public  CommResult<PageInfo<SignRecord>> getAllSignRecordBySignRuleIdAndPage(String params) {
        SignRecordDTO signRecordDTO = JacksonUtil.readValue(params,SignRecordDTO.class);
        CommResult<PageInfo<SignRecord>> commResult = new CommResult<>();

        if (signRecordDTO.getPageSize()==null){
            signRecordDTO.setPageSize(5);
        }
        PageHelper.startPage(signRecordDTO.getPageNo(),signRecordDTO.getPageSize());
        List<SignRecord> signRecordList = clockDao.getAllSignRecord(signRecordDTO.getSignruleId());
        PageInfo<SignRecord> pageInfo = new PageInfo<>(signRecordList);
        if (signRecordList!=null && signRecordList.size()>0){
            commResult.setData(pageInfo);
            commResult.setResultCode(0);
            commResult.setResultMsg("success");
        }else {
            commResult.setResultMsg("暂未有人签到！");
            commResult.setResultCode(1);
            commResult.setData(null);
        }
        return commResult;
    }

    @Override
    public CommResult getAllSignRecordBySignRuleId(String signRuleId) {
        CommResult commResult = new CommResult();
        List<SignRecord> signRecordList = clockDao.getAllSignRecord(Integer.parseInt(signRuleId));
        if (signRecordList.size()>0&& signRecordList!=null){
            commResult.setData(signRecordList);
            commResult.setResultCode(0);
            commResult.setResultMsg("success");
        }else {
            commResult.setData(null);
            commResult.setResultMsg("查询失败!");
            commResult.setResultCode(1);
        }
        return commResult;
    }

    @Override
    public CommResult insertSignRecord(String params) {
        CommResult commResult = new CommResult();
        SignRecord signRecord = JacksonUtil.readValue(params,SignRecord.class);
        if (signRecord.getSignTime()!=null&&signRecord.getStudentId()!=null&&signRecord.getStudentName()!=null&&signRecord.getStudentClazz()!=null&&signRecord.getSignruleId()!=null){
            SignRule signRule = clockDao.getSignRuleById(signRecord.getSignruleId());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            int va = 0;
            try {
                va = signRecord.getSignTime().compareTo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(signRule.getEndTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (va>1){
                commResult.setData(null);
                commResult.setResultMsg("已超过截止时间");
                commResult.setResultCode(1);
                return commResult;
            }else {
                int result = clockDao.insertSignRecord(signRecord);
                if(result!=0){
                    commResult.setData(signRecord);
                    commResult.setResultCode(0);
                    commResult.setResultMsg("success");
                    return commResult;
                }else {
                    commResult.setData(null);
                    commResult.setResultCode(1);
                    commResult.setResultMsg("签到失败！");
                    return commResult;
                }
            }
        }else {
            commResult.setResultMsg("请检查参数是否正确！");
            commResult.setResultCode(1);
            commResult.setData(null);
            return commResult;
        }
    }

    @Override
    public CommResult deleteSignRecord(String id) {
        CommResult commResult = new CommResult();
        int result = clockDao.deleteSignRecord(Integer.parseInt(id));
        if(result!=0){
            commResult.setData(null);
            commResult.setResultCode(0);
            commResult.setResultMsg("success");
            return commResult;
        }else {
            commResult.setData(null);
            commResult.setResultCode(1);
            commResult.setResultMsg("删除失败！");
            return commResult;
        }
    }


}
