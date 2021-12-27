package com.expense.service;

import com.expense.entity.User;
import com.expense.infrastructure.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(int userId) {
        User user = this.userRepository.getById(userId);
        return user;
    }

    public User addUser(User user) {
        this.userRepository.save(user);
        return user;
    }

}
