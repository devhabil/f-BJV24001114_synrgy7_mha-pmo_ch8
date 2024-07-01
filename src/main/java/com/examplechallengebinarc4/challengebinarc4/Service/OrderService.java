package com.examplechallengebinarc4.challengebinarc4.Service;

import com.examplechallengebinarc4.challengebinarc4.Entity.Merchant;
import com.examplechallengebinarc4.challengebinarc4.Entity.Order;
import com.examplechallengebinarc4.challengebinarc4.Entity.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    Map addOrder(Order order);
    Map updateOrder(UUID orderId, Order order);
    Map deleteOrder(UUID orderId);
    Map getOrderById(UUID id);
    Map pagination(int page, int size);
    Optional<List<Order>> getAllOrder();
    //testtt
}
