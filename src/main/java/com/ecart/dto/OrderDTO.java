package com.ecart.dto;

import com.ecart.entity.OrderLine;
import com.ecart.entity.User;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;

@Data
public class OrderDTO {
    private String reference;
    private Date date = new Date(System.currentTimeMillis());
    private User user;
    private HashSet<OrderLineDTO> orderLines = new HashSet<>();
    private double total;
}
