package com.expense;

import com.expense.dao.UserRepository;
import com.expense.entity.Expense;
import com.expense.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/user")
    public List<Expense> getUserExpenses(@RequestBody User user) {
        return null;
    }


    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        this.userRepository.save(user);
        return user;
    }
}
