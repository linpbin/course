package com.my.course.course.web;

import com.github.pagehelper.PageInfo;
import com.my.course.course.service.CourseService;
import com.my.course.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lin.pingbin on 2018/1/8.
 */
@RestController
@CrossOrigin
public class CourseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);
    @Autowired
    private CourseService courseService;

    @PostMapping("/courseInfo")
    public CommResult courseInfo(@RequestBody String courseId){
        CommResult<Course> commResult = new CommResult<>();
        commResult=courseService.selectCourseInfo(courseId);
        return commResult;
    }
    @PostMapping("/classTeacher")
    public CommResult classTeacher(@RequestBody String courseId){
        CommResult<Teacher> commResult = new CommResult<>();
        commResult=courseService.selectCourseTeacher(courseId);
        return commResult;
    }
    @PostMapping("/selectCourseware")
    public CommResult selectCourseware(@RequestBody String pageparam){
        CommResult<PageInfo<Courseware>> commResult = new CommResult<>();
        commResult=courseService.selectCourseware(pageparam);
        return commResult;
    }
    @PostMapping("/selectCoursetask")
    public CommResult selectCoursetask(@RequestBody String courseId){
        CommResult<List<TeacherTask>> commResult = new CommResult<>();
        commResult=courseService.selectCoursetask(courseId);
        return commResult;
    }
    @PostMapping("/courseStudents")
    public CommResult courseStudents(@RequestBody String pageparam){
        CommResult<PageInfo<StudentDTO>> commResult = new CommResult<>();
        commResult=courseService.selectStudentListByCourseId(pageparam);
        return  commResult;
    }




}
