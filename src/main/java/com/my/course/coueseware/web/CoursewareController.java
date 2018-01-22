package com.my.course.coueseware.web;

import com.my.course.coueseware.service.CoursewareService;
import com.my.course.exception.BusinessRuntimeException;
import com.my.course.model.CommResult;
import com.my.course.model.Courseware;
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
public class CoursewareController {
    @Autowired
    private CoursewareService coursewareService;
    @PostMapping("/downloadCourseware")
    public ResponseEntity<byte[]> downloadCourseware(@RequestParam("download") String download){


        Courseware courseware = coursewareService.selectCoursewareById(download);

        if (courseware!=null){
            try {
               return DownloadFile.downloadFile(courseware.getFileAddress(),courseware.getCoursewareName());
            } catch (Exception e) {
                throw new BusinessRuntimeException("下载课件失败!"+e);
            }
        }
       return null;
    }
    @PostMapping("/deleteCourseware")
    public CommResult deleteCourseware(@RequestBody String id){
        CommResult commResult = new CommResult();
        commResult=coursewareService.deleteCoursewareById(id);
        return commResult;
    }
    @PostMapping("/reupdateCourseware")
    public CommResult reupdateCourseware(@RequestParam("coursewareId") String coursewareId,@RequestParam("file") MultipartFile file){
        CommResult commResult = new CommResult();
        commResult=coursewareService.reUpdateCourseware(coursewareId,file);
        return  commResult;

    }
}
