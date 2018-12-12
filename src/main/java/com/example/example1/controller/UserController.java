package com.example.example1.controller;

import com.example.example1.dao.UserRepository;
import com.example.example1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping(path = "/demo")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/add")
    public void addNewUser(@RequestParam String name, @RequestParam String email) {
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        userRepository.save(u);
    }

    @GetMapping(path = "/all")
    @ResponseBody
    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/info")
    @ResponseBody
    public User findOne(@RequestParam Long id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(new User());
    }

    @GetMapping(path = "/delete")
    public void delete(@RequestParam Long id) {
        userRepository.deleteById(id);
    }
}
