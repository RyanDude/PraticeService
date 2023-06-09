package com.example.saga.ControllerTest;

import com.example.saga.Controller.Controller;
import com.example.saga.Entity.Account;
import com.example.saga.Entity.Orders;
import com.example.saga.Service.AccountService;
import com.example.saga.Service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = Controller.class)
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AccountService accountService;
    @MockBean
    private OrderService orderService;
    @BeforeAll
    public static void prepare(){
        System.out.println("test started");
    }
    @AfterAll
    public static void end(){System.out.println("test ended");}
    @Test
    public void getAccountTest() throws Exception {
        Account account = Account.builder().id(1).username("abc").build();
        Optional<Account> optional = Optional.of(account);
        System.out.println(optional.get());
        when(accountService.getUser(1)).thenReturn(CompletableFuture.completedFuture(optional));
        MvcResult result = mockMvc.perform(get("/api/1/get"))
                .andExpect(status().isOk())
                .andReturn();
        Account account1 = objectMapper.readValue(result.getResponse().getContentAsString(), Account.class);
        assert account1.getUsername().equals(account.getUsername());
    }
    @Test
    public void updateAccountTest() throws Exception{
        Account account = Account.builder().id(1).username("abc").build();
        Account updated = Account.builder().id(1).username("abc").build();
        doAnswer(i->{System.out.println("the method is called");return null;}).when(accountService).updateUser(updated);
        mockMvc.perform(post("/api/1/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated))
                )
                .andExpect(status().isOk());
    }
}
