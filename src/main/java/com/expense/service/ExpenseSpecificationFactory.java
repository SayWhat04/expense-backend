package com.expense.service;

import com.expense.domain.Filter;
import com.expense.entity.Expense;
import lombok.SneakyThrows;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseSpecificationFactory implements SpecificationFactory<Expense> {

    @SneakyThrows
    public Specification<Expense> createSpecification(Filter filter) {
        switch (filter.getQueryOperator()) {
            case EQUALS:
                return createSpecificationForEquals(filter);
            case NOT_EQUALS:
                return createSpecificationForNotEquals(filter);
            case GREATER_THAN:
                return createSpecificationForGreaterThan(filter);
            case LESS_THAN:
                return createSpecificationForLessThan(filter);
            case LIKE:
                return createSpecificationForLike(filter);
            case IN:
                return createSpecificationForIn(filter);
            default:
                throw new OperationNotSupportedException("Operation is not supported: " + filter.getQueryOperator());
        }
    }

    private Specification<Expense> createSpecificationForEquals(Filter filter) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get(filter.getField()),
                        castToRequiredType(root.get(filter.getField()).getJavaType(), filter.getValue()));
    }

    private Specification<Expense> createSpecificationForNotEquals(Filter filter) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.notEqual(
                        root.get(filter.getField()),
                        castToRequiredType(root.get(filter.getField()).getJavaType(), filter.getValue()));
    }

    private Specification<Expense> createSpecificationForGreaterThan(Filter filter) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.gt(
                        root.get(filter.getField()),
                        (Number) castToRequiredType(root.get(filter.getField()).getJavaType(), filter.getValue()));
    }

    private Specification<Expense> createSpecificationForLessThan(Filter filter) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lt(
                        root.get(filter.getField()),
                        (Number) castToRequiredType(root.get(filter.getField()).getJavaType(), filter.getValue()));
    }

    private Specification<Expense> createSpecificationForLike(Filter filter) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        root.get(filter.getField()),
                        "%" + filter.getValue() + "%");
    }

    private Specification<Expense> createSpecificationForIn(Filter filter) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.in(
                        root.get(filter.getField()))
                        .value(
                                castToRequiredType(root.get(filter.getField()).getJavaType(), filter.getValues()));
    }

    private Object castToRequiredType(Class fieldType, String value) {
        if (fieldType.isAssignableFrom(Double.class)) {
            return Double.valueOf(value);
        } else if (fieldType.isAssignableFrom(Integer.class)) {
            return Integer.valueOf(value);
        } else if (fieldType.isAssignableFrom(BigInteger.class)) {
            return new BigInteger(value);
        } else if (Enum.class.isAssignableFrom(fieldType)) {
            Enum anEnum = Enum.valueOf(fieldType, value);
            return anEnum;
        }
        return value;
    }

    private List<Object> castToRequiredType(Class fieldType, List<String> values) {
        return values.stream()
                .map(val -> castToRequiredType(fieldType, val))
                .collect(Collectors.toList());
    }
}
