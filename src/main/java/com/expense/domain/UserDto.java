package com.expense.domain;

import com.expense.entity.Expense;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {
    private String name;
    private String surname;
    private String email;
    private List<Expense> expenses;
}
