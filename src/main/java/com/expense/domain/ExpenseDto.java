package com.expense.domain;

import com.expense.entity.User;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
@Builder
public class ExpenseDto {
    private Date date;
    private BigInteger amount;
    private String category;
    private String subCategory;
    private String comment;
    private User user;
}
