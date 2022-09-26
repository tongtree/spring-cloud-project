package com.tong.consume.bean.base;

import com.tong.consume.enums.base.ResponseCodeEnum;
import lombok.Builder;
import lombok.Data;

/**
 * @author 牛桐
 */
@Builder
@Data
public class ResponseUtil<T> {
    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;

    /**
     * 返回成功 无实体
     *
     * @return
     */
    public static ResponseUtil success() {
        return ResponseUtil.builder()
                .code(ResponseCodeEnum.SUCCESS.getCode())
                .msg(ResponseCodeEnum.SUCCESS.getDesc())
                .build();
    }

    /**
     * 返回成功 有实体
     *
     * @param data
     * @return
     */
    public static ResponseUtil success(Object data) {
        return ResponseUtil.builder()
                .code(ResponseCodeEnum.SUCCESS.getCode())
                .msg(ResponseCodeEnum.SUCCESS.getDesc())
                .data(data)
                .build();
    }

    /**
     * 返回失败 无实体
     *
     * @return
     */
    public static ResponseUtil error() {
        return ResponseUtil.builder()
                .code(ResponseCodeEnum.ERROR.getCode())
                .msg(ResponseCodeEnum.ERROR.getDesc())
                .build();
    }

    /**
     * 返回失败 无实体
     *
     * @return
     */
    public static ResponseUtil error(String msg) {
        return ResponseUtil.builder()
                .code(ResponseCodeEnum.ERROR.getCode())
                .msg(msg)
                .build();
    }

}
