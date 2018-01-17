package com.my.course.coueseware.web;

import com.my.course.coueseware.service.CoursewareService;
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
public class CoursewareController {
    @Autowired
    private CoursewareService coursewareService;
    @PostMapping("/downloadCourseware")
    public CommResult downloadCourseware(@RequestBody String id){
        coursewareService.selectCoursewareById(id);
        return CommResult.SUCCESS;
    }
    @PostMapping("/deleteCourseware")
    public CommResult deleteCourseware(@RequestBody String id){
        CommResult commResult = new CommResult();
        commResult=coursewareService.deleteCoursewareById(id);
        return commResult;
    }
}
