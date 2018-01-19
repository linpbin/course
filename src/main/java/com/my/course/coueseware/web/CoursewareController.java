package com.my.course.coueseware.web;

import com.my.course.coueseware.service.CoursewareService;
import com.my.course.model.CommResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping("/reupdateCourseware")
    public CommResult reupdateCourseware(@RequestParam("coursewareId") String coursewareId,@RequestParam("file") MultipartFile file){
        System.out.println(file.getOriginalFilename());
        CommResult commResult = new CommResult();
        commResult=coursewareService.reUpdateCourseware(coursewareId,file);
        return  commResult;

    }
}
