package com.my.course.clock.dao;

import com.my.course.ds.ClockProvider;
import com.my.course.model.SignRecord;
import com.my.course.model.SignRule;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClockDao {
    @Select("select * from sign_rule where course_id=#{courseId}")
    List<SignRule> getAllSignRule(@Param("courseId")Integer courseId);
    @Select("select * from sign_rule where id=#{id}")
    SignRule getSignRuleById(@Param("id") Integer id);
    @Insert("insert into sign_rule(start_time,end_time,course_id) values(#{signRule.startTime},#{signRule.endTime},#{signRule.courseId})")
    @Options(useGeneratedKeys = true,keyProperty = "signRule.id")
    int insertSignRule(@Param("signRule") SignRule signRule);
    @Delete("delete from sign_rule where id=#{id}")
    int deleteSignRule(@Param("id") Integer id);
    @UpdateProvider(type = ClockProvider.class,method = "updateSignRule")
    int updateSignRule(SignRule signRule);

    @Select("select * from sign_record where signrule_id=#{signruleId}")
    List<SignRecord> getAllSignRecord(@Param("signruleId") Integer signruleId);
    @Insert("insert into sign_record(signrule_id,sign_time,student_id,student_name,student_class) values(#{signRecord.signruleId},#{signRecord.signTime},#{signRecord.studentId},#{signRecord.studentName},#{signRecord.studentClass})")
    @Options(useGeneratedKeys = true,keyProperty = "signRecord.id")
    int insertSignRecord(@Param("signRecord") SignRecord signRecord);
    @Delete("delete from sign_record where id=#{id}")
    int deleteSignRecord(@Param("id") Integer id);

}
