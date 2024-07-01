package com.examplechallengebinarc4.challengebinarc4.Controller;

import com.examplechallengebinarc4.challengebinarc4.Entity.Merchant;
import com.examplechallengebinarc4.challengebinarc4.Entity.Order;
import com.examplechallengebinarc4.challengebinarc4.Repository.OrderRepository;
import com.examplechallengebinarc4.challengebinarc4.Service.MerchantService;
import com.examplechallengebinarc4.challengebinarc4.Service.OrderService;
import com.examplechallengebinarc4.challengebinarc4.utils.Response;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Response response;

    @GetMapping(value = {"/all-order", "/all-order/"})
    public ResponseEntity<Map> getListOrder() {
        return new ResponseEntity<Map>(response.sucsess(orderService.getAllOrder()), HttpStatus.OK);
    }
    @PostMapping(value = {"/save", "/save/"})
    public ResponseEntity<Map> saveOrder(@RequestBody Order request) {
        Map obj = orderService.addOrder(request);
        return new ResponseEntity<Map>(response.sucsess(obj), HttpStatus.OK);
    }
    @PutMapping(value = {"/update/{orderId}", "/update/{orderId}/"})
    public ResponseEntity<Map> updateOrder(@RequestBody Order request, @PathVariable("orderId") UUID orderId) {
        Map obj = orderService.updateOrder(orderId, request);
        return new ResponseEntity<Map>(response.sucsess(obj), HttpStatus.OK);
    }
    @DeleteMapping(value = {"/delete/{orderId}", "/delete/{orderId}/"})
    public ResponseEntity<Map> deleteOrder(@PathVariable("orderId") UUID orderId) {
        return new ResponseEntity<Map>(orderService.deleteOrder(orderId), HttpStatus.OK);
    }
    @GetMapping(value = {"/get/{orderId}", "/get/{orderId}/"})
    public ResponseEntity<Map> getById(@PathVariable("orderId") UUID orderId) {
        return new ResponseEntity<Map>(orderService.getOrderById(orderId), HttpStatus.OK);
    }
    @PostMapping(value = {"/response-object-emp", "/response-object-emp/"})
    public Order exString(@RequestBody Order req) {
        return req;
    }
    @GetMapping(value = { "/list-order", "/list-order/" })
    public ResponseEntity<Map> list(@RequestParam(required = false, name = "destination_address") String destination_address,
//                                    @RequestParam(required = false, name = "address") String address,
//                                    @RequestParam(required = false, name = "nik") String nik,
                                    @PageableDefault(page = 0, size = 10) Pageable pageable) {

        Specification<Order> spec = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (destination_address != null && !destination_address.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + destination_address.toLowerCase() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });

        Page<Order> clientCompanyList = orderRepository.findAll(spec, pageable);
        return new ResponseEntity<Map>(response.sucsess(clientCompanyList), HttpStatus.OK);

        //testttt
    }
}
