package com.my.course.homework.service;

import com.my.course.model.CommResult;
import org.springframework.web.multipart.MultipartFile; /**
 * Created by lin.pingbin on 2018/1/9.
 */
public interface HomeworkService {
    void selectTeacherTaskById(String id);

    CommResult insertCourseTask(String courseId,String taskName, String taskDescribe, String taskDeadline, MultipartFile file);
}
