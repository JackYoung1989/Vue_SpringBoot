package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author JackYoung
 * @Description:
 * @create 2022-03-22
 */
@TableName("book")  //mybatis plus要写这个注解
@Data  //lombok 自动生成get set方法
public class Book {
    //value可以不写，在id名字一致的情况下。
    @TableId(value = "id", type = IdType.AUTO) // type：自动生成
    private Integer id;
    private String name;
    private BigDecimal price; // 数据库里面是decimal类型
    private String author;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime; // 数据库是datetime类型
}














