package com.my.course.clock.web;

import com.github.pagehelper.PageInfo;
import com.my.course.clock.service.ClockService;
import com.my.course.model.CommResult;
import com.my.course.model.SignRecord;
import com.my.course.model.SignRule;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.List;

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
    @PostMapping("/signrules")
    public CommResult<PageInfo<SignRule>> getAllSignRule(@RequestBody String params){
        System.out.println(params);
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
    @GetMapping("/exportExcel/{id}")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") String id)
            throws Exception {
        System.out.println(id);
        CommResult commResult = clockService.getAllSignRecordBySignRuleId(id);
        List<SignRecord> signRecordList = (List<SignRecord>) commResult.getData();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("签到记录表");
        HSSFRow row = sheet.createRow((int) 0);
        String[] excelHeader = {"学号","姓名","班级","签到时间"};
        for (int i = 0; i < excelHeader.length; i++) {
            sheet.setColumnWidth(i,4000);
            row.createCell(i).setCellValue(excelHeader[i]);
        }

        for (int i = 0; i < signRecordList.size(); i++) {
            row = sheet.createRow(i + 1);
            SignRecord signRecord = signRecordList.get(i);
            row.createCell(0).setCellValue(signRecord.getStudentId());
            row.createCell(1).setCellValue(signRecord.getStudentName());
            row.createCell(2).setCellValue(signRecord.getStudentClazz());
            row.createCell(3).setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(signRecord.getSignTime()));

        }
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", "attachment;filename=qiandao.xls");
        ServletOutputStream ouputStream = response.getOutputStream();
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }

}
