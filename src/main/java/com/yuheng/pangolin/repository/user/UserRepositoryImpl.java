package com.yuheng.pangolin.repository.user;

import com.yuheng.pangolin.mapper.UserMapper;
import com.yuheng.pangolin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;

    @Autowired
    public UserRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getUserByUID(String uid) {
        return userMapper.getUserByUID(uid);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public void deleteUserByUID(String uid) {

    }

    @Override
    public User updateUser(User user) {
        return user;
    }

}
