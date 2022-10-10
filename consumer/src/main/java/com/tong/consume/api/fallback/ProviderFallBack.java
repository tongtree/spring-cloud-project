package com.tong.consume.api.fallback;

import com.tong.consume.api.ProviderApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProviderFallBack implements ProviderApi {
    private static final Logger log= LoggerFactory.getLogger(ProviderFallBack.class);

    @Override
    public String getOne() {
        log.error("错误信息");
        return "";
    }
}
