package com.examplechallengebinarc4.challengebinarc4.Controller;

import com.examplechallengebinarc4.challengebinarc4.Entity.Product;
import com.examplechallengebinarc4.challengebinarc4.Repository.ProductRepository;
import com.examplechallengebinarc4.challengebinarc4.Service.ProductService;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Response response;

    @GetMapping(value = {"/all-product", "/all-Product/"})
    public ResponseEntity<Map> getListProduct() {
        return new ResponseEntity<Map>(response.sucsess(productService.getAllProduct()), HttpStatus.OK);
    }

    @PostMapping(value = {"/save", "/save/"})
    public ResponseEntity<Map> saveProduct(@RequestBody Product request) {
        Map obj = productService.addProduct(request);
        return new ResponseEntity<Map>(response.sucsess(obj), HttpStatus.OK);
    }

    @PutMapping(value = {"/update/{productId}", "/update/{productId}/"})
    public ResponseEntity<Map> updateProduct(@RequestBody Product request, @PathVariable("productId") UUID ProductId) {
        Map obj = productService.updateProduct(ProductId, request);
        return new ResponseEntity<Map>(response.sucsess(obj), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/delete/{productId}", "/delete/{productId}/"})
    public ResponseEntity<Map> deleteProduct(@PathVariable("productId") UUID ProductId) {
        return new ResponseEntity<Map>(productService.deleteProduct(ProductId), HttpStatus.OK);
    }

    @GetMapping(value = {"/get/{productId}", "/get/{productId}/"})
    public ResponseEntity<Map> getById(@PathVariable("productId") UUID ProductId) {
        return new ResponseEntity<Map>(productService.getProductById(ProductId), HttpStatus.OK);
    }

    @PostMapping(value = {"/response-object-emp", "/response-object-emp/"})
    public Product exString(@RequestBody Product req) {
        return req;
    }

    @GetMapping(value = { "/list-Product", "/list-Product/" })
    public ResponseEntity<Map> list(@RequestParam(required = false, name = "product_name") String product_name,
                                    @RequestParam(required = false, name = "price") BigDecimal price,
                                    @PageableDefault(page = 0, size = 10) Pageable pageable) {

        Specification<Product> spec = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (product_name != null && !product_name.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + product_name.toLowerCase() + "%"));
            }
            if (price != null) {
                predicates.add(criteriaBuilder.equal(root.get("price"), price));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        Page<Product> clientCompanyList = productRepository.findAll(spec, pageable);
        return new ResponseEntity<Map>(response.sucsess(clientCompanyList), HttpStatus.OK);
    }
}
