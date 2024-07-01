package com.examplechallengebinarc4.challengebinarc4.Service.impl;


import com.examplechallengebinarc4.challengebinarc4.Entity.Merchant;
import com.examplechallengebinarc4.challengebinarc4.Repository.MerchantRepository;
import com.examplechallengebinarc4.challengebinarc4.Service.MerchantService;
import com.examplechallengebinarc4.challengebinarc4.utils.Response;
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
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private Response response;

    @Override
    public Optional<List<Merchant>> getAllMerchant() {
        List<Merchant> merchantList = merchantRepository.findAll();
        return Optional.ofNullable(merchantList.isEmpty() ? null : merchantList);
    }

    @Override
    public Map insertMerchant(Merchant merchant) {
        try {
            if (response.checkNull(merchant.getMerchant_name())) {
                return response.eror("MerchantName is required.", 402);
            }
            if (StringUtils.isEmpty(merchant.getMerchant_name())) {
                return response.eror("MerchantName is required.", 402);
            }
            return response.sucsess(merchantRepository.save(merchant));
        } catch (Exception e) {
            return response.eror("An error occurred while saving merchant.", 500);
        }
    }

    @Override
    public Map updateMerchant(UUID merchantId, Merchant merchant) {
        try {
            Optional<Merchant> existingMerchant = Optional.ofNullable(merchantRepository.getByIdMerchant(merchantId));
            if (response.checkNull(merchant.getId())) {
                return response.eror("Id is required.", 402);
            }
            if (existingMerchant.isPresent()) {
                Merchant edit = existingMerchant.get();
                edit.setMerchant_name(merchant.getMerchant_name());
                edit.setMerchant_location(merchant.getMerchant_location());
                edit.setOpen(merchant.isOpen());

                return response.sucsess(merchantRepository.save(edit));
            }
            return response.eror("id not found", 404);
        } catch (Exception e) {
            return response.eror(e.getMessage(), 500);
        }
    }

    @Override
    public Map deleteMerchant(UUID merchantId) {
        try {
            Optional<Merchant> findMerchantOptional = Optional.ofNullable(merchantRepository.getByIdMerchant(merchantId));

            if (findMerchantOptional.isPresent()) {
                Merchant merchant = findMerchantOptional.get();
                merchantRepository.delete(merchant);
                return response.sucsess("data berhasil dihapus");

            } else {
                return response.eror("id not found", 404);
            }
        } catch (DataAccessException e) {
            return response.eror("An error occurred while deleting merchant", 500);
        }
    }
    @Override
    public Map getMerchantById(UUID id) {
        Optional<Merchant> getId = merchantRepository.findById(id);
        if (!getId.isPresent()) {
            return response.eror("id not found", 404);
        }
        return response.sucsess(getId.get());
    }
//    @Override
//    public Map merchantPagination(int page, int size) {
//        Pageable showMerchant = PageRequest.of(page, size, Sort.by("merchantname").descending());
//        Page<Merchant> list = (Page<Merchant>) merchantRepository.getAllDataPage(showMerchant);
//        return response.sucsess(list);
//    }
}

