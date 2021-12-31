package com.expense.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "expense")
public class Expense {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "amount")
    private BigInteger amount;
    // private Money amount;

    @Column(name = "category")
    private String category;

    @Column(name = "subCategory")
    private String subCategory;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
