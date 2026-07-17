package com.enterprises.TechStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprises.TechStore.entity.User;
import com.enterprises.TechStore.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User login(String email, String password) {

        System.out.println("Username from UI : " + email);
        System.out.println("Password from UI : " + password);

        User user = repository.findByEmail(email);

        System.out.println("User from DB : " + user);

        if(user != null) {
            System.out.println("DB Password : " + user.getPassword());

            if(user.getPassword().equals(password)) {
                System.out.println("Password Matched");
                return user;
            }
        }

        System.out.println("Login Failed");

        return null;
    }

}