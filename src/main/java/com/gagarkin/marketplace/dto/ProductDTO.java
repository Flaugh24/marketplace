package com.gagarkin.marketplace.dto;

import com.gagarkin.marketplace.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class ProductDTO {

    private long id;

    @NotNull
    @Size(min = 10)
    private String name;

    @NotNull
    private double price;

    public ProductDTO(@NotNull Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
