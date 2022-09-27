package com.tong.consume.controller;

import com.tong.consume.bean.base.ResponseUtil;
import com.tong.consume.entity.User;
import com.tong.consume.service.UserService;
import com.tong.consume.utils.RocketProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RocketProducer rocketProducer;

    /**
     * 登录
     *
     * @return
     */
    @GetMapping("/login")
    public ResponseUtil<User> login() {
        List<User> list = userService.lambdaQuery().eq(User::getUserName, "").list();
        log.info("info---");
        log.error("error---");
        log.debug("debug---");
        rocketProducer.sendDelayMsg("这是一条无用消息",0);
        User user = new User();
        return ResponseUtil.success(user);
    }
}
