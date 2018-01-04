package com.my.course.login.service;

import com.my.course.model.CommResult;

/**
 * Created by lin.pingbin on 2018/1/4.
 */
public interface LoginService {
    CommResult login(String loginJson);
}
