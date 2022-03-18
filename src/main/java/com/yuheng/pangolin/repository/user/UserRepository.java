package com.yuheng.pangolin.repository.user;

import com.yuheng.pangolin.model.user.User;

import java.util.List;

public interface UserRepository {
    User getUserByUID(String uid);
    User getUserByUsername(String username);
    int addUser(User user);
    void deleteUserByUID(String uid);
    User updateUser(User user);
}
