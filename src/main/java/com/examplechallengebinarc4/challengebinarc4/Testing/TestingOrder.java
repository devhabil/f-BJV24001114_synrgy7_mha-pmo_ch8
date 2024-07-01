package com.examplechallengebinarc4.challengebinarc4.Testing;

import com.examplechallengebinarc4.challengebinarc4.Entity.Order;
import com.examplechallengebinarc4.challengebinarc4.Service.OrderService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TestingOrder {

    @Autowired
    private OrderService orderService;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void addOrder(){
        Order save = new Order();
        save.setDestination_address("Denpasar");

        Map map = orderService.addOrder(save);
        int responseCode = (Integer) map.get("status");
        Assert.assertEquals(200, responseCode);
    }
    @Test
    public void pagination(){
        Map map = orderService.pagination(0,10);
        System.out.println(map);
        int responseCode = (Integer) map.get("status");
        Assert.assertEquals(200, responseCode);
    }

//    @Test
//    public void listSukses() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Accept", "*/*");
//        headers.set("Content-Type", "application/json");
//
//        ResponseEntity<Object> exchange = restTemplate.exchange("http://dev.farizdotid.com/api/daerahindonesia/provinsi", HttpMethod.GET, null, Object.class);
//        System.out.println("response  =" + exchange.getBody());

        // get value
//        assertEquals(HttpStatus.OK, exchange.getStatusCode());
    }
