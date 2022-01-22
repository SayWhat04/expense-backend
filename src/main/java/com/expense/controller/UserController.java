package com.expense.controller;

import com.expense.domain.ExpenseDto;
import com.expense.domain.Filter;
import com.expense.entity.Expense;
import com.expense.entity.User;
import com.expense.service.ExpenseService;
import com.expense.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ExpenseService expenseService;

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

    @DeleteMapping("/{userId}/expenses")
    public void deleteExpensesById(@RequestParam List<Integer> expenseIds) {
        this.expenseService.deleteExpensesById(expenseIds);
    }

    @PatchMapping("/{userId}/expenses/{expenseId}") // TODO: PATCH or PUT?
    public void editSingleExpense(@PathVariable int userId,
                                  @PathVariable int expenseId,
                                  @Valid @RequestBody ExpenseDto expenseDtos) {
        Expense expenses =
                this.expenseService.convertExpenseDtoToExpenseEntity(expenseDtos);
    }
}
