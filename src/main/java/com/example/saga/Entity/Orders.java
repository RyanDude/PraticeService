package com.example.saga.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private ZonedDateTime createOrderTime;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private Account account;
    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProductOrders> productOrdersList;
}
