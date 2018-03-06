package com.my.course.ds;

import com.my.course.model.SignRule;
import org.apache.ibatis.jdbc.SQL;

public class ClockProvider {
    public String updateSignRule(SignRule signRule){
        return new SQL(){{
            UPDATE("sign_rule");
            if (signRule.getStartTime()!=null){
                SET("start_time=#{startTime}");
            }
            if (signRule.getEndTime()!=null){
                SET("end_time=#{endTime}");
            }
            if (signRule.getCourseId()!=null){
                SET("course_id=#{courseId}");
            }
            WHERE("id=#{id}");
        }}.toString();
    }
}
