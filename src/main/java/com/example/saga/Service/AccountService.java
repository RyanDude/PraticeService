package com.example.saga.Service;

import com.example.saga.Entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;


@Service
public class AccountService {
    private final com.example.saga.Dao.AccountRepository accountRepository;
    @Autowired
    public AccountService(com.example.saga.Dao.AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<Account> createAccount(Account account){
        return CompletableFuture.completedFuture(accountRepository.save(account));
    }
    @Async("taskExecutor")
    @Transactional
    @CachePut("getUser")
    public CompletableFuture<Optional<Account>> getUser(int uid){
        return CompletableFuture.completedFuture(this.accountRepository.findById(uid));
    }
    @Async("taskExecutor")
//    @CacheEvict("getUser")
    @Transactional
    public void updateUser(Account account){
        this.accountRepository.save(account);
    }
}
