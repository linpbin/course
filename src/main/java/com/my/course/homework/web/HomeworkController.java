package com.my.course.homework.web;

import com.github.pagehelper.PageInfo;
import com.my.course.exception.BusinessRuntimeException;
import com.my.course.homework.service.HomeworkService;
import com.my.course.model.CommResult;
import com.my.course.model.StudentTask;
import com.my.course.model.TeacherTask;
import com.my.course.util.DownloadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created by lin.pingbin on 2018/1/9.
 */
@RestController
@CrossOrigin
public class HomeworkController {
    @Autowired
    private HomeworkService homeworkService;
    @PostMapping("/downloadCoursetask")
    public ResponseEntity<byte[]> downloadCoursetask(@RequestParam("download") String download){
        TeacherTask teacherTask = homeworkService.selectTeacherTaskById(download);
        System.out.println(teacherTask.getTeataskAddress());
        if (teacherTask!=null){
            try {
                return DownloadFile.downloadFile(teacherTask.getTeataskAddress(),teacherTask.getTaskName());
            } catch (Exception e) {
                throw new BusinessRuntimeException("下载课件失败!"+e);
            }
        }
        return null;
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
    @PostMapping("/updateStudentTask")
    public CommResult updateStudentTask(@RequestParam("coursetaskId") String coursetaskId,
                                        @RequestParam("studentId") String studentId,
                                        @RequestParam("file") MultipartFile file){
        CommResult commResult = new CommResult();
        commResult=homeworkService.commitHomework(coursetaskId,file,studentId);

        return  commResult;
    }
    @PostMapping("/selectCoursetaskinfo")
    public CommResult stuTaskInfo(@RequestBody String pageparam){
        CommResult<PageInfo<StudentTask>> commResult = new CommResult<>();
        commResult=homeworkService.selectStudentTask(pageparam);
        return commResult;
    }
}
