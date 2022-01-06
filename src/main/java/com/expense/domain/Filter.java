package com.expense.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Builder
public class Filter {
    @NotBlank
    private String field;
    @NotBlank
    private String value;
    private QueryOperator queryOperator;
    private List<String> values;
}
