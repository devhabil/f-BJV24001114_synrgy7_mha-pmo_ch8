package com.examplechallengebinarc4.challengebinarc4.Service;

import com.examplechallengebinarc4.challengebinarc4.Entity.Merchant;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface MerchantService {
    Optional<List<Merchant>> getAllMerchant();
    public Map insertMerchant(Merchant merchant);
    public Map updateMerchant(UUID merchantId, Merchant merchant);
    public Map deleteMerchant(UUID merchantId);
    public Map getMerchantById(UUID id);
//    public Map merchantPagination(int page, int size);
}


