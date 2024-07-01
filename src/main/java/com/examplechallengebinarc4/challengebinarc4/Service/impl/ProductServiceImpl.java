package com.examplechallengebinarc4.challengebinarc4.Service.impl;

import com.examplechallengebinarc4.challengebinarc4.Entity.Product;
import com.examplechallengebinarc4.challengebinarc4.Repository.ProductRepository;
import com.examplechallengebinarc4.challengebinarc4.Service.ProductService;
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
public class ProductServiceImpl implements ProductService {

    @Autowired
    private Response response;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Map addProduct(Product product) {
        try {
            if (response.checkNull(product.getProduct_name())) {
                return response.eror("name is required.", 402);
            }
            if (StringUtils.isEmpty(product.getProduct_name())) {
                return response.eror("Address is required.", 402);
            }
            if (response.checkNull(product.getPrice())) {
                return response.eror("price is required.", 402);
            }
            if (StringUtils.isEmpty(String.valueOf(product.getPrice()))) {
                return response.eror("price is required.", 402);
            }
            return response.sucsess(productRepository.save(product));
        } catch (Exception e) {
            return response.eror("An error occurred while saving Product.", 500);
        }
    }

    @Override
    public Map updateProduct(UUID productId, Product product) {
        try {
            Optional<Product> existingProduct = Optional.ofNullable(productRepository.getByIdProduct(productId));
            if (response.checkNull(product.getId())) {
                return response.eror("Id is required.", 402);
            }
            if (existingProduct.isPresent()) {
                Product edit = existingProduct.get();
                edit.setProduct_name(product.getProduct_name());
                return response.sucsess(productRepository.save(edit));
            }
            if (existingProduct.isPresent()) {
                Product edit = existingProduct.get();
                edit.setProduct_name(String.valueOf(product.getPrice()));
                return response.sucsess(productRepository.save(edit));
            }
            return response.eror("id not found", 404);
        } catch (Exception e) {
            return response.eror(e.getMessage(), 500);
        }
    }

    @Override
    public Map deleteProduct(UUID productId) {
        try {
            Optional<Product> findProductOptional = Optional.ofNullable(productRepository.getByIdProduct(productId));

            if (findProductOptional.isPresent()) {
                Product product = findProductOptional.get();
                productRepository.delete(product);
                return response.sucsess("data has been deleted");

            } else {
                return response.eror("id not found", 404);
            }
        } catch (DataAccessException e) {
            return response.eror("An error occurred while deleting order", 500);
        }
    }

    @Override
    public Map getProductById(UUID id) {
        Map map = new HashMap();
        Optional<Product> getId = productRepository.findById(id);
        if (!getId.isPresent()) {
            return response.eror("id not found", 404);
        }
        return response.sucsess(getId.get());
    }

    @Override
    public Map pagination(int page, int size) {
        Pageable show_data = PageRequest.of(page,size, Sort.by("id").descending());
        Page<Product> list = productRepository.getAllDataPage(show_data);
        return response.sucsess(list);
    }

    @Override
    public Optional<List<Product>> getAllProduct(){
        List<Product> productList = productRepository.findAll();
        return Optional.ofNullable(productList.isEmpty()? null : productList);
    }

}