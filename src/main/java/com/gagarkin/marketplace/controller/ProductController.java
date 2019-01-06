package com.gagarkin.marketplace.controller;

import com.gagarkin.marketplace.dto.ProductDTO;
import com.gagarkin.marketplace.exceptions.NotFoundException;
import com.gagarkin.marketplace.model.Product;
import com.gagarkin.marketplace.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "products")
public class ProductController {

    private static Logger LOG = Logger.getLogger(ProductController.class.getName());

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> products() {
        return productService.products()
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public Product save(@RequestBody @Validated ProductDTO productDTO) {

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        return productService.save(product);
    }

    @GetMapping(value = "{id}")
    public ProductDTO product(@PathVariable("id") Product product) {
        if(product == null)
            throw new NotFoundException();
        return new ProductDTO(product);
    }

    @PutMapping(value = "{id}")
    public Product update(@PathVariable long id, @RequestBody @Validated ProductDTO productDTO) {

        Optional<Product> optionalProduct = productService.product(id);
        if (optionalProduct.isEmpty())
            throw new NotFoundException();

        Product product = optionalProduct.get();
        product.setId(id);
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        return productService.save(product);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Product product) {
        if(product == null)
            throw new NotFoundException();
        productService.delete(product.getId());
    }
}
