package com.tong.consume.api;

import com.tong.consume.api.fallback.ProviderFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "provider",fallback = ProviderFallBack.class )
public interface ProviderApi {

    @GetMapping("/provider/getOne")
    public String getOne();

}
