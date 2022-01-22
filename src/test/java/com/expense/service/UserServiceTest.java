package com.expense.service;

import com.expense.infrastructure.dao.UserRepository;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class UserServiceTest {
    private UserRepository userRepository = mock(UserRepository.class);
    private UserService userService;

    @BeforeEach
    void initUseCase() {
        // userService = new UserService(userRepository);
    }
}
