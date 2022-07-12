package com.maple.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package_Name: com.maple.springcloud.entities
 * @Auth: Maple
 * @Date: 2022/7/12 9:45
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;

    private String msg;

    private T Data;

    public CommonResult(Integer code, String msg) {
        this(code, msg, null);
    }

}