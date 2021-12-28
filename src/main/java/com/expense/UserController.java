package com.expense;

import com.expense.domain.Filter;
import com.expense.entity.Expense;
import com.expense.entity.User;
import com.expense.service.ExpenseService;
import com.expense.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ExpenseService expenseService;

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/")
    public User addUser(@RequestBody User user) {
        return this.userService.addUser(user);
    }

    @PatchMapping("/{userId}/expenses")
    public void addExpensesToUser(@PathVariable int userId,
                                  @RequestBody List<Expense> expenses) {
        User user = this.userService.getUserById(userId);
        user.addExpenses(expenses);
        this.expenseService.addExpenses(expenses);
    }

    @GetMapping("/{userId}/expenses")
    public List<Expense> getUserExpensesByCriteria(@PathVariable int userId) {
        return this.expenseService.getUserExpenses(userId);
    }

    @PostMapping("/expenses") // TODO: Modify path and add @PathVariable with user id
    public List<Expense> getUserExpensesByCriteria(@RequestBody List<Filter> filters) {
        return this.expenseService.getExpensesByCriteria(filters);
    }
}
