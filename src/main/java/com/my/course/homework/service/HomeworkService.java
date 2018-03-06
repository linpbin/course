package com.my.course.homework.service;

import com.github.pagehelper.PageInfo;
import com.my.course.model.CommResult;
import com.my.course.model.StudentTask;
import com.my.course.model.TeacherTask;
import org.springframework.web.multipart.MultipartFile; /**
 * Created by lin.pingbin on 2018/1/9.
 */
public interface HomeworkService {
    TeacherTask selectTeacherTaskById(String id);

    CommResult insertCourseTask(String courseId,String taskName, String taskDescribe, String taskDeadline, MultipartFile file);

    CommResult commitHomework(String coursetaskId, MultipartFile file,String studentId);

    CommResult<PageInfo<StudentTask>> selectStudentTask(String pageparam);
}
