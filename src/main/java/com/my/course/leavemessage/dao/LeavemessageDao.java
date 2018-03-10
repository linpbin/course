package com.my.course.leavemessage.dao;

import com.my.course.model.LeaveMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LeavemessageDao {
    @Select("select * from leavemessage where course_id =#{courseId} order by create_time desc")
    List<LeaveMessage> leaveMessageList (@Param("courseId") Integer courseId);
    @Insert("insert into leavemessage(context,create_time,course_id) values(#{leaveMessage.context},#{leaveMessage.createTime},#{leaveMessage.courseId})")
    int createLeaveMessage(@Param("leaveMessage") LeaveMessage leaveMessage);
}
