package com.examplechallengebinarc4.challengebinarc4.Testing;

import com.examplechallengebinarc4.challengebinarc4.Entity.Merchant;
import com.examplechallengebinarc4.challengebinarc4.Entity.User;
import com.examplechallengebinarc4.challengebinarc4.Service.MerchantService;
import com.examplechallengebinarc4.challengebinarc4.Service.UserService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestingUser {
    @Autowired
    public UserService userService;

    @Test
    public void addUser() {
        User save = new User();
        save.setUsername("saiful");
        save.setEmailAddress("saiful2233@gmail.com");
        save.setPassword("saiful5566");

        Map map = userService.addUser(save);
        int responseCode = (Integer) map.get("status");
        Assert.assertEquals(200, responseCode);
    }
    @Test
    public void pagination(){
        Map map = userService.pagination(0,10);
        System.out.println(map);
        int responseCode = (Integer) map.get("status");
        Assert.assertEquals(200, responseCode);
    }
}