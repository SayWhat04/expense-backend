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
    @Email(message = "User email is invalid")
    @NotBlank(message = "User name is mandatory")
    private String username;

    @NotBlank(message = "User fullName is mandatory")
    private String fullName;

    @NotBlank(message = "Password is mandatory")
    private String password;

    private List<Expense> expenses;
}
