package com.tong.consume.api;

import com.tong.consume.fallback.ProviderFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "provider",fallback = ProviderFallBack.class )
@RequestMapping("provider")
public interface ProviderApi {

    @GetMapping("/getOne")
    public String getOne();

}
