package com.yuheng.pangolin.mapper;

import com.yuheng.pangolin.model.TestModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
    TestModel getTestDataById(int id);
}
