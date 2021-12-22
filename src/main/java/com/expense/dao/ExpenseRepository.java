package com.expense.dao;

import com.expense.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    @Query("SELECT e FROM Expense e WHERE e.user.id = :userId")
    List<Expense> findExpenseByUserId(@Param("userId") Integer userId);
}
