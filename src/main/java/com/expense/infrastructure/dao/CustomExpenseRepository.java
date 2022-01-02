package com.expense.infrastructure.dao;

import com.expense.domain.Filter;
import com.expense.entity.Expense;
import com.expense.service.ExpenseSpecificationFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomExpenseRepository {
    private final ExpenseRepository expenseRepository;
    private final ExpenseSpecificationFactory expenseSpecificationFactory;

    public List<Expense> getQueryResult(int userId, List<Filter> filters) {
        if (!filters.isEmpty()) {
            List<Expense> resultExpenses = expenseRepository.findAll(createSpecificationFromFilters(filters));
            // FIXME: Workaround solution. Implement later with use of Specifications.
            // FIXME: Can't stay like this because of very low performance and poor design.
            return resultExpenses.stream()
                    .filter(expense -> expense.getUser().getId() == userId)
                    .collect(Collectors.toList());
        } else {
            return expenseRepository.findAll();
        }
    }

    private Specification<Expense> createSpecificationFromFilters(List<Filter> filters) {
        return filters.stream()
                .map(this.expenseSpecificationFactory::createSpecification)
                .reduce(Specification::and)
                .orElseThrow(() ->
                        new IllegalStateException("Unable to combine Specification for provided filters: " + filters));
    }
}