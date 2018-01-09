package com.my.course.student.web;

import com.my.course.model.CommResult;
import com.my.course.model.Student;
import com.my.course.model.Teacher;
import com.my.course.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lin.pingbin on 2018/1/5.
 */
@RestController
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/studentInfo")
    public CommResult studentInfo(@RequestBody String id){
        CommResult<Student> commResult = new CommResult<>();
        commResult=studentService.studentInfo(id);
        return commResult;
    }
    @PostMapping("/stuModityPw")
    public CommResult stuModityPw(@RequestBody String password){
        CommResult commResult = new CommResult();
        commResult=studentService.stuModityPw(password);
        return commResult;
    }
    @PostMapping("/showCourseList")
    public CommResult showCourseList(@RequestBody String username){
        CommResult commResult = new CommResult();
        commResult=studentService.selectCourseList(username);
        return commResult;
    }
}
