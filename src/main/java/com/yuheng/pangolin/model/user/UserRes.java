package com.yuheng.pangolin.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRes {
    // 用户唯一ID
    private String uid;
    // 登录用户名（唯一）
    private String username;
    // 等级
    private int level;
    // 经验值
    private int experience;

    public UserRes(User user) {
        this.uid = user.getUid();
        this.username = user.getUsername();
        this.level = user.getLevel();
        this.experience = user.getExperience();
    }
}
