package com.expense.service;

import com.expense.domain.Filter;
import com.expense.entity.Expense;
import com.expense.infrastructure.dao.CustomExpenseRepository;
import com.expense.infrastructure.dao.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final CustomExpenseRepository customExpenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, CustomExpenseRepository customExpenseRepository) {
        this.expenseRepository = expenseRepository;
        this.customExpenseRepository = customExpenseRepository;
    }

    public void addExpenses(List<Expense> expenses) {
        this.expenseRepository.saveAll(expenses);
    }

    public List<Expense> getUserExpenses(int userId) {
        List<Expense> expenses = this.expenseRepository.findExpenseByUserId(userId);
        setUserReferenceToNull(expenses);
        return expenses;
    }

    public List<Expense> getUserExpensesByCategoryAndSubcategory(int userId, String category, String subCategory) {
        List<Expense> expenses = this.expenseRepository
                .findExpenseByUserIdByCategoryBySubCategory(userId, category, subCategory);
        setUserReferenceToNull(expenses);
        return expenses;
    }

    public List<Expense> getExpensesByCriteria(List<Filter> filters) {
        return this.customExpenseRepository.getQueryResult(filters);
    }

    private void setUserReferenceToNull(List<Expense> expenses) {
        for (Expense e : expenses) {
            e.setUser(null);
        }
    }
}
