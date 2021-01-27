package com.sachith.movieapp.service.Impl;

import com.apple.eawt.Application;
import com.sachith.movieapp.model.User;
import com.sachith.movieapp.repository.UserRepository;
import com.sachith.movieapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    private static final Logger logger= Logger.getLogger(String.valueOf(Application.class));

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            logger.info(getClass().toString()+" << User not found with username: " + username + ">>");
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    @Override
    public User userRegister(User user) {
        logger.info(getClass().toString() +" << User Register Done >>");
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setName(user.getName());
        newUser.setPosition(user.getPosition());
        newUser.setMobile(user.getMobile());
        return userRepository.save(newUser);
    }



    @Override
    public boolean userLogin(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            logger.info(getClass().toString()+" << User Authenticated >>");
            return true;
        } catch (BadCredentialsException e) {
            logger.info(getClass().toString()+" << Bad Credentials >>");
            return false;
        }
    }

    @Override
    public User getUserByName(String username) {
        logger.info(getClass().toString() +" << Get User By Name >>");
        User user = userRepository.findByUsername(username);
        return user;
    }

}
