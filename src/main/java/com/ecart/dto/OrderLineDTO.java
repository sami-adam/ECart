package com.ecart.dto;

import com.ecart.entity.Order;
import com.ecart.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class OrderLineDTO {
    @JsonIgnore
    private OrderDTO order;
    private Product product;
    private double unitPrice;
    private double quantity;
    private double discount;
    private double subTotal;
}
