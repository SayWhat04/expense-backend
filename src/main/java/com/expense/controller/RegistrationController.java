package com.expense.controller;

import com.expense.domain.UserDto;
import com.expense.exception.UserExistsException;
import com.expense.service.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final UserRegistrationService userRegistrationService;

    @PostMapping("/registration")
    public String registerUser(@Valid @RequestBody UserDto user) throws UserExistsException {
        userRegistrationService.register(user);
        return "User successfully registered"; // FIXME: Change to more meaningful message
    }
}