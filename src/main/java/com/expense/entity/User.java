package com.expense.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Expense> expenses;

    public void addExpenses(List<Expense> expenses) {
        this.expenses.addAll(expenses);
        for (Expense expense : expenses) {
            expense.setUser(this);
        }
    }
}
