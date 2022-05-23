package com.yuheng.pangolin.mapper;

import com.yuheng.pangolin.model.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User getUserByUID(String uid);
    User getUserByUsername(String username);
    int addUser(User user);
}
