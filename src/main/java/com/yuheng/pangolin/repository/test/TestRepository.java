package com.yuheng.pangolin.repository.test;

import com.yuheng.pangolin.model.TestModel;

public interface TestRepository {
    TestModel getTestDataById(int id);
}
