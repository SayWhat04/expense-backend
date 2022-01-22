package com.expense.service;

import com.expense.domain.CreateUserRequest;
import com.expense.domain.UserDto;
import com.expense.entity.User;
import com.expense.infrastructure.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserById(int userId) {
        return this.userRepository.getById(userId);
    }

    @Transactional
    public User addUser(UserDto user) {
        User userEntity = User.builder()
                .username(user.getUsername())
                .fullName(user.getFullName())
                .expenses(user.getExpenses())
                .build();
        return this.userRepository.save(userEntity);
    }

    @Transactional
    public User create(CreateUserRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ValidationException("Username exists!");
        }
        if (!request.getPassword().equals(request.getRePassword())) {
            throw new ValidationException("Passwords don't match!");
        }
        if (request.getAuthorities() == null) {
            request.setAuthorities(new HashSet<>());
        }

        User userEntity = User.builder()
                .username(request.getUsername())
                .fullName(request.getFullName())
                .password(passwordEncoder.encode(request.getPassword()))
                .expenses(request.getExpenses())
                .build();

        return userRepository.save(userEntity);
    }


}