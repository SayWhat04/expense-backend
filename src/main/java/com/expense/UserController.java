package com.expense;

import com.expense.entity.Expense;
import com.expense.entity.User;
import com.expense.service.ExpenseService;
import com.expense.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ExpenseService expenseService;

    @Autowired
    public UserController(UserService userService, ExpenseService expenseService) {
        this.userService = userService;
        this.expenseService = expenseService;
    }

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
    public List<Expense> getUserExpensesByCriteria(@PathVariable int userId,
                                                   @RequestParam(name = "category") String category,
                                                   @RequestParam(name = "subCategory") String subCategory) {

        return this.expenseService.getUserExpensesByCriteria(userId, category, subCategory);
    }
}
