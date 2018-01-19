package com.my.course.coueseware.service;

import com.my.course.model.CommResult;
import com.my.course.model.TeacherTask;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by lin.pingbin on 2018/1/9.
 */
public interface CoursewareService {
    void selectCoursewareById(String id);

    CommResult deleteCoursewareById(String id);

    CommResult reUpdateCourseware(String coursewareId, MultipartFile file);
}
