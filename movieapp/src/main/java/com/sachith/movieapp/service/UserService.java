package com.sachith.movieapp.service;

import com.sachith.movieapp.model.User;

public interface UserService {

    User userRegister(User user);

    boolean userLogin(String Username, String Password) throws Exception;

    User getUserByName(String username);

}
