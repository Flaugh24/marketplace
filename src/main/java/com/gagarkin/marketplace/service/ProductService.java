package com.gagarkin.marketplace.service;

import com.gagarkin.marketplace.model.Product;
import com.gagarkin.marketplace.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> products(){
        return productRepository.findAll(Sort.by(Sort.Order.asc("id")));
    }

    public Optional<Product> product(long id){
        return productRepository.findById(id);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public void delete(long id){
        productRepository.deleteById(id);
    }
}
