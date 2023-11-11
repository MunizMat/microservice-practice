package com.microservices.user.controllers;

import com.microservices.user.dtos.UserRecordDto;
import com.microservices.user.models.UserModel;
import com.microservices.user.services.UserService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto userRecordDto){
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel); // Converting DTO to model
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));

    }
}
