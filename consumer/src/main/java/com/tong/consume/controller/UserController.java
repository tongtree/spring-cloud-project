package com.tong.consume.controller;

import com.tong.consume.bean.base.ResponseUtil;
import com.tong.consume.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author niutong
 * @since 2022-09-26
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 登录
     *
     * @return
     */
    public ResponseUtil<User> login() {
        User user = new User();
        return ResponseUtil.success(user);
    }
}
