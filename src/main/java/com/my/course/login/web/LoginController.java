package com.my.course.login.web;

import com.my.course.login.service.LoginService;
import com.my.course.model.CommResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lin.pingbin on 2018/1/4.
 */
@RestController
@CrossOrigin
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    public CommResult login(@RequestBody String loginJson){
        LOGGER.info("login info{} "+loginJson);
        CommResult commResult = new CommResult();
        commResult=loginService.login(loginJson);
        return  commResult;
    }
}
