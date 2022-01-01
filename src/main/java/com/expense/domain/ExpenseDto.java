package com.expense.domain;

import com.expense.entity.User;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;
import java.util.Date;

@Data
@Builder
public class ExpenseDto {
    @NotNull(message = "Expense date is mandatory")
    private Date date;

    @Positive(message = "Money amount can't be negative")
    @NotNull(message = "Expense amount is mandatory")
    private BigInteger amount;

    @NotBlank(message = "Expense category is mandatory")
    private String category;

    @NotBlank(message = "Expense subCategory is mandatory")
    private String subCategory;

    @Max(value = 40, message = "Comment should be shorter than 40 characters")
    private String comment;

    private User user;
}
