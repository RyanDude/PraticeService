package com.example.saga.ServiceTest;

import com.example.saga.Dao.AccountRepository;
import com.example.saga.Entity.Account;
import com.example.saga.Service.AccountService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    // use the real code
    @InjectMocks
    private AccountService accountService;
    @Mock
    private AccountRepository accountRepository;
    @BeforeAll
    public static void beforeAll(){
        System.out.println("Account Service Test started");
    }
    @AfterAll
    public static void afterAll(){
        System.out.println("Account Service Test Ended");
    }
    @Test
    public void createAccountTest(){
        Account account = Account.builder().username("abc").build();
        when(accountRepository.save(account)).thenReturn(account);
        Account result = accountService.createAccount(account).join();
        assert result.getUsername().equals(account.getUsername());
    }
    @Test
    public void getUserTest(){
        Account account = Account.builder().id(1).username("abc").build();
        when(accountRepository.findById(1)).thenReturn(Optional.of(account));
        Account res = accountService.getUser(1).join().get();
        assertEquals(res.getId(), account.getId());
        assertEquals(res.getUsername(), account.getUsername());
    }
    @Test
    public void updateUserTest(){
        Account account = Account.builder().username("abc").id(1).build();
        when(accountRepository.save(account)).thenReturn(account);
        accountService.updateUser(account);
        verify(accountRepository).save(account);
    }
}
