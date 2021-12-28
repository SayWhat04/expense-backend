package com.expense.infrastructure.dao;

import com.expense.domain.Filter;
import com.expense.entity.Expense;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomExpenseRepository {
    private final ExpenseRepository expenseRepository;

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
                .map(this::createSpecification)
                .reduce(Specification::and)
                .orElseThrow(() ->
                        new IllegalStateException("Unable to combine Specification for provided filters: " + filters));
    }

    @SneakyThrows
    private Specification<Expense> createSpecification(Filter filter) {
        switch (filter.getQueryOperator()) {
            case EQUALS:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(
                                root.get(filter.getField()),
                                castToRequiredType(root.get(filter.getField()).getJavaType(), filter.getValue()));
            case NOT_EQUALS:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.notEqual(
                                root.get(filter.getField()),
                                castToRequiredType(root.get(filter.getField()).getJavaType(), filter.getValue()));
            case GREATER_THAN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.gt(
                                root.get(filter.getField()),
                                (Number) castToRequiredType(root.get(filter.getField()).getJavaType(), filter.getValue()));
            case LESS_THAN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.lt(
                                root.get(filter.getField()),
                                (Number) castToRequiredType(root.get(filter.getField()).getJavaType(), filter.getValue()));
            case LIKE:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.like(
                                root.get(filter.getField()),
                                "%" + filter.getValue() + "%");
            case IN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.in(
                                root.get(filter.getField()))
                                .value(
                                        castToRequiredType(root.get(filter.getField()).getJavaType(), filter.getValues()));
            default:
                throw new OperationNotSupportedException("Operation is not supported: " + filter.getQueryOperator());
        }
    }

    private Object castToRequiredType(Class fieldType, String value) {
        if (fieldType.isAssignableFrom(Double.class)) {
            return Double.valueOf(value);
        } else if (fieldType.isAssignableFrom(Integer.class)) {
            return Integer.valueOf(value);
        } else if (Enum.class.isAssignableFrom(fieldType)) {
            return Enum.valueOf(fieldType, value);
        }
        return null;
    }

    private List<Object> castToRequiredType(Class fieldType, List<String> values) {
        return values.stream()
                .map(val -> castToRequiredType(fieldType, val))
                .collect(Collectors.toList());
    }
}