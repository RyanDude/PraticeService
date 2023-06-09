package com.example.saga.Controller;

import com.example.saga.Entity.Account;
import com.example.saga.Entity.Orders;
import com.example.saga.Service.AccountService;
import com.example.saga.Service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@RestController
public class Controller {
    private AccountService accountService;
    private OrderService orderService;
    @Autowired
    public void setAccountService(AccountService accountService){
        this.accountService = accountService;
    }
    @Autowired
    public void setOrderService(OrderService orderService){
        this.orderService = orderService;
    }
    @PostMapping("/api/reg")
    public ResponseEntity<Account> reg(@RequestBody @Valid Account account){
        account = accountService.createAccount(account).join();
        return ResponseEntity.ok(account);
    }
    @GetMapping("/api/{uid}/get")
    public ResponseEntity<Account> getAccount(@PathVariable int uid){
        Account account = this.accountService.getUser(uid).join().get();
        return ResponseEntity.ok(account);
    }
    @PostMapping("/api/{uid}/update")
    public ResponseEntity<String> updateAccount(@RequestBody @Valid Account account, @PathVariable int uid){
        account.setId(uid);
        accountService.updateUser(account);
        return ResponseEntity.ok("ok");
    }
    @PostMapping("/api/{uid}/order")
    public ResponseEntity<Orders> createOrder(@PathVariable int uid, @RequestBody Orders orders){
        return ResponseEntity.ok(this.orderService.createOrder(orders, uid).join());
    }
}
