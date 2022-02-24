package com.yuheng.pangolin.service.test;

import com.yuheng.pangolin.model.TestModel;
import com.yuheng.pangolin.repository.test.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    @Autowired
    public TestServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public TestModel getTestDataById(int id) {
        return testRepository.getTestDataById(id);
    }
}
