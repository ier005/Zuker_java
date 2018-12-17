package com.workshop.zukerjava.service;

import com.workshop.zukerjava.bean.User;
import com.workshop.zukerjava.pojo.ProfileRequest;
import com.workshop.zukerjava.pojo.ProfileResponse;
import com.workshop.zukerjava.repository.UserRepository;
import com.workshop.zukerjava.sec.JwtTokenProvider;//TODO:谢谦写
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.workshop.zukerjava.pojo.LoginRequest;
import com.workshop.zukerjava.pojo.LoginResponse;
import com.workshop.zukerjava.exception.ValidationException;
import com.workshop.zukerjava.exception.SignUpErrorType;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //TODO:jwtTokenProvider
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public User registerUser(User user) {

        if (user.getUsername().length() < 3)
        {
            String id = "2";

            String msgParams = "ali12 | 6";

            // make your custom message and pass to enum
            throw new ValidationException(SignUpErrorType.INVALID_USERNAME_LENGTH, msgParams);
        }
        User user1 = userRepository.findByUsername(user.getUsername());
        System.out.println(user1.getEmail());

        User encryptedUser = new User();
        encryptedUser.setUsername(user.getUsername());
        encryptedUser.setEmail(user.getEmail());
        encryptedUser.setPassword(passwordEncoder.encode(encryptedUser.getPassword()));
        encryptedUser.setAvatarPath(user.getAvatarPath());
        userRepository.save(encryptedUser);

        return encryptedUser;
    }

    public LoginResponse loginUser(LoginRequest loginRequest) {

        LoginResponse loginResponse = new LoginResponse();
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        User user = userRepository.findByUsername(username);

        if (user != null) {
            String encodedPassword = user.getPassword();
            boolean veriftyPassword = passwordEncoder.matches(password, encodedPassword);

            if (veriftyPassword) {
                //TODO:jwtTokenProvider
                String token = jwtTokenProvider.generateToken(user);
                System.out.println("token = " + token);
                loginResponse.setMessage("True");
                loginResponse.setToken(token);
            }
        }

        return loginResponse;
    }

    public Long verifyToken(String token) {

        Long id = null;
        boolean validToken = jwtTokenProvider.validateToken(token);
        if (validToken == true) {
            id = jwtTokenProvider.getUserIdFromJWT(token);
        }

        return id;
    }

    /*
    * 修改密码 post
    * url: /zuker/profile/update/password
    */
    public ProfileResponse updatePassword(ProfileRequest profileRequest) {
        //LoginResponse loginResponse = new LoginResponse();
        String username = profileRequest.getUsername();
        String newPassword = profileRequest.getPassword();

        User user = userRepository.findByUsername(username);

        if (user != null) {
            return null;
        }
        ProfileResponse profileResponse = new ProfileResponse();
        //profileResponse.setPassword(newPassword);
        String encrypted = passwordEncoder.encode(newPassword);
        user.setPassword(encrypted);
        profileResponse.setPassword(encrypted);
        /*
        if (veriftyPassword) {
            //TODO:jwtTokenProvider
            String token = jwtTokenProvider.generateToken(user);
            System.out.println("token = " + token);
            loginResponse.setMessage("True");
            loginResponse.setToken(token);

        return loginResponse;
        */
        return profileResponse;
    }

}
