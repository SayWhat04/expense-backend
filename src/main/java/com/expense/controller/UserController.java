package com.expense.controller;

import com.expense.domain.ExpenseDto;
import com.expense.domain.Filter;
import com.expense.domain.UserDto;
import com.expense.entity.Expense;
import com.expense.entity.User;
import com.expense.service.ExpenseService;
import com.expense.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ExpenseService expenseService;

    @PostMapping("/")
    public User addUser(@Valid @RequestBody UserDto user) {
        return this.userService.addUser(user);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    @PatchMapping("/{userId}/expenses")
    public void addExpensesToUser(@PathVariable int userId,
                                  @Valid @RequestBody List<ExpenseDto> expenseDtos) {
        List<Expense> expenses =
                this.expenseService.convertExpenseDtosToExpenseEntities(expenseDtos);
        User user = this.userService.getUserById(userId);
        user.addExpenses(expenses);
        this.expenseService.addExpenses(expenses);
    }

    @GetMapping("/{userId}/expenses")
    public List<Expense> getUserExpensesByCriteria(@PathVariable int userId) {
        return this.expenseService.getUserExpenses(userId);
    }

    @PostMapping("/{userId}/expenses")
    public List<Expense> getUserExpensesByCriteria(@PathVariable int userId,
                                                   @RequestBody List<Filter> filters) {
        return this.expenseService.getExpensesByCriteria(userId, filters);
    }
}
