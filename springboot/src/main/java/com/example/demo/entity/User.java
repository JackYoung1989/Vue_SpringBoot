package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author JackYoung
 * @Description:
 * @create 2022-03-22
 */
@TableName("user")  //mybatis plus要写这个注解
@Data  //lombok 自动生成get set方法
public class User {
    //value可以不写，在id名字一致的情况下。
    @TableId(value = "id", type = IdType.AUTO) // type：自动生成
    private Integer id;
    private String username;
    private String nickName;
    private String password;
    private Integer age;
    private String sex;
    private String address;
}














