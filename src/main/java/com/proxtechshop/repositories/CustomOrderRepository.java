package com.proxtechshop.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.proxtechshop.entities.Order;

@Component
public interface CustomOrderRepository {

	List<Order> listOrderInStartAndEndDate(Date start, Date end);
}
