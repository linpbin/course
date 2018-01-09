package com.my.course.homework.web;

import com.my.course.homework.service.HomeworkService;
import com.my.course.model.CommResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
