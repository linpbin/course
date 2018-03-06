package com.my.course.homework.dao;

import com.my.course.model.StudentTask;
import com.my.course.model.TeacherTask;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by lin.pingbin on 2018/1/9.
 */
@Mapper
public interface HomeworkDao {
    @Select("select * from teacher_task where id=#{teachertaskId}")
    TeacherTask selectTeacherTaskById(@Param("teachertaskId") Integer teachertaskId);
    @Insert("insert into teacher_task(task_name,task_describe,task_deadline,teatask_address,course_id) values(#{teacherTask.taskName},#{teacherTask.taskDescribe},#{teacherTask.taskDeadline},#{teacherTask.teataskAddress},#{teacherTask.courseId}) ")
    int insertHomework(@Param("teacherTask") TeacherTask teacherTask);
    @Insert("insert into student_task(task_id,student_id,upload_time,stutask_address,course_id,task_name) values(#{studentTask.taskId},#{studentTask.studentId},#{studentTask.uploadTime},#{studentTask.stutaskAddress},#{studentTask.courseId},#{studentTask.taskName}) ")
    int insertStudentTask(@Param("studentTask") StudentTask studentTask);
    @Select("select * from student_task where student_id=#{studentId} and course_id=#{courseId}")
    List<StudentTask> selectStudentTaskById(@Param("studentId") Integer studentId,@Param("courseId") Integer courseId);
    @Select("select * from teacher_task where course_id=#{courseId}")
    List<TeacherTask> selectTeacherTaskListByCourseId(@Param("courseId") Integer courseId);
}
