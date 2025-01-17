package com.companies.enterprise.controllers;

import com.companies.enterprise.dto.UserDto;
import com.companies.enterprise.services.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UserSevice userSevice;

    @PostMapping
    private UserDto save(@RequestBody UserDto userDto) {
        return userSevice.save(userDto);
    }
}
