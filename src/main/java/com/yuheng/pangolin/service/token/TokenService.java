package com.yuheng.pangolin.service.token;

import com.yuheng.pangolin.model.Token;
import io.jsonwebtoken.Claims;

public interface TokenService {
    // 创建token
    Token createTokenByUserID(String uid);
    // 解析token
    Claims parseToken(String token);
    // 验证有效期
    boolean isTokenExpired(String token);
    // 获取UserID
    String getUserId(String token);
}
