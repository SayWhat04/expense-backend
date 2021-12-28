package com.expense.infrastructure.dao;

import com.expense.domain.Filter;
import com.expense.entity.Expense;
import com.expense.service.ExpenseSpecificationFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomExpenseRepository {
    private final ExpenseRepository expenseRepository;
    private final ExpenseSpecificationFactory expenseSpecificationFactory;

    // TODO: Implement fetching Expenses only for passed userId
    public List<Expense> getQueryResult(List<Filter> filters) {
        if (!filters.isEmpty()) {
            return expenseRepository.findAll(createSpecificationFromFilters(filters));
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