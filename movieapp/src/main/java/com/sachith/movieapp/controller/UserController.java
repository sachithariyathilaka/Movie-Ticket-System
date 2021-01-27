package com.sachith.movieapp.controller;

import com.apple.eawt.Application;
import com.sachith.movieapp.model.User;
import com.sachith.movieapp.repository.UserRepository;
import com.sachith.movieapp.service.Impl.UserServiceImpl;
import com.sachith.movieapp.config.JwtRequest;
import com.sachith.movieapp.config.JwtResponse;
import com.sachith.movieapp.dto.UserResponseDto;
import com.sachith.movieapp.utill.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@CrossOrigin
public class UserController {

    private static final Logger logger= Logger.getLogger(String.valueOf(Application.class));

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody JwtRequest credentials, @RequestParam String position) throws Exception {
        logger.info(getClass().toString() + " << User Login Controller >>");
        User newUser = userRepository.findByUsernameAndPosition(credentials.getUsername(), position);
        if(newUser != null){
            boolean auth = userServiceImpl.userLogin(credentials.getUsername(), credentials.getPassword());
            if(auth){
                final UserDetails userDetails = userServiceImpl.loadUserByUsername(credentials.getUsername());
                final User user = userServiceImpl.getUserByName(credentials.getUsername());
                final String token = jwtTokenUtil.generateToken(userDetails);
                return ResponseEntity.ok(new UserResponseDto(token, user.getId(), "User Authenticated",1));
            } else{
                logger.info(getClass().toString()+" << User Credentials Invalid >>");
                return ResponseEntity.ok(new JwtResponse("Invalid"));
            }
        } else {
            logger.info(getClass().toString()+" << User Credentials Invalid >>");
            return ResponseEntity.ok(new JwtResponse("Invalid"));
        }

    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        logger.info(getClass().toString()+" << User Register Controller >>");
        User newUser = userServiceImpl.userRegister(user);
        return ResponseEntity.ok(new UserResponseDto("", newUser.getId(), "User register success", 1));
    }
}
