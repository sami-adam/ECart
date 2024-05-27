package com.ecart.controller;

import com.ecart.dto.OrderDTO;
import com.ecart.entity.Order;
import com.ecart.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImpl orderService;
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getAllProducts(){
        return ResponseEntity.ok(orderService.getAllProducts());
    }

    @PostMapping("/addOrder")
    public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(orderService.addOrder(orderDTO));
    }
}
