package com.yuheng.pangolin.model.user;

import lombok.Data;

@Data
public class User {
    // 用户唯一ID
    private String uid;
    // 登录用户名（唯一）
    private String username;
    // 密码(加盐、hash后的值)
    private String secPassword;
    // 用于HMAC计算
    private String salt;
    // 等级
    private int level;
    // 经验值
    private int experience;
}
