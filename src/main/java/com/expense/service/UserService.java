package com.expense.service;

import com.expense.domain.UserDto;
import com.expense.entity.User;
import com.expense.infrastructure.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserById(int userId) {
        return this.userRepository.getById(userId);
    }

    public User addUser(UserDto user) {
        User userEntity = User.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .expenses(user.getExpenses())
                .build();
        return this.userRepository.save(userEntity);
    }
}