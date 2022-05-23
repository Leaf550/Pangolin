package com.yuheng.pangolin.service.user;

import com.yuheng.pangolin.constant.StatusCode;
import com.yuheng.pangolin.encryption.Encryptor;
import com.yuheng.pangolin.model.user.Token;
import com.yuheng.pangolin.model.user.User;
import com.yuheng.pangolin.model.user.UserRes;
import com.yuheng.pangolin.repository.user.UserRepository;
import com.yuheng.pangolin.service.token.TokenService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public Token signUp(@NonNull String username,
                        @NonNull String password,
                        UserCallBack userCallBack) {
        if (this.hasUserWithUsername(username)) {
            userCallBack.callback(StatusCode.USER_EXISTED);
            return null;
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
            userCallBack.callback(StatusCode.UNKNOWN_ERR);
            return null;
        }

        userCallBack.callback(StatusCode.OK);
        return tokenService.createTokenByUser(user);
    }

    @Override
    public Token signIn(String username, String password, UserCallBack userCallback) {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            userCallback.callback(StatusCode.PWD_ERR);
            return null;
        }
        String stored = user.getSecPassword();
        String salt = user.getSalt();
        String input = Encryptor.hmacMD5(password, salt);
        if (input == null || !input.equals(stored)) {
            userCallback.callback(StatusCode.PWD_ERR);
            return null;
        }

        userCallback.callback(StatusCode.OK);
        return tokenService.createTokenByUser(user);
    }

    @Override
    public void signOut(String token) {

    }

    @Override
    public UserRes getUserById(String uid) {
        User user = userRepository.getUserByUID(uid);
        if (user == null) {
            return null;
        }
        return new UserRes(user);
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
