package com.tong.consume.enums.base;

/**
 * @author niutong
 */

public enum ResponseCodeEnum {
    /**
     * 请求返回
     */
    SUCCESS(200,"请求成功"),
    PARAMETER_ERROR(400,"参数错误"),
    ERROR(500,"请求失败"),
    ;

    private Integer code;
    private String desc;
    ResponseCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
