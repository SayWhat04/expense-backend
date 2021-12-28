package com.expense.service;

import com.expense.entity.User;
import com.expense.infrastructure.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserById(int userId) {
        User user = this.userRepository.getById(userId);
        return user;
    }

    public User addUser(User user) {
        this.userRepository.save(user);
        return user;
    }
}