package com.my.course.login.service.impl;

import com.my.course.login.dao.LoginDao;
import com.my.course.login.service.LoginService;
import com.my.course.model.CommResult;
import com.my.course.model.Login;
import com.my.course.model.Student;
import com.my.course.model.Teacher;
import com.my.course.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lin.pingbin on 2018/1/4.
 */
@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    private LoginDao loginDao;
    @Override
    public CommResult login(String loginJson) {
        Login login = JacksonUtil.readValue(loginJson,Login.class);
        LOGGER.info("json 转化结果{}"+login.getUsername());
        CommResult commResult = new CommResult();
        if (login.getLogintype().equals("Student")){
            LOGGER.info("student login {}"+login.getLogintype());
            Student student =loginDao.studentLogin(login.getUsername(),login.getPassword());
            if (student!=null){
                commResult.setData(student);

                commResult.setResultCode(0);
            }else {
                commResult.setResultCode(1);
                commResult.setResultMsg("用户名或密码错误");
            }
        }else if(login.getLogintype().equals("Teacher")){
            LOGGER.info("teacher login {}"+login.getLogintype());
            Teacher teacher =loginDao.teacherLogin(login.getUsername(),login.getPassword());
            if (teacher!=null){
                commResult.setData(teacher);
                commResult.setResultCode(0);
            }else {
                commResult.setResultCode(1);
                commResult.setResultMsg("用户名或密码错误");
            }
        }else {
            commResult.setResultCode(1);
            commResult.setResultMsg("未知错误");
        }
        return commResult;
    }
}
