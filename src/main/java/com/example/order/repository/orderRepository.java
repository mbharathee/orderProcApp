package com.example.order.repository;




import com.example.order.model.Order;
import com.example.order.model.orderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface orderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(orderStatus status);
}
