package com.expense.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Filter {
    private String field;
    private String value;
    private QueryOperator queryOperator;
    private List<String> values;
}
