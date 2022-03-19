package com.expense;

import com.expense.measurements.ProfileExecutionAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ExpenseApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExpenseApplication.class, args);
    }

    @Bean
    public ProfileExecutionAspect profileExecutionAspect() {
        return new ProfileExecutionAspect();
    }
}
