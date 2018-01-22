package com.my.course.homework.web;

import com.my.course.homework.service.HomeworkService;
import com.my.course.model.CommResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by lin.pingbin on 2018/1/9.
 */
@RestController
@CrossOrigin
public class HomeworkController {
    @Autowired
    private HomeworkService homeworkService;
    @PostMapping("/downloadCoursetask")
    public CommResult downloadCoursetask(@RequestBody String id){
        homeworkService.selectTeacherTaskById(id);
        return CommResult.SUCCESS;
    }
    @PostMapping("/releaseHomework")
    public CommResult releaseHomework(@RequestParam("courseId") String courseId,
                                      @RequestParam("taskName") String taskName,
                                      @RequestParam("taskDescribe") String taskDescribe,
                                      @RequestParam("taskDeadline") String taskDeadline,
                                      @RequestParam("file") MultipartFile file){
        CommResult commResult = new CommResult();
        commResult = homeworkService.insertCourseTask(courseId,taskName,taskDescribe,taskDeadline,file);
        return  commResult;
    }
}
