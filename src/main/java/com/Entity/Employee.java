package com.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: lambda
 * @description
 * @author: xiangyuyi
 * @create: 2020-10-24 11:22
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private String name;

    private Integer age;

    private Status status;


    public enum Status{
        FREE,
        BUSY,
        VOCATION
    }
}
