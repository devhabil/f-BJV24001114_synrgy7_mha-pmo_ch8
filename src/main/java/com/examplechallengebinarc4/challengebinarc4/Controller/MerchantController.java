package com.examplechallengebinarc4.challengebinarc4.Controller;

import com.examplechallengebinarc4.challengebinarc4.Entity.Merchant;
import com.examplechallengebinarc4.challengebinarc4.Service.MerchantService;
import com.examplechallengebinarc4.challengebinarc4.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/v1/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    public Response response;

//    @GetMapping(value = {"/list-merchant", "/list-merchant/"})
//    public ResponseEntity<Map> getListMerchantPagination() {
//        return new ResponseEntity<Map>(response.sucsess(merchantService.merchantPagination(0, 10)), HttpStatus.OK);
//    }

    @GetMapping(value = {"/all-merchant", "/all-merchant/"})
    public ResponseEntity<Map> getListMerchant() {
        return new ResponseEntity<Map>(response.sucsess(merchantService.getAllMerchant()), HttpStatus.OK);
    }

    @PostMapping(value = {"/save", "/save/"})
    public ResponseEntity<Map> saveMerchant(@RequestBody Merchant request) {
        Map obj = merchantService.insertMerchant(request);
        return new ResponseEntity<Map>(response.sucsess(obj), HttpStatus.OK);
    }

    @PutMapping(value = {"/update/{merchantId}", "/update/{merchantId}/"})
    public ResponseEntity<Map> updateMerchant(@RequestBody Merchant request, @PathVariable("merchantId") UUID merchantId) {
        Map obj = merchantService.updateMerchant(merchantId, request);
        return new ResponseEntity<Map>(response.sucsess(obj), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/delete/{merchantId}", "/delete/{merchantId}/"})
    public ResponseEntity<Map> deleteMerchant(@PathVariable("merchantId") UUID merchantId) {
        return new ResponseEntity<Map>(merchantService.deleteMerchant(merchantId), HttpStatus.OK);
    }

    @GetMapping(value = {"/get/{merchantId}", "/get/{merchantId}/"})
    public ResponseEntity<Map> getById(@PathVariable("merchantId") UUID merchantId) {
        return new ResponseEntity<Map>(merchantService.getMerchantById(merchantId), HttpStatus.OK);
    }
}

