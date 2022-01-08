package com.expense.service;

import com.expense.domain.UserDto;
import com.expense.entity.User;
import com.expense.exception.UserExistsException;
import com.expense.infrastructure.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void register(UserDto user) throws UserExistsException {
        if (checkIfUserExists(user)) {
            throw new UserExistsException("User for this mail already exists!");
        }
        User userEntity = User.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .build();
        encodePassword(userEntity, user);
        userRepository.save(userEntity);
    }

    private boolean checkIfUserExists(UserDto user) {
        return userRepository.findByEmail(user.getEmail()) != null;
    }

    private void encodePassword(User userEntity, UserDto user) {
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}