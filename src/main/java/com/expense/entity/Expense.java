package com.expense.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
