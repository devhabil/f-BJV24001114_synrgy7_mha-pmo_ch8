package com.examplechallengebinarc4.challengebinarc4.Service.impl;

import com.examplechallengebinarc4.challengebinarc4.Entity.Merchant;
import com.examplechallengebinarc4.challengebinarc4.Entity.Order;
import com.examplechallengebinarc4.challengebinarc4.Repository.OrderRepository;
import com.examplechallengebinarc4.challengebinarc4.Service.OrderService;
import com.examplechallengebinarc4.challengebinarc4.utils.Response;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.*;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private Response response;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Map addOrder(Order order){
        try {
            if (response.checkNull(order.getDestination_address())) {
                return response.eror("Address is required.", 402);
            }
            if (StringUtils.isEmpty(order.getDestination_address())) {
                return response.eror("Address is required.", 402);
            }
            return response.sucsess(orderRepository.save(order));
        } catch (Exception e) {
            return response.eror("An error occurred while saving order.", 500);
        }
    }

    @Override
    public Map updateOrder(UUID orderId, Order order)  {
        try {
            Optional<Order> existingOrder = Optional.ofNullable(orderRepository.getByIdOrder(orderId));
            if (response.checkNull(order.getId())) {
                return response.eror("Id is required.", 402);
            }
            if (existingOrder.isPresent()) {
                Order edit = existingOrder.get();
                edit.setDestination_address(order.getDestination_address());
                return response.sucsess(orderRepository.save(edit));
            }
            return response.eror("id not found", 404);
        } catch (Exception e) {
            return response.eror(e.getMessage(), 500);
        }
    }

    @Override
    public Map deleteOrder(UUID orderId){
        try {
            Optional<Order> findOrderOptional = Optional.ofNullable(orderRepository.getByIdOrder(orderId));

            if (findOrderOptional.isPresent()) {
                Order order = findOrderOptional.get();
                orderRepository.delete(order);
                return response.sucsess("data has been deleted");

            } else {
                return response.eror("id not found", 404);
            }
        } catch (DataAccessException e) {
            return response.eror("An error occurred while deleting merchant", 500);
        }
    }

    @Override
    public Map getOrderById(UUID id) {
        Map map = new HashMap();
        Optional<Order> getId = orderRepository.findById(id);
        if (!getId.isPresent()) {
            return response.eror("id not found", 404);
        }
        return response.sucsess(getId.get());
    }

    @Override
    public Map pagination(int page, int size) {
        Pageable show_data = PageRequest.of(page,size, Sort.by("id").descending());
        Page<Order> list = orderRepository.getAllDataPage(show_data);
        return response.sucsess(list);
    }

    @Override
    public Optional<List<Order>> getAllOrder(){
        List<Order> orderList = orderRepository.findAll();
        return Optional.ofNullable(orderList.isEmpty()? null : orderList);
    }

}


