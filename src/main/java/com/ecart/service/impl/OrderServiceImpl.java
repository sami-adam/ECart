package com.ecart.service.impl;

import com.ecart.dto.OrderDTO;
import com.ecart.dto.OrderLineDTO;
import com.ecart.entity.Order;
import com.ecart.entity.OrderLine;
import com.ecart.repository.OrderLineRepository;
import com.ecart.repository.OrderRepository;
import com.ecart.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final ModelMapper mapper;
    public OrderServiceImpl(OrderRepository orderRepository, OrderLineRepository orderLineRepository){
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
        this.mapper = new ModelMapper();
    }
    public List<OrderDTO> getAllProducts(){
        List<Order> orders = orderRepository.findAll().stream().toList();
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for(Order order: orders){
            HashSet<OrderLineDTO> orderLineDTOS = new HashSet<>();
            List<OrderLine> orderLines = orderLineRepository.getOrderLineByOrder(order);
            orderLines.stream().map(orderLine -> mapper.map(orderLine, OrderLineDTO.class)).forEach(
                    orderLineDTOS::add
            );
            OrderDTO orderDTO = mapper.map(order, OrderDTO.class);
            orderDTO.setOrderLines(orderLineDTOS);
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }

    public OrderDTO addOrder(OrderDTO orderDTO){
        Set<OrderLine> orderLines = new HashSet<>();
        for(OrderLineDTO orderLineDTO: orderDTO.getOrderLines()){
            orderLines.add(orderLineRepository.save(mapper.map(orderLineDTO, OrderLine.class)));
            System.out.println(orderLineDTO);
        }
        Order order = mapper.map(orderDTO, Order.class);
        order.setOrderLines(orderLines);
        orderRepository.save(order);
        return orderDTO;
    }
}
