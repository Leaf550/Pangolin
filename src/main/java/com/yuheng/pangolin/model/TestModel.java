package com.yuheng.pangolin.model;


/*
* `id` int auto_increment,
* `desc` varchar(20),
* `some_number` int,
* */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TestModel {
    private int id;
    private String desc;
    private int someNumber;
}
