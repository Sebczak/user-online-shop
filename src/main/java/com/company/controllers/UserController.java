package com.company.controllers;

import com.company.dtos.CartDto;
import com.company.dtos.UserDto;
import com.company.entities.Cart;
import com.company.mapper.Mapper;
import com.company.sevices.CartService;
import com.company.sevices.UserService;
import com.company.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;
    private final Mapper mapper;
    private final CartService cartService;

    @Autowired
    public UserController(UserService userService, Mapper mapper, CartService cartService) {
        this.userService = userService;
        this.mapper = mapper;
        this.cartService = cartService;
    }

    @GetMapping
    public List<UserDto> getUsers() {
        List<User> users = userService.getUsers();
        return mapper.toUserDtos(users);
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateUserFirstnameAndEmail(@PathVariable("userId") Long userId, @RequestParam(required = false) String firstname, @RequestParam(required = false) String email) {
        userService.updateUser(userId, firstname, email);
    }
}
