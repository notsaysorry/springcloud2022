package com.liudong.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResult<T> {
    private int code;
    private String message;
    private T data;


    public CommonResult(int code, String message){
        this.code = code;
        this.message = message;
        this.data = null;
    }
}
