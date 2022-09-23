package com.tong.consume.controller;

import com.tong.consume.api.ProviderApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumeTest")
public class TestController {

    @Autowired
    private ProviderApi providerApi;

    @RequestMapping("/getOneTest")
    public String getOneText() {
      return  providerApi.getOne();
    }
}
