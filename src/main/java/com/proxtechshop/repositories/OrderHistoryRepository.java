package com.proxtechshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.OrderHistory;

@Component
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Integer> {

}
