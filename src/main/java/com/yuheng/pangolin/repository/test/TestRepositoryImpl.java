package com.yuheng.pangolin.repository.test;

import com.yuheng.pangolin.mapper.TestMapper;
import com.yuheng.pangolin.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestRepositoryImpl implements TestRepository {

    private final TestMapper testMapper;

    @Autowired
    public TestRepositoryImpl(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    @Override
    public TestModel getTestDataById(int id) {
        return testMapper.getTestDataById(id);
    }
}
