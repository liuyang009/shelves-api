package io.shelves.controller;

import io.shelves.entity.UserEntity;
import io.shelves.interceptor.AuthorizationInterceptor;
import io.shelves.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * ${DESCRIPTION}
 *
 * @author five.liu
 * @create 2018/3/9/009
 */
public class BaseController {

    @Autowired
    private UserService userService;

    @Resource
    protected HttpServletRequest request;

    /**
     * 获取当前用户
     * @return
     */
    public UserEntity getUser(){

        Long userId = (Long) request
                .getAttribute(AuthorizationInterceptor.USER_KEY);

        return userService.selectById(userId);
    }
}
