package com.example.example1.controller;

import com.example.example1.dao.UserRepository;
import com.example.example1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/info")
    @ResponseBody
    public User findOne(@RequestParam Long id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(new User());
    }

    @GetMapping(path="/updateEmailByName")
    @ResponseBody
    public void updateEmailByName(@RequestParam String name,@RequestParam String email){
        userRepository.updateEmailByName(name,email);
    }

    @GetMapping(path="/deleteByName")
    @ResponseBody
    public void deleteByName(@RequestParam String name)
    {
        userRepository.deleteByName(name);
    }

    @GetMapping(path = "/all")
    @ResponseBody
    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }



    @GetMapping(path = "/delete")
    public void delete(@RequestParam Long id) {
        userRepository.deleteById(id);
    }

    /**
     * 验证排序和分页查询方法
     * @return
     */
    @GetMapping(path = "/page")
    @ResponseBody
    public Page<User> getAllUserByPage() {
        //已过时
        //return userRepository.findAll(new PageRequest(1, 20, new Sort(new Sort.Order(Sort.Direction.ASC, "name"))));
        return userRepository.findAll(PageRequest.of(1, 20, Sort.by(Sort.Order.asc("name"))));
    }

    /**
     * 验证排序方法
     * @return
     */
    @GetMapping(path="/sort")
    @ResponseBody
    public Iterable<User> getAllUsersWithSort(){
        return userRepository.findAll(Sort.by(Sort.Order.asc("name")));
    }

}
