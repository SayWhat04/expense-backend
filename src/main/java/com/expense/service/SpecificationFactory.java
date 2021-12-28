package com.expense.service;

import com.expense.domain.Filter;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationFactory<T> {
    Specification<T> createSpecification(Filter filter);
}
