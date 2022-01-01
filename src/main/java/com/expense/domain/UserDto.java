package com.expense.domain;

import com.expense.entity.Expense;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
public class UserDto {
    @NotBlank(message = "User name is mandatory")
    private String name;

    @NotBlank(message = "User surname is mandatory")
    private String surname;

    @Email(message = "User email is invalid")
    @NotBlank(message = "User email is mandatory")
    private String email;

    private List<Expense> expenses;
}
