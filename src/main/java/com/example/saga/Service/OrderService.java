package com.example.saga.Service;

import com.example.saga.Dao.AccountRepository;
import com.example.saga.Dao.OrdersRepository;
import com.example.saga.Entity.Account;
import com.example.saga.Entity.Orders;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final AccountRepository accountRepository;
    @Autowired
    public OrderService(OrdersRepository ordersRepository,
                        AccountRepository accountRepository){
        this.ordersRepository = ordersRepository;
        this.accountRepository = accountRepository;
    }
    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<Orders> createOrder(Orders orders, int uid){
        Account account = this.accountRepository.findById(uid).get();
        orders.setAccount(account);
        orders.setCreateOrderTime(ZonedDateTime.now());
        return CompletableFuture.completedFuture(this.ordersRepository.save(orders));
    }
}
