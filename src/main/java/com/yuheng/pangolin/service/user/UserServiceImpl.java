package com.yuheng.pangolin.service.user;

import com.yuheng.pangolin.encryption.Encryptor;
import com.yuheng.pangolin.exception.user.UserException;
import com.yuheng.pangolin.model.Token;
import com.yuheng.pangolin.model.User;
import com.yuheng.pangolin.repository.user.UserRepository;
import com.yuheng.pangolin.service.token.TokenService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            TokenService tokenService
    ) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    public Token signUp(@NonNull String username, @NonNull String password) throws UserException {
        if (username.isEmpty() || password.isEmpty()) {
            throw new UserException("账号或密码不能为空");
        }

        if (this.hasUserWithUsername(username)) {
            throw new UserException("用户名已经存在啦～");
        }

        String salt = Encryptor.generateHmacMD5Salt();
        String encryptedPassword = Encryptor.hmacMD5(password, salt);

        User user = new User();
        String uid = generateUserID();
        user.setUid(uid);
        user.setUsername(username);
        user.setSalt(salt);
        user.setSecPassword(encryptedPassword);
        user.setLevel(0);
        user.setExperience(0);

        if (userRepository.addUser(user) < 1) {
            System.out.println("1");
            throw new UserException("似乎因为一些奇怪的事情注册失败了...");
        }

        System.out.println("2");

        return tokenService.createTokenByUserID(uid);
    }

    @Override
    public Token signIn(String username, String password) throws UserException {
        if (username.isEmpty() || password.isEmpty()) {
            throw new UserException("账号或密码不能为空");
        }

        User user = userRepository.getUserByUsername(username);
        String stored = user.getSecPassword();
        String salt = user.getSalt();
        String input = Encryptor.hmacMD5(password, salt);
        if (input == null || !input.equals(stored)) {
            throw new UserException("账号或密码错误");
        }

        return tokenService.createTokenByUserID(user.getUid());
    }

    @Override
    public void signOut(String token) {

    }

    @Override
    public Token editUserInfo(User user) {
        return null;
    }

    @Override
    public boolean hasUserWithUsername(String username) {
        return userRepository.getUserByUsername(username) != null;
    }

    private String generateUserID() {
        return Encryptor.generateUUID();
    }
}
