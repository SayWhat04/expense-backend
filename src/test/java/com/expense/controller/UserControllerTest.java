package com.expense.controller;

import com.expense.domain.UserDto;
import com.expense.service.ExpenseService;
import com.expense.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private ExpenseService expenseService;

    @Test
    void should_return200_when_getUserWithValidUrlAndMethod() throws Exception {
        mockMvc.perform(get("/user/{userId}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void should_return200_when_postValidUser() throws Exception {
        UserDto user = UserDto.builder()
                .username("email@email.com")
                .fullName("surname")
                .expenses(new ArrayList<>())
                .build();

        mockMvc.perform(post("/user/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    void should_return400_when_postInvalidUser() throws Exception {
        UserDto user = UserDto.builder()
                .username("")
                .fullName("")
                .expenses(new ArrayList<>())
                .build();

        mockMvc.perform(post("/user/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return200_when_getUserExpensesWithValidUrlAndMethod() throws Exception {
        mockMvc.perform(get("/user/{userId}/expenses", 1))
                .andExpect(status().isOk());
    }

    @Test
    void should_return200_when_getExpensesByCriteriaWithValidUrlAndMethod() throws Exception {
        mockMvc.perform(post("/user/expenses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new ArrayList<>())))
                .andExpect(status().isOk());
    }

}
