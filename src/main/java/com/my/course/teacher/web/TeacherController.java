package com.my.course.teacher.web;

import com.my.course.model.CommResult;
import com.my.course.model.Student;
import com.my.course.model.Teacher;
import com.my.course.student.service.StudentService;
import com.my.course.teacher.service.TeacherService;
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
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @PostMapping("/teacherInfo")
    public CommResult studentInfo(@RequestBody String id){
        CommResult<Teacher> commResult = new CommResult<>();
        commResult=teacherService.teacherInfo(id);
        return commResult;
    }
    @PostMapping("/teaModityPw")
    public CommResult stuModityPw(@RequestBody String password){
        CommResult commResult = new CommResult();
        commResult=teacherService.teaModityPw(password);
        return commResult;
    }
    @PostMapping("/showTeaCourseList")
    public CommResult showCourseList(@RequestBody String username){
        CommResult commResult = new CommResult();
        commResult=teacherService.selectCourseList(username);
        return commResult;
    }
}
