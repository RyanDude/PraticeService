package com.example.saga.ServiceTest;

import com.example.saga.Dao.AccountRepository;
import com.example.saga.Dao.OrdersRepository;
import com.example.saga.Entity.Account;
import com.example.saga.Entity.Orders;
import com.example.saga.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private OrdersRepository ordersRepository;
    @InjectMocks
    private OrderService orderService;
    @Test
    public void createOrderTest(){
        Account account = Account.builder().id(1).username("abc").build();
        Orders orders = Orders.builder().id(2).account(account).build();
        when(accountRepository.findById(1)).thenReturn(Optional.of(account));
        when(ordersRepository.save(orders)).thenReturn(orders);
        Orders res = orderService.createOrder(orders, 1).join();
        verify(accountRepository).findById(1);
        verify(ordersRepository).save(orders);
        assertEquals(res.getId(), orders.getId());
    }
}
