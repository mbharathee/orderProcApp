package com.example.order.service;




import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.order.model.Order;
import com.example.order.model.orderStatus;
import com.example.order.repository.orderRepository;

@Service
public class orderService {
    
    private final orderRepository orderRepo;

    public orderService(orderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepo.findById(id);
    }

    public Order createOrder(Order order) {
        order.setStatus(orderStatus.DELIVERED);
        return orderRepo.save(order);
    }
    public Order updateOrder(Long id, Order updatedOrder) {
        return orderRepo.findById(id)
                .map(existingOrder -> {
                    existingOrder.setCustomerName(updatedOrder.getCustomerName());
                    existingOrder.setProduct(updatedOrder.getProduct());
                    existingOrder.setQuantity(updatedOrder.getQuantity());
                    existingOrder.setPrice(updatedOrder.getPrice());
                    return orderRepo.save(existingOrder);
                })
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }

    public List<Order> getOrdersByStatus(orderStatus status) {
        return orderRepo.findByStatus(status);
    }
}
