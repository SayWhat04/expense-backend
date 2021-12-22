package com.expense.service;

import com.expense.dao.ExpenseRepository;
import com.expense.entity.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getUserExpenses(int userId) {
        List<Expense> expenses = this.expenseRepository.findExpenseByUserId(userId);
        for (Expense e : expenses) {
            e.setUser(null);
        }
        return expenses;
    }

    public void addExpenses(List<Expense> expenses) {
        this.expenseRepository.saveAll(expenses);
    }
}
