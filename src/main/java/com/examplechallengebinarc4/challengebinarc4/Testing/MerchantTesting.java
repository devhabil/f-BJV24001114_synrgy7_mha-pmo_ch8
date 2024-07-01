package com.examplechallengebinarc4.challengebinarc4.Testing;

import com.examplechallengebinarc4.challengebinarc4.Entity.Merchant;
import com.examplechallengebinarc4.challengebinarc4.Service.MerchantService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MerchantTesting {
        @Autowired
        public MerchantService merchantService;

        @Test
        public void addMerchant() {
            Merchant save = new Merchant();
            save.setMerchant_name("mha");
            save.setMerchant_location("Bandar Lampung");

            Map map = merchantService.insertMerchant(save);
            int responseCode = (Integer) map.get("status");
            Assert.assertEquals(200, responseCode);
        }
}